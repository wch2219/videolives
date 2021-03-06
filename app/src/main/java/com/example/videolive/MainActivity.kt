package com.example.videolive


import android.Manifest
import android.content.Intent
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.PermissionUtils
import com.example.kottlinbaselib.utils.SPUtils
import com.example.videolive.model.bean.AttentBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.ui.activitys.VideoPreViewActivity
import com.example.videolive.ui.adapters.MainFragmentAdapter
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.fragments.MainFragment
import com.example.videolive.ui.fragments.MineFragment
import com.example.videolive.ui.fragments.UserInforPageFragment
import com.example.videolive.ui.views.OnDoubleClick
import com.hg.kotlin.api.CustomObserver
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_main.viewpage
import kotlinx.android.synthetic.main.fragment_main.*

import ui.fragments.HomeFragment

class MainActivity : BaseActivity<BasePresenter<IView>, IView>() {
    private var fragments: MutableList<Fragment> = mutableListOf()
    override fun getlayoutId(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        fragments.add(MainFragment())
//        fragments.add(ShareFragment())

        fragments.add(UserInforPageFragment())


        viewpage.adapter = MainFragmentAdapter(supportFragmentManager, fragments, mutableListOf())
        viewpage.currentItem = 0
        viewpage.offscreenPageLimit = 2
    }

    override fun initData() {
        if (PermissionUtils.checkReadPermission(
                arrayOf(
                    Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
                ), 100, mContext
            )
        ){

        }
    }

    override fun initListener() {
        super.initListener()
//        bott_navi_view.setOnNavigationItemSelectedListener(this)
//        viewpage.addOnPageChangeListener(this)
//        id_camera.setOnClickListener {
//
//
//            if (PermissionUtils.checkReadPermission(
//                    arrayOf(
//                        Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
//                    ), 100, mContext
//                )
//            ) {
//                PictureSelector.create(this@MainActivity)
//                    .openGallery(PictureMimeType.ofVideo())
////                    .openCamera(PictureMimeType.ofVideo())
//                    .previewVideo(true)
//                    .videoMaxSecond(10)
//                    .forResult(1)
//            }
//
//
////            upVideo()
//        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val list = PictureSelector.obtainMultipleResult(data)
            if (list.size != 0) {
                val intent = Intent(mContext, VideoPreViewActivity::class.java)
                intent.putExtra("path", list[0].path)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {

    }

    private fun upVideo() {
        val map: MutableMap<String, Any?> = mutableMapOf()
        map[Contents.UID] = SPUtils.getString(Contents.UID)
        map[Contents.Token] = SPUtils.getString(Contents.Token)
        map["thumb"] = "https://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg"
        map["href"] =
            "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"
        val upVideo = Model.getServer().upVideo(map)
        Model.getObservable(upVideo, object : CustomObserver<AttentBean>(mvpView) {
            override fun success(t: AttentBean) {

            }
        })
    }



}
