package com.example.kottlinbaselib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SPUtils {

    companion object {
        private var mSharedPreferences: SharedPreferences? = null

        fun instance(context: Context): SharedPreferences? {
            if (mSharedPreferences == null) {
                mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
            }

            return mSharedPreferences
        }

        //存储方式,存储位置：/data/data/<package name>/shared_prefs
        @SuppressLint("ApplySharedPref")
        fun save(
            key: String,
            value: Any
        ) {

            if (value is String) {
                mSharedPreferences!!.edit().putString(key, value).apply()
            } else if (value is Boolean) {
                mSharedPreferences!!.edit().putBoolean(key, value).apply()
            } else if (value is Int) {
                mSharedPreferences!!.edit().putInt(key, value).apply()
            } else if (value is Float) {
                mSharedPreferences!!.edit().putFloat(key, value).apply()
            } else if (value is Long) {
                mSharedPreferences!!.edit().putLong(key, value).apply()
            }
        }


        fun remove(key: String) {
            mSharedPreferences!!.edit().remove(key).commit()
        }


        // 获取SharedPreferences数据指定key所对应的value，如果该key不存在，返回默认值false(boolen),""(string);
        fun getBoolean(
            key: String
        ): Boolean {

            return mSharedPreferences!!.getBoolean(key, false)
        }

        fun getString(
            key: String
        ): String? {

            return mSharedPreferences!!.getString(key, "")
        }

        fun getInt(key: String): Int {

            return mSharedPreferences!!.getInt(key, 0)
        }

        fun getLong(key: String): Long {

            return mSharedPreferences!!.getLong(key, 0)
        }

        /**
         * 清空所有数据
         * @param
         * @param fileName
         */
        fun removeFile(fileName: String) {

            val editor = mSharedPreferences!!.edit()
            editor.clear()
            editor.commit()
        }

    }
}