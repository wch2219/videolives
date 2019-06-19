package com.example.videolive.ui.views.popu

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.utils.CookbarUtils
import com.example.kottlinbaselib.utils.PermissionUtils
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.R
import com.example.videolive.model.utils.Contents
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.model.utils.QRCode
import com.hg.kotlin.api.ApiContents
import java.io.File



class SharePopuWindow(context: Context) : PopupWindow(context), View.OnClickListener {
    var context: Context? = null
    var viewHolder: CommonViewHolder? = null
    var createQRCode: Bitmap? = null

    init {
        this.context = context
    }

    fun instance(): SharePopuWindow {
        val view = LayoutInflater.from(context).inflate(com.example.videolive.R.layout.popu_share, null)
        this.contentView = view
        this.width = ViewGroup.LayoutParams.MATCH_PARENT
        this.height = ViewGroup.LayoutParams.MATCH_PARENT
        this.animationStyle = R.style.popwin_anim_style
        this.isOutsideTouchable = true
        this.isClippingEnabled = false
        this.isFocusable = true
//        this.setBackgroundDrawable(ColorDrawable(0x077000000))

        viewHolder = CommonViewHolder(view)
        viewHolder?.setOnClickListener(com.example.videolive.R.id.tv_close, this)
        viewHolder?.setOnClickListener(com.example.videolive.R.id.tv_downcode, this)
        viewHolder?.setOnClickListener(com.example.videolive.R.id.tv_savelink, this)
        return this
    }

    fun setUrl(url: String): SharePopuWindow {
        viewHolder?.setText(com.example.videolive.R.id.tv_sharelink, """邀请码: ${SPUtils.getString(Contents.INVITATIONCODE)}""")
        val logo = BitmapFactory.decodeResource(context?.resources, R.mipmap.logo, null)
        createQRCode = QRCode.createQRCodeWithLogo5("""$url?code=${SPUtils.getString(Contents.INVITATIONCODE)}""",500,logo)

        viewHolder?.getView<ImageView>(com.example.videolive.R.id.iv_code)?.setImageBitmap(createQRCode)
        return this
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            com.example.videolive.R.id.tv_close ->
                dismiss()
            com.example.videolive.R.id.tv_downcode -> {

                PermissionUtils.checkReadPermission(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), PermissionUtils.WriteAndRead, context
                )

                GlideUtils.saveBitmap(context, createQRCode, object : GlideUtils.SavePicListener {
                    override fun saveSuccess(file: File?) {

                    }

                })
                dismiss()
            }

            com.example.videolive.R.id.tv_savelink -> {
                copyText(ApiContents.ShareUrl)
                dismiss()
            }
        }
    }


    private fun copyText(s: String) {
        CookbarUtils.show(context, "复制成功", true)
        ToastUtil.show("复制成功")
        //获取剪贴板管理器：
        val cm = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val mClipData = ClipData.newPlainText("Label", s)
        // 将ClipData内容放到系统剪贴板里。
        cm.primaryClip = mClipData
    }
}
