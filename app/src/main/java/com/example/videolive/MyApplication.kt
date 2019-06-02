package com.example.videolive

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.kottlinbaselib.utils.AppManager
import com.example.kottlinbaselib.utils.NetworkUtils
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.api.HttpManager

class MyApplication : MultiDexApplication(){
    private var mMyApplication: MyApplication? = null
    var mAppManager: AppManager  ?= null
    override fun onCreate() {
        super.onCreate()
        HttpManager.getInstance().init(applicationContext)
        MultiDex.install(applicationContext)
        SPUtils.instance(applicationContext)
        NetworkUtils.getInstance()
        mAppManager = AppManager.getAppManager()
    }
}