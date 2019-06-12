package com.example.videolive.mvp.view

import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.model.bean.VideoListBean

interface HomeVideoIView:IView{
    fun videoList(info: MutableList<VideoListBean.DataBean.InfoBean>)
    fun attentSucc(position: Int, isattent: String?)
    fun likeSucc(position: Int, islike: String?, likes: String?)
}