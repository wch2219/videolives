package com.example.videolive.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView

import com.example.videolive.R
import com.example.videolive.ui.base.BaseFragment

class LiveFragment : BaseFragment<BasePresenter<IView>,IView>() {
    override fun getlayoutId(): Int {
        return R.layout.fragment_live
    }

    override fun initView(rootView: View?) {

    }

    override fun initData() {

    }



}
