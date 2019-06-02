package com.example.videolive.ui.views.popu

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.videolive.R

class SharePopuWindow(context: Context) : PopupWindow(context), View.OnClickListener {
    var context: Context? = null

    init {
        this.context = context
    }

    fun instance(): SharePopuWindow {
        val view = LayoutInflater.from(context).inflate(R.layout.popu_share, null)
        this.contentView = view
        this.width = ViewGroup.LayoutParams.MATCH_PARENT
        this.height = ViewGroup.LayoutParams.MATCH_PARENT
        this.animationStyle = R.style.popwin_anim_style
        this.isOutsideTouchable = true
        this.isClippingEnabled = false
        this.isFocusable = true
//        this.setBackgroundDrawable(ColorDrawable(0x077000000))

        var viewHolder = CommonViewHolder(view)
        viewHolder.setOnClickListener(R.id.tv_close,this)
        viewHolder.setOnClickListener(R.id.tv_downcode,this)
        viewHolder.setOnClickListener(R.id.tv_savelink,this)
        return this
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_close ->
                dismiss()
            R.id.tv_downcode -> {


            }

            R.id.tv_savelink -> {
            }
        }
    }
}
