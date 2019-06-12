package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.model.bean.AttentBean
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.HomeVideoIView
import com.hg.kotlin.api.CustomObserver

class HomeVideoPresenter(view:HomeVideoIView):BasePresenter<HomeVideoIView>(view) {


    fun getVideoList(page: Int){
        val map:MutableMap<String,Any?> = mutableMapOf()
        map["uid"] = SPUtils.getString(Contents.UID)
        map["p"] = page

        val videoList = Model.getServer().getVideoList(map)
        Model.getObservable(videoList,object :CustomObserver<VideoListBean>(mvpView){

            override fun success(t: VideoListBean) {
                if (t.data.code == 0) {
                    mvpView.videoList(t.data.info)
                }else{
                    ToastUtil.show(t.data.msgX)
                }
            }
        })
    }


     fun attent(position:Int,touid:String){
        val map:MutableMap<String,Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map["touid"] = touid

        val attent = Model.getServer().attent(map)
        Model.getObservable(attent,object: CustomObserver<AttentBean>(null){
            override fun success(t: AttentBean) {
                if (t.data.code == 0) {
                    mvpView.attentSucc(position,t.data.info[0].isattent)
                }else{
                    ToastUtil.show(t.data.msgX)
                }
            }
        })
    }


     fun like(position:Int,videoId:String){
        val map:MutableMap<String,Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map["token"] = SPUtils.getString(Contents.Token)
        map["token"] = SPUtils.getString(Contents.Token)
        map["videoid"] = videoId

        val attent = Model.getServer().like(map)
        Model.getObservable(attent,object: CustomObserver<AttentBean>(null){
            override fun success(t: AttentBean) {
                if (t.data.code == 0) {
                    mvpView.likeSucc(position,t.data.info[0].islike,t.data.info[0].likes)
                }else{
                    ToastUtil.show(t.data.msgX)
                }
            }
        })
    }


}