package com.example.videolive.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.model.utils.DataUtils
import com.example.videolive.ui.adapters.MineAdapter
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.views.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_poper_info.*

class PoperInfoActivity : BaseActivity<BasePresenter<IView>, IView>() {
    private var adapter: MineAdapter? = null

    override fun getlayoutId(): Int {
        return R.layout.activity_poper_info
    }

    override fun initView() {

    }

    override fun initData() {
 val linaManager = GridLayoutManager(mContext, 3)
        rv_list.layoutManager = linaManager
        adapter = MineAdapter(mContext!!, DataUtils.getTikTokVideoList(), R.layout.item_mine)
        rv_list.adapter = adapter
        rv_list.addItemDecoration(SpaceItemDecoration(5, 10))
    }


}
