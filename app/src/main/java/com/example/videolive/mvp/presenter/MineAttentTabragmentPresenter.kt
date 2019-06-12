package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.model.bean.FollowListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.MineAttentTabragmentIView
import com.hg.kotlin.api.CustomObserver

class MineAttentTabragmentPresenter(view:MineAttentTabragmentIView):BasePresenter<MineAttentTabragmentIView>(view) {


    fun getFollowList(page:Int){

        val map:MutableMap<String,Any?> = mutableMapOf()
        map.put(Contents.UID,SPUtils.getString(Contents.UID))
        map.put("p",page)
        map.put("touid",SPUtils.getString(Contents.UID))

        val followList = Model.getServer().getFollowList(map)
        Model.getObservable(followList,object :CustomObserver<FollowListBean>(mvpView){
            override fun success(t: FollowListBean) {
                if (t.data.code == 0) {
                    mvpView.followData(t.data.info)
                }
            }
        })
    }
}