package com.example.videolive.ui.fragments


import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
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
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.SpaceItemDecoration
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver
import kotlinx.android.synthetic.main.fragment_user_infor_page.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class UserInforPageFragment : BaseFragment<PoperInfoActivityPresenter, PoperInfoActivityIView>(),
    PoperInfoActivityIView {
    private var adapter: MineAdapter? = null
    private var page:Int = 1
    private var infos: MutableList<VideoListBean.DataBean.InfoBean> = mutableListOf()
    override fun getlayoutId(): Int {
        return R.layout.fragment_user_infor_page
    }

    override fun initView(view: View) {
        EventBus.getDefault().register(this)
        initRefreshLayout(smartfresh)
        smartfresh.setEnableLoadMore(true)
        smartfresh.setEnableRefresh(false)

    }

    override fun initData() {

        val touid = activity?.intent?.getStringExtra(Contents.TOUID)
        val linaManager = GridLayoutManager(mContext, 3)
        rv_list.layoutManager = linaManager
        adapter = MineAdapter(mContext!!, infos, R.layout.item_mine, arguments?.getString("api"))
        rv_list.adapter = adapter
        rv_list.addItemDecoration(SpaceItemDecoration(5, 10))

    }

    override fun VideoData(info: MutableList<VideoListBean.DataBean.InfoBean>) {
        infos.addAll(info)
        adapter?.notifyDataSetChanged()
    }

    override fun loadMore() {
        val touid = activity?.intent?.getStringExtra(Contents.TOUID)
        presenter.getVideoList(page++,touid!!)
    }
    override fun createPresenter(): PoperInfoActivityPresenter {
        return PoperInfoActivityPresenter(mvpView)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(touid:String) {
        presenter.getVideoList(page,touid!!)
        getUserInfo(touid)

    }
    override fun onDestroyView() {
        EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }

    fun getUserInfo(touid: String) {

        val map: MutableMap<String, Any?> = mutableMapOf()
        map[Contents.UID] = touid
//        map[Contents.Token] = SPUtils.getString(Contents.Token)
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
