package com.example.videolive.ui.fragments


import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import com.dueeeke.videoplayer.player.IjkVideoView
import com.example.kottlinbaselib.utils.LogUtils
import com.example.videolive.R
import com.example.videolive.interfac.OnViewPagerListener
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.mvp.presenter.HomeVideoPresenter
import com.example.videolive.mvp.view.HomeVideoIView
import com.example.videolive.ui.adapters.HomeAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.TikTokController
import com.example.videolive.ui.views.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.fragment_home_video.*


class HomeVideoFragment :  BaseFragment<HomeVideoPresenter, HomeVideoIView>(),HomeVideoIView,HomeAdapter.OnClickListenter {
    var adapter: HomeAdapter? = null
    var manager: ViewPagerLayoutManager? = null
    private var mCurrentPosition: Int = 0
    private var mIjkVideoView: IjkVideoView? = null
    private var mTikTokController: TikTokController? = null
    private var isRelease:Boolean = true//是否重置播放器
    private var page:Int = 1
    private var infos: MutableList<VideoListBean.DataBean.InfoBean> = mutableListOf()
    override fun getlayoutId(): Int {
       return R.layout.fragment_home_video
    }

    override fun initView(rootView: View) {
        initRefreshLayout(smartfresh)
        smartfresh.setEnableLoadMore(true)
        smartfresh.setEnableRefresh(false)
        manager = ViewPagerLayoutManager(mContext!!, OrientationHelper.VERTICAL)
        rv_list.layoutManager = manager


    }

    override fun initData() {

        adapter = HomeAdapter(mContext!!, infos, R.layout.item_home)
        rv_list.adapter = adapter
        mIjkVideoView = IjkVideoView(mContext!!)
        mIjkVideoView?.setLooping(true)
        mTikTokController = TikTokController(mContext!!)
        mTikTokController?.setIjkVideoView(mIjkVideoView)
        mIjkVideoView?.setVideoController(mTikTokController)
        mTikTokController!!.setOnPlayProgressListener { currPro ->
            LogUtils.I("当前进度:%d"+currPro.toInt())
            seekBar.max = mIjkVideoView?.duration?.toInt()!!
            seekBar.progress = currPro.toInt()

        }
        presenter.getVideoList(page)
    }

    override fun initListener() {
        manager?.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {
//自动播放第一条
                if (isRelease) {
                    startPlay(0)
                }else{
                    isRelease = true
                }
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurrentPosition == position && isRelease) {
                    mIjkVideoView?.release()
                }else{
                    isRelease = true
                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                if (mCurrentPosition == position){

                    return
                }
                startPlay(position)
                mCurrentPosition = position
            }
        })

        adapter?.onClickListenter = this
    }


    override fun loadMore() {
        page++
        presenter.getVideoList(page)
    }

    private fun startPlay(position: Int) {
        val itemView = rv_list.getChildAt(0)
        val frameLayout = itemView.findViewById<FrameLayout>(R.id.container)
        Glide.with(this)
            .load(infos.get(position).thumb)

            .into(mTikTokController?.thumb!!)
        val parent = mIjkVideoView?.parent
        if (parent is FrameLayout) {
            (parent as FrameLayout).removeView(mIjkVideoView)
        }
        frameLayout.addView(mIjkVideoView)
        mIjkVideoView?.setUrl(infos.get(position).href)
        seekBar.max = mIjkVideoView?.duration?.toInt()!!
        mIjkVideoView?.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP)
        mIjkVideoView?.start()
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {

        } else {
            mIjkVideoView?.pause()
        }
    }

    override fun videoList(info: MutableList<VideoListBean.DataBean.InfoBean>) {
        infos.addAll(info)
        adapter?.notifyDataSetChanged()
    }

    override fun attent(position: Int) {
        this.isRelease = false
        presenter.attent(position,infos[position].userinfo.id)
    }

    override fun like(position: Int) {
        this.isRelease = false
        presenter.like(position,infos[position].id)
    }

    override fun attentSucc(position: Int, isattent: String?) {
//        this.isRelease = true
        infos[position].isattent = isattent
        adapter?.notifyDataSetChanged()
    }

    override fun likeSucc(position: Int, islike: String?, likes: String?) {
//        this.isRelease = true
        infos[position].islike = islike
        infos[position].likes = likes
        adapter?.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        mIjkVideoView?.pause()
    }

    override fun onResume() {
        super.onResume()
//        mIjkVideoView?.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mIjkVideoView?.release()
    }

    override fun createPresenter(): HomeVideoPresenter {
        return HomeVideoPresenter(mvpView)
    }

}
