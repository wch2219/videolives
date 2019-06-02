package com.example.kottlinbaselib.holder

import android.view.View
import androidx.annotation.DrawableRes

interface InterfaceViewHolder<H>: View.OnLongClickListener, View.OnClickListener {

    fun <T : View> getView(viewId: Int): T

    fun setText(viewId: Int, text: CharSequence): H

    fun setViewVisibility(viewId: Int, visibility: Int): H


    fun setImageResource(viewId: Int, resourceId: Int): H

    fun setTextColor(viewId: Int, textColor: Int): H

    fun setBackGroundRes(viewId: Int, @DrawableRes resourceId: Int): H

    fun setCheckBox(viewId: Int, ischeck: Boolean): H


    /**
     * 关于事件的
     */
    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): H

    /**
     * 关于事件的
     */
    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener): H
}