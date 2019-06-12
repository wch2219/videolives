package com.example.videolive.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.kottlinbaselib.holder.CommonViewHolder
import com.example.kottlinbaselib.mvp.base.BaseRecyclerAdapter
import com.example.videolive.R
import com.example.videolive.model.bean.FollowListBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.model.utils.GlideUtils
import com.example.videolive.ui.activitys.PoperInfoActivity

class MineAttentAdapter(context: Context, data: MutableList<FollowListBean.DataBean.InfoBean>, layoutId: Int) :
    BaseRecyclerAdapter<FollowListBean.DataBean.InfoBean>(context, data, layoutId) {

    override fun bindData(holder: CommonViewHolder, data: FollowListBean.DataBean.InfoBean, position: Int) {

        GlideUtils.showHead(mContext, holder.getView(R.id.iv_head), data.avatar)
        holder.setText(R.id.tv_nickname,data?.user_nicename)
        holder.setText(R.id.tv_signdesc,data?.signature)
        holder.setOnClickListener(R.id.ll_root, View.OnClickListener {

            val intent = Intent(mContext, PoperInfoActivity::class.java)
            intent.putExtra(Contents.TOUID,data.id)
            mContext?.startActivity(intent)
        })
    }
}