package com.example.videolive.ui.activitys

import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.view.View
import com.example.kottlinbaselib.utils.HideUtil
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.R
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.mvp.presenter.MineInfoPresenter
import com.example.videolive.mvp.view.MineInfoIView
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.views.popu.EditPopuWindow
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_mine_info.*


class MineInfoActivity : BaseActivity<MineInfoPresenter,MineInfoIView>(),View.OnClickListener,MineInfoIView {
    override fun getlayoutId(): Int {
        return R.layout.activity_mine_info
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        iv_head.setOnClickListener(this)
        tv_nickname.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_head -> {
                PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
//                    .openCamera(PictureMimeType.ofVideo())
                    .maxSelectNum(1)
                    .enableCrop(true)
                    .isGif(false)

                    .previewImage(true)
                    .withAspectRatio(1,1)
                    .imageSpanCount(4)
                    .freeStyleCropEnabled(true)
                    .forResult(1)
            }
            R.id.tv_nickname ->{
                EditPopuWindow(mContext)
                    .instance()
                    .setAffOnClick(object :EditPopuWindow.OnAffClickListener{
                        override fun affClick(content: String) {
                            HideUtil.hideSoftKeyboard(mContext as Activity)
                            tv_nickname.text = content
                               ToastUtil.show("修改成功")
                        }
                    })
                    .showAtLocation(tv_nickname, Gravity.CENTER,0,0)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val list = PictureSelector.obtainMultipleResult(data)
            if (list.size != 0) {
               GlideUtils.showHead(mContext,iv_head,list[0].cutPath)
                presenter.upavatar(list[0].cutPath)
            }
        }
    }

    override fun createPresenter(): MineInfoPresenter {
        return MineInfoPresenter(mvpView)
    }
}
