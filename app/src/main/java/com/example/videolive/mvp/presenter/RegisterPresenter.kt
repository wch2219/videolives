package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.LogUtils
import com.example.videolive.model.bean.BaseResult
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.RegisterView
import com.hg.kotlin.api.CustomObserver

class RegisterPresenter(view: RegisterView) : BasePresenter<RegisterView>(view){

    fun getAuth(phone:String){
       val observable = Model.getServer().getAuth(phone)
        Model.getObservable(observable,object :CustomObserver<BaseResult>(mvpView){
            override fun onNext(t: BaseResult) {
                LogUtils.I(t.code)
            }
        })
    }

    fun register(phone: String, pwd: String, authcode: String) {
        val map = mutableMapOf<String,Any>()
        map.put(Contents.Phone,phone)
        map.put(Contents.Password,pwd)
        map.put(Contents.AuthCode,authcode)
        val observable = Model.getServer().register(Model.getRequestBody(map))
        Model.getObservable(observable,object :CustomObserver<BaseResult>(mvpView){
            override fun onNext(t: BaseResult) {
                LogUtils.I(t.code)
            }
        })
    }
}
