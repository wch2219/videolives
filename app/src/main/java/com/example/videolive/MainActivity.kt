package com.example.videolive


import android.Manifest
import android.content.Intent
import androidx.fragment.app.Fragment
import com.dueeeke.videoplayer.player.VideoViewManager
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.PermissionUtils
import com.example.videolive.ui.activitys.VideoPreViewActivity
import com.example.videolive.ui.adapters.MainFragmentAdapter
import com.example.videolive.ui.base.BaseActivity
import com.example.videolive.ui.fragments.MineFragment
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_main.*
import ui.fragments.HomeFragment

class MainActivity : BaseActivity<BasePresenter<IView>, IView>()
     {
    private var fragments: MutableList<Fragment> = mutableListOf()
    override fun getlayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        fragments.add(HomeFragment())
//        fragments.add(ShareFragment())
        fragments.add(MineFragment())
        val titles:MutableList<String> = mutableListOf()
        titles.add("首页")
        titles.add("我的")
        viewpage.adapter = MainFragmentAdapter(supportFragmentManager, fragments,titles)
        tablayout.setupWithViewPager(viewpage)
        viewpage.currentItem = 0
        viewpage.offscreenPageLimit = 3
    }

    override fun initData() {

    }

    override fun initListener() {
        super.initListener()
//        bott_navi_view.setOnNavigationItemSelectedListener(this)
//        viewpage.addOnPageChangeListener(this)
        id_camera.setOnClickListener {
            if (PermissionUtils.checkReadPermission(arrayOf(Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE),100,mContext)) {
                PictureSelector.create(this@MainActivity)
                    .openCamera(PictureMimeType.ofVideo())
                    .forResult(1)
            }
        }

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
        if (!VideoViewManager.instance().onBackPressed()) {
            super.onBackPressed()
        }
    }
}
