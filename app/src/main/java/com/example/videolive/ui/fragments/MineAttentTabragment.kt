package com.example.videolive.ui.fragments


import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videolive.R
import com.example.videolive.model.bean.FollowListBean
import com.example.videolive.mvp.presenter.MineAttentTabragmentPresenter
import com.example.videolive.mvp.view.MineAttentTabragmentIView
import com.example.videolive.ui.adapters.MineAttentAdapter
import com.example.videolive.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine_tabragment.*


/**
 * 关注
 *
 */
class MineAttentTabragment : BaseFragment<MineAttentTabragmentPresenter,MineAttentTabragmentIView>(),MineAttentTabragmentIView {
    private var adapter: MineAttentAdapter? = null
    private var infos: MutableList<FollowListBean.DataBean.InfoBean> = mutableListOf()
    private var page:Int = 1;
    override fun getlayoutId(): Int {
        return R.layout.fragment_mine_tabragment
    }

    override fun initView(rootView: View) {
        initRefreshLayout(smartfresh)
        smartfresh.setEnableLoadMore(true)
        smartfresh.setEnableRefresh(false)
    }

    override fun initData() {

        val linaManager = LinearLayoutManager(mContext)
        rv_list.layoutManager = linaManager
        adapter = MineAttentAdapter(mContext!!, infos, R.layout.item_mine_attent)
        rv_list.adapter = adapter
        rv_list.addItemDecoration(DividerItemDecoration(mContext,LinearLayoutManager.VERTICAL))
        presenter.getFollowList(page)
    }

    override fun loadMore() {
        page++
        presenter.getFollowList(page)
    }

    override fun followData(info: MutableList<FollowListBean.DataBean.InfoBean>) {
        infos.addAll(info)
        adapter?.notifyDataSetChanged()
    }

    override fun createPresenter(): MineAttentTabragmentPresenter {
        return MineAttentTabragmentPresenter(mvpView)
    }
}
