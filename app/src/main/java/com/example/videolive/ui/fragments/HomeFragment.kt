package ui.fragments


import android.annotation.TargetApi
import android.media.MediaPlayer
import android.os.Build
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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
import com.example.videolive.ui.fragments.HomeVideoFragment
import com.example.videolive.ui.fragments.LiveFragment
import com.example.videolive.ui.views.TikTokController
import com.example.videolive.ui.views.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.activity_video_pre_view.*
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment<BasePresenter<IView>, IView>(), RadioGroup.OnCheckedChangeListener {
    private var fragments: MutableList<Fragment> = mutableListOf()
    var transaction: FragmentTransaction? = null
    override fun getlayoutId(): Int {

        return com.example.videolive.R.layout.fragment_home
    }

    override fun initView(rootView: View?) {
        transaction = childFragmentManager.beginTransaction()
    }

    override fun initData() {
        fragments.add(HomeVideoFragment())
        fragments.add(LiveFragment())
        changeFragment(0)
        rg_group.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_video -> {
                changeFragment(0)
            }
            R.id.rb_live -> {
                changeFragment(1)
            }
        }
    }

    private var fragment: Fragment? = null

    private var lastFragment: Fragment? = null

    private fun changeFragment(i: Int) {

        transaction = childFragmentManager.beginTransaction()
        // 上一个不为空 隐藏上一个
        if (lastFragment != null && lastFragment !== fragments[i]) {
            //            transaction.hide(lastFragment);
            transaction!!.remove(lastFragment!!)
        }

        fragment = fragments[i]
        // fragment不能重复添加 // 添加过 显示 没有添加过 就隐藏
        if (fragment!!.isAdded) {
            transaction!!.show(fragment!!)
        } else {
            transaction!!.add(com.example.videolive.R.id.rl_layout, fragment!!)
        }
        transaction!!.commitAllowingStateLoss()
        lastFragment = fragment
    }
}
