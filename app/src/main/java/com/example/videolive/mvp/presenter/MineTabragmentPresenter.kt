package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.MineTabragmentIView
import com.hg.kotlin.api.CustomObserver

class MineTabragmentPresenter(view:MineTabragmentIView):BasePresenter<MineTabragmentIView>(view) {

    fun getVideoData(api: String?, page: Int) {
        val map:MutableMap<String,Any?> = mutableMapOf()
        map.put("uid",SPUtils.getString(Contents.UID))
        map.put("token",SPUtils.getString(Contents.Token))
        map.put("p",page)
        val mineVideo = Model.getServer().getMineVideo(api!!, map)

        Model.getObservable(mineVideo,object :CustomObserver<VideoListBean>(mvpView){
            override fun success(t: VideoListBean) {
                if (t.data.code == 0) {
                 mvpView.videoData(api,t.data.info)
                }
            }
        })
    }
}