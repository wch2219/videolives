package com.example.videolive.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.ui.base.BaseActivity

class FeedBackActivity : BaseActivity<BasePresenter<IView>,IView>() {
    override fun getlayoutId(): Int {
        return R.layout.activity_feed_back
    }

    override fun initView() {

    }

    override fun initData() {

    }


}