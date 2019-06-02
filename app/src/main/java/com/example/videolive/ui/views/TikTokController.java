package com.example.videolive.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.controller.MediaPlayerControl;
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.util.L;
import com.example.kottlinbaselib.utils.ButtonUtils;
import com.example.kottlinbaselib.utils.LogUtils;
import com.example.kottlinbaselib.weight.CircleImageView;
import com.example.videolive.R;
import com.example.videolive.model.utils.Contents;
import com.example.videolive.model.utils.GlideUtils;
import com.example.videolive.ui.views.popu.SharePopuWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 抖音
 * Created by xinyu on 2018/1/6.
 */

public class TikTokController extends BaseVideoController implements View.OnClickListener {

    private IjkVideoView ijkVideoView;
    private ViewHolder holder;
    private long currTime;
    private long starTime;
    private boolean isPlaying;
    public TikTokController(@NonNull Context context) {
        super(context);
    }

    public TikTokController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TikTokController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_tiktok_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        holder = new ViewHolder(mControllerView);
        holder.mRlAttent.setOnClickListener(this);
        holder.mTvLike.setOnClickListener(this);
        holder.mTvMessage.setOnClickListener(this);
        holder.mTvShare.setOnClickListener(this);
        GlideUtils.showHead(getContext(), holder.mIvHead, Contents.userHeader);
    }

    public void setIjkVideoView(IjkVideoView ijkVideoView) {
        this.ijkVideoView = ijkVideoView;

    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);

        LogUtils.Companion.I(playState+"");
        switch (playState) {
            case IjkVideoView.STATE_IDLE:
                isPlaying = false;
                L.e("STATE_IDLE");
                holder.mIvThumb.setVisibility(VISIBLE);
//                showPlay();
                currTime = 0;
                if (currThrend != null) {
                    currThrend.stop();
                    currThrend = null;
                }
                break;
            case IjkVideoView.STATE_PLAYING:
                L.e("STATE_PLAYING");
                isPlaying = true;
                holder.mIvThumb.setVisibility(GONE);
                holder.mImgPlay.setVisibility(GONE);
                if (currTime == 0) {
                    starTime = System.currentTimeMillis();

                }

                handler1.sendEmptyMessageDelayed(1,1000);


                break;
            case IjkVideoView.STATE_PREPARED:
                isPlaying = false;
                L.e("STATE_PREPARED");
                this.currTime = 0;
                if (currThrend != null) {
                    currThrend.stop();
                    currThrend = null;
                }
                break;
            case IjkVideoView.STATE_PAUSED:
                isPlaying = false;
                L.e("STATE_PREPARED");
                    showPlay();
                if (currThrend != null) {
                    currThrend.stop();
                    currThrend = null;
                }
                break;
            case IjkVideoView.STATE_BUFFERED:

                isPlaying = true;
                holder.mIvThumb.setVisibility(GONE);
                holder.mImgPlay.setVisibility(GONE);
                if (currTime == 0) {
                    starTime = System.currentTimeMillis();
                }

                handler1.sendEmptyMessageDelayed(1,20);

                break;
            default:
                isPlaying = false;
                if (currThrend != null) {
                        currThrend.stop();
                        currThrend = null;
                    }

                    break;

        }




    }

    @SuppressLint("HandlerLeak")
    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (onPlayProgressListener != null&&isPlaying) {
                onPlayProgressListener.progress(ijkVideoView.getCurrentPosition());
                sendEmptyMessageDelayed(1,20);
            }
        }
    };

    Thread currThrend = null;



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_attent:

                Toast.makeText(getContext(), "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_like:

                Toast.makeText(getContext(), "点赞", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message:

                Toast.makeText(getContext(), "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_share:

                    new SharePopuWindow(getContext())
                            .instance()
                            .showAtLocation(holder.mImgPlay, Gravity.CENTER,0,0);
                break;


        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                long currTime = System.currentTimeMillis();
                if (lastTouchTime == 0) {
                    isShowPlay = true;
                    lastTouchTime = currTime;
                }else {

                    if (currTime - lastTouchTime>400) {
                        isShowPlay = true;
                    }else {
                        isShowPlay = false;
                        handler.removeMessages(1);
                    }
                    lastTouchTime = currTime;
                }
                if (isShowPlay) {

                    handler.sendEmptyMessageDelayed(1,400);
                }

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                if (moveX - touchX>20) {
                    isShowPlay = false;
                    ijkVideoView.seekTo(ijkVideoView.getCurrentPosition()+200);
                }

                break;
        }

        return super.onTouchEvent(event);
    }

    private long lastTouchTime;
    private boolean isShowPlay;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (ijkVideoView != null&&isShowPlay) {
                lastTouchTime = 0;
                if (ijkVideoView.isPlaying()) {

                    showPlay();
                } else {
                    hintPlay();

                }
            }
        }
    };

    private void hintPlay() {

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_play_hint);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                holder.mImgPlay.setVisibility(GONE);
                ijkVideoView.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        holder.mImgPlay.startAnimation(animation);
    }

    private void showPlay() {

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_play_show);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                holder.mImgPlay.setVisibility(VISIBLE);
                ijkVideoView.pause();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        holder.mImgPlay.startAnimation(animation);
    }

    public ImageView getThumb() {
        return holder.mIvThumb;
    }

    private OnPlayProgressListener onPlayProgressListener;
    public interface OnPlayProgressListener{

        void progress(Long currTime);
    }


    public void setOnPlayProgressListener(OnPlayProgressListener onPlayProgressListener) {
        this.onPlayProgressListener = onPlayProgressListener;
    }

    private class ViewHolder {
        public View rootView;
        public ImageView mIvThumb;
        public CircleImageView mIvHead;
        public RelativeLayout mRlAttent;
        public TextView mTvLike;
        public TextView mTvMessage;
        public TextView mTvShare;
        public ImageView mImgPlay;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvThumb = (ImageView) rootView.findViewById(R.id.iv_thumb);
            this.mIvHead = (CircleImageView) rootView.findViewById(R.id.iv_head);
            this.mRlAttent = (RelativeLayout) rootView.findViewById(R.id.rl_attent);
            this.mTvLike = (TextView) rootView.findViewById(R.id.tv_like);
            this.mTvMessage = (TextView) rootView.findViewById(R.id.tv_message);
            this.mTvShare = (TextView) rootView.findViewById(R.id.tv_share);
            this.mImgPlay = (ImageView) rootView.findViewById(R.id.img_play);
        }

    }
}
