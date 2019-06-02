package com.example.videolive.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView

import com.example.videolive.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_play_video.*
import com.luck.picture.lib.tools.VoiceUtils.release

import com.dueeeke.videocontroller.StandardVideoController






/**
 * 视频播放
 */
class PlayVideoActivity : BaseActivity<BasePresenter<IView>,IView>() {
    override fun getlayoutId(): Int {

        return com.example.videolive.R.layout.activity_play_video
    }

    override fun initView() {

    }

    override fun initData() {
        val url = intent.getStringExtra("url")
        ijkVideoView.setUrl(url) //设置视频地址
        val controller = StandardVideoController(this)
        controller
//        controller.setTitle("网易公开课-如何掌控你的自由时间") //设置视频标题
        ijkVideoView.setVideoController(controller) //设置控制器，如需定制可继承BaseVideoController
        ijkVideoView.setLooping(true)
        ijkVideoView.start() //开始播放，不调用则不自动播放


    }

    override fun onPause() {
        super.onPause()
        ijkVideoView.pause()
    }

    override fun onResume() {
        super.onResume()
        ijkVideoView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        ijkVideoView.release()
    }


    override fun onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed()
        }
    }
}
