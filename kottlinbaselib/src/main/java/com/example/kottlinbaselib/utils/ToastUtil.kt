package com.example.kottlinbaselib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.Toast

class ToastUtil{
    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context ?= null
        fun init(context: Context){
            this.context = context
        }

        fun show(content:String){
            var toast = Toast.makeText(context, content, Toast.LENGTH_SHORT)

                toast.setGravity(
                    Gravity.CENTER,
                    0,
                    0
                )
            toast.show()
        }

    }
}
