package com.example.videolive.ui.fragments


import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.model.utils.Contents
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.ui.activitys.ChangePwdActivity
import com.example.videolive.ui.activitys.FeedBackActivity
import com.example.videolive.ui.activitys.LoginActivity
import com.example.videolive.ui.adapters.MainFragmentAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.popu.SharePopuWindow
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 我的
 */
class MineFragment : BaseFragment<BasePresenter<IView>, IView>(), View.OnClickListener {


    override fun getlayoutId(): Int {

        return R.layout.fragment_mine
    }

    override fun initView(rootView: View) {

    }

    override fun onResume() {
        super.onResume()
//        tv_cleancache.text = String.format("缓存%s", CleanDataUtils.getTotalCacheSize(mContext))
    }

    override fun initData() {
        GlideUtils.showHead(mContext, iv_head, Contents.userHeader)
        val titles:MutableList<String> = mutableListOf()
         val fragments: MutableList<Fragment> = mutableListOf()
        titles.add("关注")
        titles.add("收藏")
        titles.add("我的视频")
        fragments.add(MineTabragment())
        fragments.add(MineTabragment())
        fragments.add(MineTabragment())
        viewpage.adapter = MainFragmentAdapter(childFragmentManager,fragments,titles!!)
        tablayout.setupWithViewPager(viewpage)
    }

    override fun initListener() {
        ll_user.setOnClickListener(this::onClick)
        tv_changepwd.setOnClickListener(this::onClick)
        tv_feedback.setOnClickListener(this::onClick)
        tv_cleancache.setOnClickListener(this::onClick)
        iv_share.setOnClickListener(this::onClick)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_user -> {
                startActivity(Intent(mContext, LoginActivity::class.java))

            }
            R.id.tv_feedback -> {
                startActivity(Intent(mContext, FeedBackActivity::class.java))

            }
            R.id.tv_changepwd -> {
                startActivity(Intent(mContext, ChangePwdActivity::class.java))

            }
            R.id.tv_cleancache -> {
                SharePopuWindow(mContext!!)
                    .instance()
                    .showAtLocation(iv_head,Gravity.CENTER,0,0)


            }

            R.id.tv_verUp -> {
                Toast.makeText(mContext, "已经更新到最新版本", Toast.LENGTH_SHORT).show()

            }
            R.id.iv_share ->{

                SharePopuWindow(mContext!!)
                    .instance()
                    .showAtLocation(iv_head,Gravity.CENTER,0,0)
            }
        }
    }
}
