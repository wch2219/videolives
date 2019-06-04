package ui.fragments


import android.annotation.TargetApi
import android.media.MediaPlayer
import android.os.Build
import android.view.View
import android.widget.*
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

    override fun getlayoutId(): Int {

        return R.layout.fragment_home
    }

    override fun initView(rootView: View?) {
        var transaction = childFragmentManager.beginTransaction()
    }

    override fun initData() {
        fragments.add(HomeVideoFragment())
        fragments.add(LiveFragment())
        rg_group.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            R.id.rb_video ->{

            }
            R.id.rb_live -> {

            }
        }
    }
}
