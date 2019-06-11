package com.hg.kotlin.api

class ApiContents {
    companion object {
        const val SALT = "76576076c1f5f657b634e966c8836a06"
        //返回状态码
        const val SUCCESS: Int = 200 //成功
        const val AGAIN_LOGIN: Int = 700 //登录失效重新登录
        val Token: String = "token"
        const val BaseUrl: String = "http://www.tlimit.top/api/public/"
        private const val PARENTPARAM = "?service="
        const val REGISTER: String = PARENTPARAM + "Login.UserReg"//注册
        const val GETCODE: String = PARENTPARAM + "Login.GetCode"//获取验证码
        const val LOGIN: String = PARENTPARAM + "Login.UserLogin"//登陸
        const val ForPWdAuthCode: String = PARENTPARAM + "Login.GetForgetCode"//找回密码验证码
        const val ForPWd: String = PARENTPARAM + "Login.UserFindPass"//找回密码
        const val GET_USERINFO: String = PARENTPARAM + "User.GetBaseInfo"//获取用户信息
    }

}