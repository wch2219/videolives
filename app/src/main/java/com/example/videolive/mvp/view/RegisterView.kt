package com.example.videolive.mvp.view

import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.model.bean.AuthCodeBean

interface RegisterView :IView{
    fun authSuccess(t: AuthCodeBean)
    fun cleanPwd()
    fun registerSuccess()
    fun setInvitation(user_agent_code: String?)
}