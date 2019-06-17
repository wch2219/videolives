package com.example.videolive

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.kottlinbaselib.utils.AppManager
import com.example.kottlinbaselib.utils.NetworkUtils
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.api.HttpManager



class MyApplication : MultiDexApplication() {

    companion object {
        var sInstance: MyApplication? = null
    }
    private var mMyApplication: MyApplication? = null
    var mAppManager: AppManager? = null


    var sDeBug: Boolean = false
    private var mCount: Int = 0
    private var mFront: Boolean = false//是否前台
    //public static RefWatcher sRefWatcher;
    private var mBeautyInited: Boolean = false

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        HttpManager.getInstance().init(applicationContext)
        MultiDex.install(applicationContext)
        SPUtils.instance(applicationContext)
        NetworkUtils.getInstance()
        ToastUtil.init(applicationContext)
        mAppManager = AppManager.getAppManager()
//        registerActivityLifecycleCallbacks()
    }

    private fun registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {

            }

            override fun onActivityStarted(activity: Activity) {
                mCount++
                if (!mFront) {
                    mFront = true

//                    AppConfig.getInstance().isFrontGround = true
                }
            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {
                mCount--
                if (mCount == 0) {
                    mFront = false

//                    AppConfig.getInstance().isFrontGround = false
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })
    }
}