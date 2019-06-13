package com.hg.kotlin.api.upload

import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.BaseContents
import com.example.kottlinbaselib.utils.LogUtils
import com.hg.kotlin.api.OkHttpInterce
import com.hg.kotlin.api.OnErrorCalBackListener
import io.reactivex.observers.DefaultObserver

abstract class FileUploadObserver<T>(view: IView?) : DefaultObserver<T>(), OnErrorCalBackListener {
    private var mIView: IView? = null

    init {
        OkHttpInterce.setOnErrorCalBackListener(this)
        mIView = view
    }

    override fun onNext(t: T) {
        mIView?.dismissLoading()
//        val code = (t as BaseResult).ret
//        if (code == ApiContents.AGAIN_LOGIN) {
//            mIView?.onError((t as BaseResult).msg)
//            mIView?.onAgainLogin()
//        } else {
//
//            if (code == 400) {
//                mIView?.onError((t as BaseResult).msg)
//            } else {
//
//
//
//            }
//        }
        onUpLoadSuccess(t)

    }

    override fun onError(e: Throwable) {
        mIView?.dismissLoading()
        onUpLoadFail(e)
    }

    override fun onComplete() {
        mIView?.dismissLoading()
    }

    override fun onError(code: Int, mess: String) {
        try {
            if (code == BaseContents.Code_200) {
//                if (!TextUtils.isEmpty(mess)) {
//
//
//                    val baseBean = JSON.parseObject<InterBean>(mess, InterBean::class.java)
//                    when (baseBean.ret) {
//                        ApiContents.AGAIN_LOGIN ->
//
//                            mIView?.onAgainLogin()
//                        ApiContents.SUCCESS -> {
//                                when(baseBean.data.code){
//                                    ApiContents.AGAIN_LOGIN ->
//
//                                        mIView?.onAgainLogin()
//
//                                }
//                        }
//                        else -> {
//                            mIView?.onError(baseBean.msg)
//                        }
//                    }
//                    return
//
//                    mIView?.dismissLoading()
//                }
                mIView?.dismissLoading()
            } else {

                mIView?.onError(mess)
                mIView?.dismissLoading()
            }
        } catch (e: Exception) {
            LogUtils.I("err:$mess")
        }
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