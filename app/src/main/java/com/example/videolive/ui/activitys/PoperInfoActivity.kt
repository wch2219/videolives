package com.example.videolive.ui.activitys

import androidx.recyclerview.widget.GridLayoutManager
import com.example.videolive.R
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.presenter.PoperInfoActivityPresenter
import com.example.videolive.mvp.view.PoperInfoActivityIView
import com.example.videolive.ui.adapters.MineAdapter
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.views.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_poper_info.*


class PoperInfoActivity : BaseActivity<PoperInfoActivityPresenter, PoperInfoActivityIView>(),PoperInfoActivityIView {
    private var adapter: MineAdapter? = null
    private var page:Int = 1
    private var infos: MutableList<VideoListBean.DataBean.InfoBean> = mutableListOf()
    override fun getlayoutId(): Int {
        return R.layout.activity_poper_info
    }

    override fun initView() {
        isBlack = true
        initRefreshLayout(smartfresh)
        smartfresh.setEnableLoadMore(true)
        smartfresh.setEnableRefresh(false)
    }

    override fun initData() {

        val touid = intent.getStringExtra(Contents.TOUID)
        val linaManager = GridLayoutManager(mContext, 3)
        rv_list.layoutManager = linaManager
        adapter = MineAdapter(mContext!!, infos, R.layout.item_mine)
        rv_list.adapter = adapter
        rv_list.addItemDecoration(SpaceItemDecoration(5, 10))
        presenter.getVideoList(page,touid)
    }

    override fun VideoData(info: MutableList<VideoListBean.DataBean.InfoBean>) {
        infos.addAll(info)
        adapter?.notifyDataSetChanged()
    }

    override fun loadMore() {
        val touid = intent.getStringExtra(Contents.TOUID)
        presenter.getVideoList(page++,touid)
    }
    override fun createPresenter(): PoperInfoActivityPresenter {
        return PoperInfoActivityPresenter(mvpView)
    }
}
