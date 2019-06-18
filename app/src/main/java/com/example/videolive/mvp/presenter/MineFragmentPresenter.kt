package com.example.videolive.mvp.presenter

import com.alibaba.fastjson.JSON
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.model.bean.UserInfoBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.MineFragmentView
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver

class MineFragmentPresenter(view: MineFragmentView) : BasePresenter<MineFragmentView>(view) {


    fun getUserInfo() {

        val map: MutableMap<String, Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map[Contents.Token] = SPUtils.getString(Contents.Token)
        val userInfo = Model.getServer().getUserInfo(map)
        Model.getObservable(userInfo, object : CustomObserver<UserInfoBean>(mvpView) {
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
                    mvpView.userInfo(t.data.info[0])
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
