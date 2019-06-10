package com.example.videolive.ui.views.popu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.utils.ToastUtil.Companion.context
import com.example.videolive.R

class MainCenterPopu: PopupWindow(context), View.OnClickListener {
    var context: Context? = null
    var viewHolder: CommonViewHolder? = null

    init {
        this.context = context
    }

    fun instance(): MainCenterPopu {
        val view = LayoutInflater.from(context).inflate(R.layout.edit_text, null)
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

    }
}
