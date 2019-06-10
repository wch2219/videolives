package com.example.videolive.ui.views.popu

import android.app.Activity
import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.utils.HideUtil
import com.example.videolive.R

class EditPopuWindow(context: Context) : PopupWindow(context), View.OnClickListener {
    var context: Context? = null
    var viewHolder: CommonViewHolder? = null

    init {
        this.context = context
    }

    fun instance(): EditPopuWindow {
        val view = LayoutInflater.from(context).inflate(R.layout.popu_edit, null)
        this.contentView = view
        this.width = ViewGroup.LayoutParams.MATCH_PARENT
        this.height = ViewGroup.LayoutParams.MATCH_PARENT
        this.animationStyle = R.style.popwin_anim_style
        this.isOutsideTouchable = true
        this.isClippingEnabled = false
        this.isFocusable = true
//        this.setBackgroundDrawable(ColorDrawable(0x077000000))

        viewHolder = CommonViewHolder(view)
        viewHolder?.setOnClickListener(R.id.tv_close, this)
        viewHolder?.setOnClickListener(R.id.tv_aff, this)
        return this
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_close ->

                dismiss()
            R.id.tv_aff -> {
                val editText = viewHolder?.getView<EditText>(R.id.et_content)
                if (editText?.text.toString().trim().isNullOrEmpty()) {
                    Toast.makeText(context, "请输入要修改的昵称！！！", Toast.LENGTH_SHORT).show()
                    return
                }

                if (onAffClickListener != null) {
                    dismiss()

                    onAffClickListener!!.affClick(editText?.text.toString().trim())
                }
            }

        }
    }

    override fun dismiss() {
        HideUtil.toggleSoftInput(context as Activity)
        super.dismiss()
    }
    fun setAffOnClick(onAffClickListener: OnAffClickListener): EditPopuWindow {
        this.onAffClickListener = onAffClickListener
        return this
    }
    private var onAffClickListener: OnAffClickListener? = null

    interface OnAffClickListener {
        fun affClick(content: String)
    }

}
