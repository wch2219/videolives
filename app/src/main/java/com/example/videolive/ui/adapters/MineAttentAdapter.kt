package com.example.videolive.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.mvp.base.BaseRecyclerAdapter
import com.example.kottlinbaselib.utils.DensityUtil
import com.example.videolive.R
import com.example.videolive.model.bean.VideoBean
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.ui.activitys.PlayVideoActivity
import com.example.videolive.ui.activitys.PoperInfoActivity
import kotlinx.android.synthetic.main.item_mine.view.*

class MineAttentAdapter(context: Context, data: MutableList<VideoBean>, layoutId: Int) :
    BaseRecyclerAdapter<VideoBean>(context, data, layoutId) {

    override fun bindData(holder: CommonViewHolder, data: VideoBean, position: Int) {

        GlideUtils.show(mContext, holder.getView(R.id.iv_head), data.thumb)
        holder.setOnClickListener(R.id.ll_root, View.OnClickListener {

            val intent = Intent(mContext, PoperInfoActivity::class.java)
            intent.putExtra("url",data.url)
            mContext?.startActivity(intent)
        })
    }
}