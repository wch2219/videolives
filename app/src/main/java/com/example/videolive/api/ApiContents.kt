package com.hg.kotlin.api

class ApiContents {
    companion object {
        const val SALT = "76576076c1f5f657b634e966c8836a06"
        //返回状态码
        const val SUCCESS: Int = 200 //成功
        const val AGAIN_LOGIN: Int = 700 //登录失效重新登录
        val Token: String = "token"
        const val BaseUrl: String = "http://www.tlimit.top/"
        private const val PARENTPARAM = "api/public/?service="
        const val REGISTER: String = PARENTPARAM + "Login.UserReg"//注册
        const val GETCODE: String = PARENTPARAM + "Login.GetCode"//获取验证码
        const val LOGIN: String = PARENTPARAM + "Login.UserLogin"//登陸
        const val ForPWdAuthCode: String = PARENTPARAM + "Login.GetForgetCode"//找回密码验证码
        const val ForPWd: String = PARENTPARAM + "Login.UserFindPass"//找回密码
        const val GET_USERINFO: String = PARENTPARAM + "User.GetBaseInfo"//获取用户信息
        const val GETVideoList: String = PARENTPARAM + "Video.GetVideoList"//获取视频列表
        const val ATTENT: String = PARENTPARAM + "User.SetAttent"//关注
        const val LIKE: String = PARENTPARAM + "Video.AddLike"//视频点赞
        const val GetFollowsList: String = PARENTPARAM + "User.GetFollowsList"//关注列表
        const val GetAttentionVideo: String = PARENTPARAM + "Video.GetAttentionVideo"//获取点赞视频列表
        const val GetHomeVideo: String = PARENTPARAM + "Video.GetHomeVideo"//获取个人主页视频
        const val UPDATEPASS: String = PARENTPARAM + "User.UpdatePass"//获取密码
        const val SETVIDEO: String = PARENTPARAM + "Video.SetVideo"//上传短视频
        const val GetMyVideo: String = PARENTPARAM + "Video.GetMyVideo"//上传短视频
        const val UPDATEAVATAR: String = PARENTPARAM + "User.updateAvatar"//上传头像
        const val INVITATION: String = PARENTPARAM + "Login.getInvitation"//获取邀请码
        const val ShareUrl: String = BaseUrl + "videoShare/"//分享二维码链接地址
        const val UPDATEVVIEWVIDEOS: String = PARENTPARAM + "User.updatevViewVideos"//更新观看视频次数
    }

}