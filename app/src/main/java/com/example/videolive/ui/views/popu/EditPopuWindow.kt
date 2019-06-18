package com.example.videolive.ui.views.popu


import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.videolive.R

class EditPopuWindow(context: Context) : Dialog(context), View.OnClickListener {

    var viewHolder: CommonViewHolder? = null


    fun instance(): EditPopuWindow {
        val view = LayoutInflater.from(context).inflate(R.layout.popu_edit, null)
        this.setContentView(view)
//        this.width = ViewGroup.LayoutParams.MATCH_PARENT
//        this.height = ViewGroup.LayoutParams.MATCH_PARENT
//        this.an = R.style.popwin_anim_style
//        this.isOutsideTouchable = true
//        this.isClippingEnabled = false
//        this.isFocusable = true
//        this.setBackgroundDrawable(ColorDrawable(0x077000000))
            this.setCancelable(false)
        viewHolder = CommonViewHolder(view)
        viewHolder?.setOnClickListener(R.id.tv_close, this)
        viewHolder?.setOnClickListener(R.id.tv_aff, this)
        window.setBackgroundDrawableResource(android.R.color.transparent)
        return this
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_close -> {

            }


            R.id.tv_aff -> {
                val editText = viewHolder?.getView<EditText>(R.id.et_content)
                if (editText?.text.toString().trim().isNullOrEmpty()) {
                    Toast.makeText(context, "请输入要修改的昵称！！！", Toast.LENGTH_SHORT).show()
                    return
                }

                if (onAffClickListener != null) {


                    onAffClickListener!!.affClick(editText?.text.toString().trim())
                }
            }

        }
        dismiss()
    }


    fun setAffOnClick(onAffClickListener: OnAffClickListener): Dialog {
        this.onAffClickListener = onAffClickListener
        show()
        return this
    }

    private var onAffClickListener: OnAffClickListener? = null

    interface OnAffClickListener {
        fun affClick(content: String)
    }

}
