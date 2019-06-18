package com.hg.kotlin.api

import com.example.videolive.model.bean.*
import com.example.videolive.model.utils.Contents
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiServer {


    //    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST
    fun getAuth(@Url api:String,@Query(Contents.MOBILE) phone: String, @Query(Contents.SIGN) sign:String): Observable<AuthCodeBean>

    @FormUrlEncoded
    @POST
    fun register(@Url api: String,@FieldMap map: MutableMap<String, Any>): Observable<RegisterBean>?

    @FormUrlEncoded
    @POST(ApiContents.LOGIN)
    fun login(@FieldMap map: MutableMap<String, Any>): Observable<LoginBean>?

    @FormUrlEncoded
    @POST(ApiContents.GET_USERINFO)
    fun getUserInfo(@FieldMap map: MutableMap<String, Any?>): Observable<UserInfoBean>?

    @FormUrlEncoded
    @POST(ApiContents.GETVideoList)
    fun getVideoList(@FieldMap map: MutableMap<String, Any?>): Observable<VideoListBean>?

    @FormUrlEncoded
    @POST(ApiContents.ATTENT)//关注/取消关注
    fun attent(@FieldMap map: MutableMap<String, Any?>):Observable<AttentBean>?
    @FormUrlEncoded
    @POST(ApiContents.LIKE)//点赞/取消点赞
    fun like(@FieldMap map: MutableMap<String, Any?>): Observable<AttentBean>?
    @FormUrlEncoded
    @POST(ApiContents.GetFollowsList)//获取关注列表
    fun getFollowList(@FieldMap map: MutableMap<String, Any?>):Observable<FollowListBean>

    @FormUrlEncoded
    @POST(ApiContents.GetHomeVideo)//获取个人主页视频
    fun getPoperPageVideos(@FieldMap map: MutableMap<String, Any?>):Observable<VideoListBean>


    @FormUrlEncoded
    @POST  //获取关注视频 发布的视频
    fun getMineVideo(@Url api: String,@FieldMap map: MutableMap<String, Any?>):Observable<VideoListBean>

    @FormUrlEncoded
    @POST(ApiContents.UPDATEPASS)
    fun changePwd(@FieldMap map: MutableMap<String, Any?>):Observable<LoginBean>


    @FormUrlEncoded
    @POST(ApiContents.SETVIDEO)//发布视频
    fun upVideo(@FieldMap map: MutableMap<String, Any?>):Observable<AttentBean>?

    @Multipart
    @POST(ApiContents.UPDATEAVATAR) //上传头像
    fun upAvatar(@Part(Contents.UID)uid: RequestBody, @Part(Contents.Token)token:RequestBody, @Part file: MultipartBody.Part):Observable<AttentBean>?

    @POST(ApiContents.INVITATION)//获取邀请码
    fun getInvitation(@Query("identifier")dev: String):Observable<InvitationBean>?
}