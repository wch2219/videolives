package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.PoperInfoActivityIView
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver

class PoperInfoActivityPresenter(view:PoperInfoActivityIView):BasePresenter<PoperInfoActivityIView>(view) {


    fun getVideoList(page:Int, touid:String){

        val map:MutableMap<String,Any?> = mutableMapOf()
        map.put(Contents.UID,SPUtils.getString(Contents.UID))
        map.put("p",page)
        map.put("touid",touid)

        val followList = Model.getServer().getPoperPageVideos(map)
        Model.getObservable(followList,object :CustomObserver<VideoListBean>(mvpView){
            override fun success(t: VideoListBean) {
                if (t.data.code == 0) {
                    mvpView.VideoData(t.data.info)
                }else{
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
                }
            }
        })
    }
}