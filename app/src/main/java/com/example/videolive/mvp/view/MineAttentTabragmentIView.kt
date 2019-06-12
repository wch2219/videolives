package com.example.videolive.mvp.view

import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.model.bean.FollowListBean

interface MineAttentTabragmentIView:IView {
    fun followData(info: MutableList<FollowListBean.DataBean.InfoBean>)
}