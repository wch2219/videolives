package com.example.videolive.ui.adapters

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.mvp.base.BaseRecyclerAdapter
import com.example.videolive.R
import com.example.videolive.model.bean.VideoListBean
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.ui.views.popu.SharePopuWindow


class HomeAdapter(context: Context, info: List<VideoListBean.DataBean.InfoBean>, layoutId: Int) :
    BaseRecyclerAdapter<VideoListBean.DataBean.InfoBean>(context, info, layoutId) {


    override fun bindData(holder: CommonViewHolder, data: VideoListBean.DataBean.InfoBean, position: Int) {
        Glide.with(mContext!!)
            .load(data.thumb)
            .into(holder.getView(R.id.thumb))
        GlideUtils.showHead(mContext,holder.getView(R.id.iv_head),data.userinfo.avatar)
        val iv_attent = holder.getView<ImageView>(R.id.iv_attent)
        val tv_like = holder.getView<TextView>(R.id.tv_like)
        if (data.isattent == "0") {
            iv_attent.setImageResource(R.mipmap.add_icon)
        }else{
            iv_attent.setImageResource(R.mipmap.icon_duigou)
        }
        tv_like.isSelected = data.islike=="1"

        tv_like.text = data.likes
        holder.setText(R.id.tv_message,data.comments)
        holder.setText(R.id.tv_share,data.shares)
        holder.setOnClickListener(R.id.tv_share, View.OnClickListener {
            SharePopuWindow(mContext)
                .instance()
                .showAtLocation(tv_like, Gravity.CENTER, 0, 0)
        })

          holder.setOnClickListener(R.id.rl_attent, View.OnClickListener {
              if (onClickListenter != null) {
                  onClickListenter!!.attent(position)
              }
        })

          holder.setOnClickListener(R.id.tv_like, View.OnClickListener {
              if (onClickListenter != null) {
                  onClickListenter!!.like(position)
              }
        })

    }




    public var onClickListenter: OnClickListenter? = null

    interface OnClickListenter {
        fun attent(position:Int)
        fun like(position:Int)
    }


}