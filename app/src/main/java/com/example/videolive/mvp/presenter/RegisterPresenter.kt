package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.model.bean.AuthCodeBean
import com.example.videolive.model.bean.RegisterBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.model.utils.MD5Util
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.RegisterView
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver

class RegisterPresenter(view: RegisterView) : BasePresenter<RegisterView>(view) {

    fun getAuth(phone: String, forPWdAuthCode: String) {
        val sign = MD5Util.getMD5("mobile=$phone&${ApiContents.SALT}")
        val observable = Model.getServer().getAuth(forPWdAuthCode, phone, sign)
        Model.getObservable(observable, object : CustomObserver<AuthCodeBean>(mvpView) {
            override fun success(t: AuthCodeBean) {
                if (t.data.code == 1002) {

                    ToastUtil.show("发送成功,请注意查收短信")
                    mvpView.authSuccess(t)
                } else {
                    ToastUtil.show(t.data.msgX)
                }

            }
        })
    }

    fun register(
        phone: String,
        pwd: String,
        affpwd: String,
        authcode: String,
        api: String,
        invitationcode: String?
    ) {

        if (phone.isNullOrEmpty()) {
            ToastUtil.show("手机号不能为空")
            return
        }
        if (authcode.isNullOrEmpty()) {
            ToastUtil.show("验证码不能为空")
            return
        }
        if (pwd.isNullOrEmpty()) {
            ToastUtil.show("密码不能为空")
            return
        }
        if (affpwd.isNullOrEmpty()) {
            ToastUtil.show("确认密码不能为空")
            return
        }

        if (pwd != affpwd) {

            ToastUtil.show("两次密码不一致")
            mvpView.cleanPwd()
            return
        }


        val map = mutableMapOf<String, Any>()
        map[Contents.USER_LOGIN] = phone
        map[Contents.USER_PASS] = pwd
        map[Contents.USER_PASS2] = pwd
        map[Contents.CODE] = authcode
        if (!invitationcode.isNullOrEmpty()) {
            map["invitationcode"] = invitationcode
        }
        val observable = Model.getServer().register(api, map)
        Model.getObservable(observable, object : CustomObserver<RegisterBean>(mvpView) {
            override fun success(t: RegisterBean) {
                if (t.data.code == 0) {
                    mvpView.registerSuccess()
                    ToastUtil.show("操作成功")
                } else {

                    ToastUtil.show(t.data.msgX)
                }

            }
        })
    }

//    fun getInvitation(dev: String) {
//        val observable = Model.getServer().getInvitation(dev)
//        Model.getObservable(observable, object : CustomObserver<InvitationBean>(mvpView) {
//            override fun success(t: InvitationBean) {
//                if (t.data.code == 0) {
//                    mvpView.setInvitation(t.data.info.user_agent_code)
//                }else{
//
//                }
//            }
//        })
//    }
}
