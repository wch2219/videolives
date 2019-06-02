package com.hg.kotlin.api

import com.example.videolive.model.bean.BaseResult
import com.example.videolive.model.utils.Contents
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

public interface ApiServer {


    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("manager/user/getAuth")
    fun  getAuth(@Query(Contents.Phone) phone:String): Observable<BaseResult>


    @POST("manager/user/register")
    fun register(@Body body:RequestBody): Observable<BaseResult>?

    @POST("manager/user/login")
    fun login(@Body requestBody: RequestBody?):Observable<BaseResult>?
}