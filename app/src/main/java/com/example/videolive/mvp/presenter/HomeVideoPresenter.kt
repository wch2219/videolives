package com.example.videolive.mvp.presenter

import android.text.TextUtils
import com.alibaba.fastjson.JSON
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.model.bean.AttentBean
import com.example.videolive.model.bean.UserInfoBean
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.HomeVideoIView
import com.hg.kotlin.api.ApiContents
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
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
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
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
                    ToastUtil.show(t.data.msgX)
                }
            }
        })
    }


     fun like(position:Int,videoId:String){
        val map:MutableMap<String,Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map["token"] = SPUtils.getString(Contents.Token)
        map["videoid"] = videoId

        val attent = Model.getServer().like(map)
        Model.getObservable(attent,object: CustomObserver<AttentBean>(null){
            override fun success(t: AttentBean) {
                if (t.data.code == 0) {
                    mvpView.likeSucc(position,t.data.info[0].islike,t.data.info[0].likes)
                }else{
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
                    ToastUtil.show(t.data.msgX)
                }
            }
        })
    }


    fun upVideoPlayWatchNum(){
        if (TextUtils.isEmpty(SPUtils.getString(Contents.UID))) {

            return
        }
        val map:MutableMap<String,Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map["token"] = SPUtils.getString(Contents.Token)

        val mineVideo = Model.getServer().getMineVideo(ApiContents.UPDATEVVIEWVIDEOS, map)

        Model.getObservable(mineVideo,object :CustomObserver<VideoListBean>(mvpView){
            override fun success(t: VideoListBean) {

            }
        })
    }
    fun getUserInfo() {
        if (TextUtils.isEmpty(SPUtils.getString(Contents.UID))) {

            return
        }
        val map: MutableMap<String, Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map[Contents.Token] = SPUtils.getString(Contents.Token)
        val userInfo = Model.getServer().getUserInfo(map)
        Model.getObservable(userInfo, object : CustomObserver<UserInfoBean>(null) {
            override fun success(t: UserInfoBean) {
                if (t.data.code == 0) {
                    SPUtils.save(Contents.LoginInfo, JSON.toJSONString(t))
                    SPUtils.save(Contents.AVATAR, t.data.info[0].avatar)
                    SPUtils.save(Contents.USER_NAME, t.data.info[0].user_nicename)
                    SPUtils.save(Contents.SIGNATURE, t.data.info[0].signature)
                    SPUtils.save(Contents.UID, t.data.info[0].id)
                    SPUtils.save(Contents.MAXIVIDEOMUM,t.data.info[0].view_videos)//最大视频数量
                    SPUtils.save(Contents.CANPLAYVIDEONUM,t.data.info[0].can_view_videos)//能播放的视频数量
                    SPUtils.save(Contents.INVITATIONCODE,t.data.info[0].invitationcode)//邀请码
                    mvpView.userInfo()
                } else {
                    ToastUtil.show(t.data.msgX)
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
                }
            }
        })
    }
}