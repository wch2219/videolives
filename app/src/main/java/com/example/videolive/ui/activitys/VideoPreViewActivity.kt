package com.example.videolive.ui.activitys

import android.app.AlertDialog
import android.text.TextUtils
import android.view.LayoutInflater
import com.dueeeke.videocontroller.StandardVideoController
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.R
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.ui.base.BaseActivity
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver
import kotlinx.android.synthetic.main.activity_video_pre_view.*




class VideoPreViewActivity : BaseActivity<BasePresenter<IView>, IView>() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_pre_view
    }

    override fun initView() {
    }

    override fun initData() {

        val can_play_video = SPUtils.getInt(Contents.CANPLAYVIDEONUM)
        if (can_play_video <= 0) {
            //todo
            val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, null)
            val builder = AlertDialog.Builder(mContext)
            builder.setView(view)
            builder.setCancelable(false)
            builder.show()

            return
        }
        val  path = intent.getStringExtra("path")
        ijkVideoView.setUrl(path) //设置视频地址
        val controller = StandardVideoController(this)
        controller.setTitle("网易公开课-如何掌控你的自由时间") //设置视频标题
        ijkVideoView.setVideoController(controller) //设置控制器，如需定制可继承BaseVideoController
        ijkVideoView.start() //开始播放，不调用则不自动播放

        upVideoPlayWatchNum()
    }

    fun upVideoPlayWatchNum(){
        if (TextUtils.isEmpty(SPUtils.getString(Contents.UID))) {

            return
        }
        val map:MutableMap<String,Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map["token"] = SPUtils.getString(Contents.Token)

        val mineVideo = Model.getServer().getMineVideo(ApiContents.UPDATEVVIEWVIDEOS, map)

        Model.getObservable(mineVideo,object : CustomObserver<VideoListBean>(mvpView){
            override fun success(t: VideoListBean) {

            }
        })
    }
}
