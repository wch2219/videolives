package com.hg.kotlin.api

import com.example.videolive.model.bean.AuthCodeBean
import com.example.videolive.model.bean.LoginBean
import com.example.videolive.model.bean.RegisterBean
import com.example.videolive.model.utils.Contents
import io.reactivex.Observable
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
    fun getUserInfo(@FieldMap map: MutableMap<String, Any?>): Observable<LoginBean>?
}