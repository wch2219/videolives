package com.example.kottlinbaselib.mvp.view

interface IView {
    fun showLoading()

    fun dismissLoading()

    fun onError(mess:String?)

    fun showDialog(mess: String?,code:Int)
    fun onAgainLogin()
}