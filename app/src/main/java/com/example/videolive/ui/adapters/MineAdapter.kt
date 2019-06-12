package com.example.videolive.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.mvp.base.BaseRecyclerAdapter
import com.example.kottlinbaselib.utils.DensityUtil
import com.example.videolive.R
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.ui.activitys.PlayVideoActivity

class MineAdapter(context: Context, data: MutableList<VideoListBean.DataBean.InfoBean>, layoutId: Int) :
    BaseRecyclerAdapter<VideoListBean.DataBean.InfoBean>(context, data, layoutId) {

    override fun bindData(holder: CommonViewHolder, data: VideoListBean.DataBean.InfoBean, position: Int) {
        val imageView = holder.getView<ImageView>(R.id.iv_thumb)

        val metrics = mContext?.resources?.displayMetrics

        val widthPixels = metrics?.widthPixels

        val layoutParams = imageView.layoutParams
        layoutParams?.height = (widthPixels!! - DensityUtil.dp2px(20f))/3
        imageView.layoutParams = layoutParams
        GlideUtils.show(mContext, holder.getView(R.id.iv_thumb), data.thumb)
        holder.setOnClickListener(R.id.ll_root, View.OnClickListener {

            val intent = Intent(mContext,PlayVideoActivity::class.java)
            intent.putExtra("url",data.href)
            mContext?.startActivity(intent)
        })
    }
}