package com.example.videolive.mvp.view

import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.model.bean.VideoListBean

interface PoperInfoActivityIView:IView {
    fun VideoData(info: MutableList<VideoListBean.DataBean.InfoBean>)
}