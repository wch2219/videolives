package com.example.videolive.mvp.view

import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.model.bean.UserInfoBean

interface MineFragmentView : IView {
    fun userInfo(t: UserInfoBean.DataBean.InfoBean)
}
