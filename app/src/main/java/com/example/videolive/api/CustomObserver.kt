package com.hg.kotlin.api

import android.os.Handler
import com.example.kottlinbaselib.mvp.view.IView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class CustomObserver<T>(view: IView): Observer<T>,OnErrorCalBackListener {

    private var mIView: IView?=null
    init {
        OkHttpInterce.setOnErrorCalBackListener(this)
        mIView = view
    }

    override fun onSubscribe(d: Disposable) {

        mIView?.showLoading()
    }

    abstract override fun onNext(t: T)

    override fun onError(e: Throwable) {
        mIView?.showLoading()
        //       onError(400,"连接失败");
    }

   override fun onError(code: Int, mess: String) {

    }

    override fun onComplete() {
        mIView?.dismissLoading()
    }

    var handler:Handler?=Handler(Handler.Callback { msg ->

        if (msg.what == 0) {

        }


        false
    })
}