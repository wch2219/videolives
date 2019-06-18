package com.example.videolive.ui.activitys

import android.content.Intent
import android.os.CountDownTimer
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.MainActivity
import com.example.videolive.R
import com.example.videolive.ui.base.BaseActivity

class SplashActivity : BaseActivity<BasePresenter<IView>, IView>() {
    override fun getlayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {

    }

    override fun initData() {
        mCountDownTimer.start()
    }

    var mCountDownTimer = object : CountDownTimer(1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            startActivity(Intent(mContext, MainActivity::class.java))
//            val token = SPUtils.getString(Contents.Token)
//            if (token.isNullOrEmpty()) {
//                startActivity(Intent(mContext, LoginActivity::class.java))
//            } else {
//                startActivity(Intent(mContext, MainActivity::class.java))
//            }

            finish()
        }
    }

    override fun onDestroy() {

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel()
        }
        super.onDestroy()
    }
}
