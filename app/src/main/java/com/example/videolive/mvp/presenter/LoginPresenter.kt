package com.example.videolive.mvp.presenter

import com.alibaba.fastjson.JSON
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.model.bean.LoginBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.LoginView
import com.hg.kotlin.api.CustomObserver

class LoginPresenter(view: LoginView) : BasePresenter<LoginView>(view) {


    fun login(mobile: String, pwd: String) {

        if (mobile.isNullOrEmpty()) {
            ToastUtil.show("手机号不能为空")
            return
        }

        if (pwd.isNullOrEmpty()) {
            ToastUtil.show("密码不能为空")
            return
        }


        val map = mutableMapOf<String, Any>()
        map[Contents.USER_LOGIN] = mobile
        map[Contents.USER_PASS] = pwd
        val login = Model.getServer().login(map)
        Model.getObservable(login, object : CustomObserver<LoginBean>(mvpView) {
            override fun success(t: LoginBean) {
                if (t.data.code == 0) {
                    ToastUtil.show("登陆成功")
                    SPUtils.save(Contents.LoginInfo, JSON.toJSONString(t))
                    SPUtils.save(Contents.AVATAR, t.data.info[0].avatar)
                    SPUtils.save(Contents.Token, t.data.info[0].token)
                    SPUtils.save(Contents.USER_NAME, t.data.info[0].user_nicename)
                    SPUtils.save(Contents.SIGNATURE, t.data.info[0].signature)
                    SPUtils.save(Contents.UID, t.data.info[0].id)
                    mvpView.loginSuccess()
                } else {
                    ToastUtil.show(t.data.msgX)
                }

            }

        })
    }
}