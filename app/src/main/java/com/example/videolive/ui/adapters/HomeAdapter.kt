package com.example.videolive.ui.adapters

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dueeeke.videoplayer.player.IjkVideoView
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.mvp.base.BaseRecyclerAdapter
import com.example.videolive.R
import com.example.videolive.model.bean.VideoBean



class HomeAdapter(context: Context, datas: MutableList<VideoBean>, layoutId: Int) :
    BaseRecyclerAdapter<VideoBean>(context, datas, layoutId) {


    override fun bindData(holder: CommonViewHolder, data: VideoBean, position: Int) {
        Glide.with(mContext!!)
            .load(data.thumb)
            .into(holder.getView(R.id.thumb))
    }
}