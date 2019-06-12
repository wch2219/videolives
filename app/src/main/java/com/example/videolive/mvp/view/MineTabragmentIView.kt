package com.example.videolive.mvp.view

import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.model.bean.VideoListBean

interface MineTabragmentIView:IView {
    fun videoData(api: String, info: MutableList<VideoListBean.DataBean.InfoBean>)

}