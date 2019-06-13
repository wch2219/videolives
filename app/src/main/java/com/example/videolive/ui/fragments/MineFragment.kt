package com.example.videolive.ui.fragments


import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.videolive.R
import com.example.videolive.model.bean.LoginBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.mvp.presenter.MineFragmentPresenter
import com.example.videolive.mvp.view.MineFragmentView
import com.example.videolive.ui.activitys.ChangePwdActivity
import com.example.videolive.ui.activitys.MineInfoActivity
import com.example.videolive.ui.adapters.MainFragmentAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.popu.SharePopuWindow
import com.hg.kotlin.api.ApiContents
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 我的
 */
class MineFragment : BaseFragment<MineFragmentPresenter, MineFragmentView>(), View.OnClickListener,MineFragmentView {


    override fun getlayoutId(): Int {

        return R.layout.fragment_mine
    }

    override fun initView(rootView: View) {

    }

    override fun onResume() {
        super.onResume()

        presenter.getUserInfo()
    }

    override fun initData() {
        GlideUtils.showHead(mContext, iv_head, Contents.userHeader)
        val titles:MutableList<String> = mutableListOf()
         val fragments: MutableList<Fragment> = mutableListOf()
        titles.add("关注")
        titles.add("收藏")
        titles.add("我的视频")
        fragments.add(MineAttentTabragment())
        fragments.add(MineTabragment.instance(ApiContents.GetAttentionVideo)!!)
        fragments.add(MineTabragment.instance(ApiContents.GetMyVideo)!!)

        viewpage.adapter = MainFragmentAdapter(childFragmentManager,fragments,titles!!)
        tablayout.setupWithViewPager(viewpage)
    }

    override fun initListener() {
        ll_user.setOnClickListener(this::onClick)
        tv_changepwd.setOnClickListener(this::onClick)
        tv_mineinfo.setOnClickListener(this::onClick)
        tv_cleancache.setOnClickListener(this::onClick)
        iv_share.setOnClickListener(this::onClick)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_user -> {
//                startActivity(Intent(mContext, LoginActivity::class.java))



            }
            R.id.tv_mineinfo -> {
                startActivity(Intent(mContext, MineInfoActivity::class.java))

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

    override fun userInfo(t: LoginBean.DataBean.InfoBean) {
        GlideUtils.showHead(mContext,iv_head,t.avatar)
        tv_nickname.text = t.user_nicename
        tv_usersign.text = t.signature
    }

    override fun createPresenter(): MineFragmentPresenter {
        return MineFragmentPresenter(mvpView)
    }
}
