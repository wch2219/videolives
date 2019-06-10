package com.example.videolive.ui.fragments


import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.model.utils.DataUtils
import com.example.videolive.ui.adapters.MineAdapter
import com.example.videolive.ui.adapters.MineAttentAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.SpaceItemDecoration
import com.luck.picture.lib.decoration.RecycleViewDivider
import kotlinx.android.synthetic.main.fragment_mine_tabragment.*


/**
 * 关注
 *
 */
class MineAttentTabragment : BaseFragment<BasePresenter<IView>,IView>() {
    private var adapter: MineAttentAdapter? = null
    override fun getlayoutId(): Int {
        return R.layout.fragment_mine_tabragment
    }

    override fun initView(rootView: View) {

    }

    override fun initData() {
        val linaManager = LinearLayoutManager(mContext)
        rv_list.layoutManager = linaManager
        adapter = MineAttentAdapter(mContext!!, DataUtils.getTikTokVideoList(), R.layout.item_mine_attent)
        rv_list.adapter = adapter
        rv_list.addItemDecoration(DividerItemDecoration(mContext,LinearLayoutManager.VERTICAL))
    }



}
