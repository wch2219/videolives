package com.example.kottlinbaselib.mvp.presenter

import com.example.kottlinbaselib.mvp.IModel
import com.example.kottlinbaselib.mvp.view.IView

interface IPresenter<V : IView> {

    fun attachView(mRootView: V)
    fun attachModel(model: IModel)
    fun detachView()

}