package com.example.videolive.mvp.presenter

import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.model.bean.AttentBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.view.MineInfoIView
import com.hg.kotlin.api.CustomObserver
import com.hg.kotlin.api.upload.FileUploadObserver
import com.hg.kotlin.api.upload.UploadFileRequestBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File


class MineInfoPresenter(view: MineInfoIView):BasePresenter<MineInfoIView>(view) {
    fun upavatar(cutPath: String?) {
        val map:MutableMap<String,Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map[Contents.Token] = SPUtils.getString(Contents.Token)


        val file = File(cutPath)
        val requestBody = UploadFileRequestBody(file, mFileUploadObserver)
        // 创建RequestBody，传入参数："multipart/form-data"，String
        val uid = RequestBody.create(MediaType.parse("multipart/form-data"), SPUtils.getString(Contents.UID))
        val token = RequestBody.create(MediaType.parse("multipart/form-data"), SPUtils.getString(Contents.Token))
//        val requestApiAttribute = RequestBody.create(MediaType.parse("multipart/form-data"), attribute)
        val part = MultipartBody.Part.createFormData("file", file.name, requestBody)
        val upAvatar = Model.getServer().upAvatar(uid,
            token,part)
        Model.getObservable(upAvatar,object :CustomObserver<AttentBean>(mvpView){
            override fun success(t: AttentBean) {
                if (t.data.code == 0) {

                }else{
                    if (t.data.code == 700) {
                        mvpView.onAgainLogin()
                    }else{
                        mvpView.onError(t.data.msgX)
                    }
                }
            }
        })
    }

    private val mFileUploadObserver = object : FileUploadObserver<ResponseBody>(mvpView) {
        override fun onUpLoadSuccess(t: ResponseBody) {

        }

        override fun onUpLoadFail(e: Throwable) {

        }

        override fun onProgress(progress: Int) {

        }
    }
}