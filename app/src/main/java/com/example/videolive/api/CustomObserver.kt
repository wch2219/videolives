package com.hg.kotlin.api

import android.os.Handler
import android.text.TextUtils
import com.alibaba.fastjson.JSON
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.BaseContents
import com.example.kottlinbaselib.utils.LogUtils
import com.example.videolive.model.bean.BaseResult
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class CustomObserver<T>(view: IView?) : Observer<T>, OnErrorCalBackListener {

    private var mIView: IView? = null

    init {
        OkHttpInterce.setOnErrorCalBackListener(this)
        mIView = view
    }

    override fun onSubscribe(d: Disposable) {

        mIView?.showLoading()
    }

    override fun onNext(t: T) {
        val code = (t as BaseResult).ret
        if (code == ApiContents.AGAIN_LOGIN) {
            mIView?.onError((t as BaseResult).msg)
            mIView?.onAgainLogin()
        } else {

            if (code == 400) {
                mIView?.onError((t as BaseResult).msg)
            }else{
                success(t)
            }
        }
    }

    protected abstract fun success(t: T)

    override fun onError(e: Throwable) {
        mIView?.showLoading()
        //       onError(400,"连接失败");
    }

    override fun onError(code: Int, mess: String) {

        try {
            if (code == BaseContents.Code_200) {
                if (!TextUtils.isEmpty(mess)) {


                    val baseBean = JSON.parseObject<BaseResult>(mess, BaseResult::class.java)
                    when (baseBean.ret) {
                        ApiContents.AGAIN_LOGIN ->

                            mIView?.onAgainLogin()
                        ApiContents.SUCCESS -> {

                        }
                        else -> {
                            mIView?.onError(baseBean.msg)
                        }
                    }
                    return

                    mIView?.dismissLoading()
                }
            } else {

                mIView?.onError(mess)
                mIView?.dismissLoading()
            }
        } catch (e: Exception) {
            LogUtils.I("err:$mess")
        }
    }

    override fun onComplete() {
        mIView?.dismissLoading()
    }

    var handler: Handler? = Handler(Handler.Callback { msg ->

        if (msg.what == 0) {

        }


        false
    })
}