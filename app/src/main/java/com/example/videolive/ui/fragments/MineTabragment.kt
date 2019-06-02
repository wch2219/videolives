package com.example.videolive.ui.fragments


import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.model.utils.DataUtils
import com.example.videolive.ui.adapters.MineAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_mine_tabragment.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MineTabragment : BaseFragment<BasePresenter<IView>,IView>() {
    private var adapter: MineAdapter? = null
    override fun getlayoutId(): Int {
        return R.layout.fragment_mine_tabragment
    }

    override fun initView(rootView: View) {

    }

    override fun initData() {
        val linaManager = GridLayoutManager(mContext, 3)
        rv_list.layoutManager = linaManager
        adapter = MineAdapter(mContext!!, DataUtils.getTikTokVideoList(), R.layout.item_mine)
        rv_list.adapter = adapter
        rv_list.addItemDecoration(SpaceItemDecoration(5, 10))
    }



}
