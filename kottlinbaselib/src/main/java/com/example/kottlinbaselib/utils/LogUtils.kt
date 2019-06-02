package com.example.kottlinbaselib.utils

import android.util.Log
import com.example.kottlinbaselib.BuildConfig

class LogUtils {
    companion object {
        private var Auth_log = "wch"
        private val DEBUG = BuildConfig.DEBUG
        fun I(tag: String, values: Any) {
            if (!DEBUG) {

                return
            }
            Log.i(tag, values.toString())
        }

        fun I(values: Any) {
            if (!DEBUG) {

                return
            }
            Log.i(Auth_log, values.toString())
        }

        fun E(values: Any) {
            if (!DEBUG) {

                return
            }
            Log.e(Auth_log, values.toString())
        }

        fun W(values: Any) {
            if (!DEBUG) {

                return
            }
            Log.w(Auth_log, values.toString())
        }
    }

}