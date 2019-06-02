package com.example.videolive.ui.activitys

import com.dueeeke.videocontroller.StandardVideoController
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_pre_view.*




class VideoPreViewActivity : BaseActivity<BasePresenter<IView>, IView>() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_pre_view
    }

    override fun initView() {
    }

    override fun initData() {
        val  path = intent.getStringExtra("path")
        ijkVideoView.setUrl(path) //设置视频地址
        val controller = StandardVideoController(this)
        controller.setTitle("网易公开课-如何掌控你的自由时间") //设置视频标题
        ijkVideoView.setVideoController(controller) //设置控制器，如需定制可继承BaseVideoController
        ijkVideoView.start() //开始播放，不调用则不自动播放

    }


}
