package ui.fragments


import android.annotation.TargetApi
import android.media.MediaPlayer
import android.os.Build
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dueeeke.videoplayer.player.IjkVideoView
import com.dueeeke.videoplayer.player.VideoViewManager
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.LogUtils
import com.example.videolive.R
import com.example.videolive.interfac.OnViewPagerListener
import com.example.videolive.model.bean.VideoBean
import com.example.videolive.model.utils.DataUtils
import com.example.videolive.ui.adapters.HomeAdapter
import com.example.videolive.ui.base.BaseFragment
import com.example.videolive.ui.views.TikTokController
import com.example.videolive.ui.views.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.activity_video_pre_view.*
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment<BasePresenter<IView>, IView>() {
    private var fragments: MutableList<Fragment> = mutableListOf()
    var adapter: HomeAdapter? = null
    var manager: ViewPagerLayoutManager? = null
    private var mCurrentPosition: Int = 0
    private var mIjkVideoView: IjkVideoView? = null
    private var mTikTokController: TikTokController? = null
    private var mVideoList: MutableList<VideoBean>? = mutableListOf()
    override fun getlayoutId(): Int {

        return R.layout.fragment_home
    }

    override fun initView(rootView: View) {
        initRefreshLayout(smartfresh)
        smartfresh.setEnableLoadMore(true)
        smartfresh.setEnableRefresh(false)
        manager = ViewPagerLayoutManager(mContext!!, OrientationHelper.VERTICAL)
        rv_list.layoutManager = manager


    }

    override fun initData() {
        mVideoList = DataUtils.getTikTokVideoList()
        adapter = HomeAdapter(mContext!!, mVideoList!!, R.layout.item_home)
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
    }

    override fun initListener() {
        manager?.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {
//自动播放第一条
                startPlay(0)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurrentPosition == position) {
                    mIjkVideoView?.release()
                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                if (mCurrentPosition == position) return
                startPlay(position)
                mCurrentPosition = position
            }
        })


    }

    private fun startPlay(position: Int) {
        val itemView = rv_list.getChildAt(0)
        val frameLayout = itemView.findViewById<FrameLayout>(R.id.container)
        Glide.with(this)
            .load(mVideoList?.get(position)?.thumb)

            .into(mTikTokController?.thumb!!)
        val parent = mIjkVideoView?.parent
        if (parent is FrameLayout) {
            (parent as FrameLayout).removeView(mIjkVideoView)
        }
        frameLayout.addView(mIjkVideoView)
        mIjkVideoView?.setUrl(mVideoList?.get(position)?.url)
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

    override fun onPause() {
        super.onPause()
        mIjkVideoView?.pause()
    }

    override fun onResume() {
        super.onResume()
        mIjkVideoView?.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mIjkVideoView?.release()
    }

}
