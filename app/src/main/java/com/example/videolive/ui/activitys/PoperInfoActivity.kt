package com.example.videolive.ui.activitys

import androidx.recyclerview.widget.GridLayoutManager
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.R
import com.example.videolive.model.bean.UserInfoBean
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.mvp.model.Model
import com.example.videolive.mvp.presenter.PoperInfoActivityPresenter
import com.example.videolive.mvp.view.PoperInfoActivityIView
import com.example.videolive.ui.adapters.MineAdapter
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.views.SpaceItemDecoration
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver
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
        adapter = MineAdapter(mContext!!, infos, R.layout.item_mine, "")
        rv_list.adapter = adapter
        rv_list.addItemDecoration(SpaceItemDecoration(5, 10))
        presenter.getVideoList(page,touid)
        getUserInfo()
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


    fun getUserInfo() {

        val map: MutableMap<String, Any?> = mutableMapOf()
        map[Contents.UID] = intent.getStringExtra(Contents.TOUID)
        map[Contents.Token] = SPUtils.getString(Contents.Token)
        val userInfo = Model.getServer().getUserInfo(map)
        Model.getObservable(userInfo, object : CustomObserver<UserInfoBean>(null) {
            override fun success(t: UserInfoBean) {
                if (t.data.code == 0) {
//                    SPUtils.save(Contents.LoginInfo, JSON.toJSONString(t))
//                    SPUtils.save(Contents.AVATAR, t.data.info[0].avatar)
//                    SPUtils.save(Contents.USER_NAME, t.data.info[0].user_nicename)
//                    SPUtils.save(Contents.SIGNATURE, t.data.info[0].signature)
//                    SPUtils.save(Contents.UID, t.data.info[0].id)
//                    SPUtils.save(Contents.MAXIVIDEOMUM,t.data.info[0].view_videos)//最大视频数量
//                    SPUtils.save(Contents.CANPLAYVIDEONUM,t.data.info[0].can_view_videos)//能播放的视频数量
//                    SPUtils.save(Contents.INVITATIONCODE,t.data.info[0].invitationcode)//邀请码
//                    val can_play_video = SPUtils.getInt(Contents.CANPLAYVIDEONUM)
                    GlideUtils.show(mContext,iv_head,t.data.info[0].avatar)
                    tv_nickname.setText(t.data.info[0].user_nicename)
                    tv_signdesc.setText(t.data.info[0].signature)
                } else {
                    ToastUtil.show(t.data.msgX)
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
                }
            }
        })
    }
}
