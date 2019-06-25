package com.example.videolive.ui.fragments


import android.Manifest
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kottlinbaselib.utils.PermissionUtils
import com.example.videolive.R
import com.example.videolive.model.bean.UserInfoBean
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
        iv_exit.setOnClickListener(this::onClick)
        viewpage.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
//                if (position == 0) {
//                    tablayout.setBackgroundColor(resources.getColor(R.color.text_66))
//                }else{
//                    tablayout.setBackgroundColor(resources.getColor(R.color.white))
//                }
            }
        })

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
                if (PermissionUtils.checkReadPermission(
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE

                        ), 100, mContext
                    )
                ){
                    SharePopuWindow(mContext)
                        .instance()
                        .setUrl(ApiContents.ShareUrl)
                        .showAtLocation(iv_share, Gravity.CENTER, 0, 0)
                }


            }

            R.id.tv_verUp -> {
                Toast.makeText(mContext, "已经更新到最新版本", Toast.LENGTH_SHORT).show()

            }
            R.id.iv_share ->{

                if (PermissionUtils.checkReadPermission(
                        arrayOf(
                         Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 100, mContext
                    )
                ){
                    SharePopuWindow(mContext)
                        .instance()
                        .setUrl(ApiContents.ShareUrl)
                        .showAtLocation(iv_share, Gravity.CENTER, 0, 0)
                }
            }
            R.id.iv_exit->{
                mvpView.onAgainLogin()
            }
        }
    }

    override fun userInfo(t: UserInfoBean.DataBean.InfoBean) {
        GlideUtils.showHead(mContext,iv_head,t.avatar)
        tv_nickname.text = t.user_nicename
        tv_usersign.text = t.signature
    }

    override fun createPresenter(): MineFragmentPresenter {
        return MineFragmentPresenter(mvpView)
    }
}
