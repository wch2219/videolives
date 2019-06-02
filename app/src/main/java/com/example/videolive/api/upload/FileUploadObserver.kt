package com.hg.kotlin.api.upload

import io.reactivex.observers.DefaultObserver

abstract class FileUploadObserver<T>: DefaultObserver<T>() {

    override fun onNext(t: T) {
        onUpLoadSuccess(t)
    }

    override fun onError(e: Throwable) {
        onUpLoadFail(e)
    }

    override fun onComplete() {

    }

    //监听进度的改变
    fun onProgressChange(bytesWritten: Long, contentLength: Long) {
        onProgress((bytesWritten * 100 / contentLength).toInt())
    }

    //上传成功的回调
    abstract fun onUpLoadSuccess(t: T)

    //上传失败回调
    abstract fun onUpLoadFail(e: Throwable)

    //上传进度回调
    abstract fun onProgress(progress: Int)
}