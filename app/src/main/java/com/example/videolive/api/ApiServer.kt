package com.hg.kotlin.api

import com.example.videolive.model.bean.BaseResult
import com.example.videolive.model.utils.Contents
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiServer {


    //    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("manager/user/getAuth")
    fun getAuth(@Query(Contents.Phone) phone: String): Observable<BaseResult>

    @FormUrlEncoded
    @POST("manager/user/register")
    fun register(@FieldMap map: MutableMap<String, Any>): Observable<BaseResult>?

    @FormUrlEncoded
    @POST("manager/user/login")
    fun login(@FieldMap map: MutableMap<String, Any>): Observable<BaseResult>?
}