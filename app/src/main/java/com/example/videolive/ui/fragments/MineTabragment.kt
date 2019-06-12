package com.example.videolive.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.videolive.R
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.mvp.presenter.MineTabragmentPresenter
import com.example.videolive.mvp.view.MineTabragmentIView
import com.example.videolive.ui.adapters.MineAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_mine_tabragment.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MineTabragment : BaseFragment<MineTabragmentPresenter,MineTabragmentIView>(),MineTabragmentIView {
    private var adapter: MineAdapter? = null
    private var page:Int = 1
    private var infos: MutableList<VideoListBean.DataBean.InfoBean> = mutableListOf()
    companion object {
        fun instance(api:String):MineTabragment?{
            val fragment:MineTabragment = MineTabragment()
            val bundle:Bundle = Bundle()
            bundle.putString("api",api)
            fragment.arguments = bundle

            return fragment
        }
    }


    override fun getlayoutId(): Int {
        return R.layout.fragment_mine_tabragment
    }

    override fun initView(rootView: View) {
        initRefreshLayout(smartfresh)
        smartfresh.setEnableLoadMore(true)
        smartfresh.setEnableRefresh(false)
    }

    override fun initData() {
        val linaManager = GridLayoutManager(mContext, 3)
        rv_list.layoutManager = linaManager
        adapter = MineAdapter(mContext!!, infos, R.layout.item_mine)
        rv_list.adapter = adapter
        rv_list.addItemDecoration(SpaceItemDecoration(5, 10))
        if (arguments?.getString("api") != null) {

            presenter.getVideoData(arguments?.getString("api"),page)
        }
    }

    override fun loadMore() {
        presenter.getVideoData(arguments?.getString("api"),page++)
    }
    override fun videoData(api: String, info: MutableList<VideoListBean.DataBean.InfoBean>) {
        if (arguments?.getString("api") == api) {
            infos.addAll(info)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun createPresenter(): MineTabragmentPresenter {
        return MineTabragmentPresenter(mvpView)
    }
}
