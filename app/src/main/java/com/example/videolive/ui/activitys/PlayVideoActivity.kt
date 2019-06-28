package com.example.videolive.ui.activitys

import android.app.Dialog
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.alibaba.fastjson.JSON
import com.dueeeke.videocontroller.StandardVideoController
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.R
import com.example.videolive.model.bean.UserInfoBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.views.popu.SharePopuWindow
import com.hg.kotlin.api.ApiContents
import com.hg.kotlin.api.CustomObserver
import kotlinx.android.synthetic.main.activity_play_video.*



/**
 * 视频播放
 */
class PlayVideoActivity : BaseActivity<BasePresenter<IView>, IView>() {
    override fun getlayoutId(): Int {

        return com.example.videolive.R.layout.activity_play_video
    }

    override fun initView() {

    }

    override fun initData() {
        val url = intent.getStringExtra("url")

        ijkVideoView.setUrl(url) //设置视频地址
        val controller = StandardVideoController(this)
        controller
//        controller.setTitle("网易公开课-如何掌控你的自由时间") //设置视频标题
        ijkVideoView.setVideoController(controller) //设置控制器，如需定制可继承BaseVideoController
        ijkVideoView.setLooping(true)

        startPlay()
        ll_jinggao.setOnClickListener {
            getUserInfo()

        }

    }

    private fun startPlay() {
        if (ll_jinggao.visibility == View.VISIBLE) {

            return
        }

        val api = intent.getStringExtra("api")
        if ("api/public/?service=Video.GetMyVideo" == api) {

            ijkVideoView.start() //开始播放，不调用则不自动播放
            return
        }

        val can_play_video = SPUtils.getInt(Contents.CANPLAYVIDEONUM)
        if (can_play_video <= 0) {
            //todo
            val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, null)
            val builder = Dialog(mContext)
            builder.setContentView(view)
            builder.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val btn_close = view.findViewById<Button>(R.id.btn_close)
            btn_close.setOnClickListener {
                builder.dismiss()
                getUserInfo()

                val url = SharePopuWindow(mContext)
                    .instance()
                    .setUrl(ApiContents.ShareUrl)
                url.showAtLocation(ijkVideoView, Gravity.CENTER, 0, 0)
                url.setOnDismissListener {
//                    startPlay()
                    ll_jinggao.visibility = View.VISIBLE
                }

                //                builder.dismiss()
                //                SharePopuWindow(mContext)
                //                    .instance()
                //
                //                    .setUrl(ApiContents.ShareUrl)
                //                    .showAtLocation(rv_list, Gravity.CENTER, 0, 0)
            }
            builder.setCancelable(false)
            builder.show()




            return
        }

        ijkVideoView.start() //开始播放，不调用则不自动播放
    }

    fun getUserInfo() {
        if (TextUtils.isEmpty(SPUtils.getString(Contents.UID))) {

            return
        }
        val map: MutableMap<String, Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map[Contents.Token] = SPUtils.getString(Contents.Token)
        val userInfo = Model.getServer().getUserInfo(map)
        Model.getObservable(userInfo, object : CustomObserver<UserInfoBean>(null) {
            override fun success(t: UserInfoBean) {
                if (t.data.code == 0) {
                    SPUtils.save(Contents.LoginInfo, JSON.toJSONString(t))
                    SPUtils.save(Contents.AVATAR, t.data.info[0].avatar)
                    SPUtils.save(Contents.USER_NAME, t.data.info[0].user_nicename)
                    SPUtils.save(Contents.SIGNATURE, t.data.info[0].signature)
                    SPUtils.save(Contents.UID, t.data.info[0].id)
                    SPUtils.save(Contents.MAXIVIDEOMUM, t.data.info[0].view_videos)//最大视频数量
                    SPUtils.save(Contents.CANPLAYVIDEONUM, t.data.info[0].can_view_videos)//能播放的视频数量
                    SPUtils.save(Contents.INVITATIONCODE, t.data.info[0].invitationcode)//邀请码
                    val can_play_video = SPUtils.getInt(Contents.CANPLAYVIDEONUM)
                    if (can_play_video > 0) {
                        ll_jinggao.visibility = View.GONE
                        startPlay()
                    }
                } else {
                    ToastUtil.show(t.data.msgX)
                    if (t.data.code == ApiContents.AGAIN_LOGIN) {
                        mvpView.onAgainLogin()
                    }
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        ijkVideoView.pause()
    }

    override fun onResume() {
        super.onResume()
        ijkVideoView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        ijkVideoView.release()
    }


    override fun onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed()
        }
    }


}
