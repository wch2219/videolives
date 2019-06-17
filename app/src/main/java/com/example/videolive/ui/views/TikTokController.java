package com.example.videolive.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.util.L;
import com.example.kottlinbaselib.utils.LogUtils;
import com.example.videolive.R;
import com.example.videolive.ui.views.popu.SharePopuWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 抖音
 * Created by xinyu on 2018/1/6.
 */

public class TikTokController extends BaseVideoController implements View.OnClickListener, GestureDetector.OnGestureListener,SeekBar.OnSeekBarChangeListener {

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
            holder.seekBar .setOnSeekBarChangeListener(this);
    //        holder.mRlAttent.setOnClickListener(this);
    //        holder.mTvLike.setOnClickListener(this);
    //        holder.mTvMessage.setOnClickListener(this);
    //        holder.mTvShare.setOnClickListener(this);
    //        GlideUtils.showHead(getContext(), holder.mIvHead, Contents.userHeader);

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

            if (onPlayProgressListener != null&&isPlaying&& !isonTouchSeek) {

                long currentPosition = ijkVideoView.getCurrentPosition();
                long duration = ijkVideoView.getDuration();
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                String format = dateFormat.format(new Date(currentPosition));
                String durationformat = dateFormat.format(new Date(duration));
                LogUtils.Companion.I(format);
                LogUtils.Companion.I(durationformat);

                float aa = ((float)ijkVideoView.getCurrentPosition()/(float) ijkVideoView.getDuration());
                LogUtils.Companion.I(aa);
                holder.seekBar.setProgress((int) (ijkVideoView.getCurrentPosition()/ijkVideoView.getDuration()*100));

                onPlayProgressListener.progress(ijkVideoView.getCurrentPosition());

                sendEmptyMessageDelayed(1,20);
            }else {
                sendEmptyMessageDelayed(1,20);
            }
        }
    };



    Thread currThrend = null;
    private boolean isonTouchSeek = false;
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isonTouchSeek = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isonTouchSeek = false;
//        seekBar.setProgress(seekBar.getProgress());
        int pos = (int) (seekBar.getProgress()/100*ijkVideoView.getDuration());
        LogUtils.Companion.I(seekBar.getProgress());
        LogUtils.Companion.I(pos);
        ijkVideoView.seekTo(pos);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_attent:
                if (onClickListenter != null) {
                    onClickListenter.attent();
                }

                break;
            case R.id.tv_like:
                if (onClickListenter != null) {
                    onClickListenter.like();
                }

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
    private   OnClickListenter onClickListenter;
    public   interface OnClickListenter{
        void attent();
        void like();
    }

    public void setOnClickListenter(OnClickListenter onClickListenter) {
        this.onClickListenter = onClickListenter;
    }
    long downTime = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = 0;

        long upTime;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();


//                touchX = event.getX();


                break;
            case MotionEvent.ACTION_UP:
//                upTime = System.currentTimeMillis();
//                LogUtils.Companion.I(upTime);
//                if ((upTime - downTime)<100&& upTime -downTime>20) {
//                    if (ijkVideoView.isPlaying()) {
//
//                        showPlay();
//                    } else {
//                        hintPlay();
//
//                    }
//                }

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
            case MotionEvent.ACTION_MOVE:
//                float moveX = event.getX();
//                if (moveX - touchX>20) {
//                    isShowPlay = false;
//                    ijkVideoView.seekTo(ijkVideoView.getCurrentPosition()+200);
//                }

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
                holder.seekBar.setVisibility(GONE);
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
                holder.seekBar.setVisibility(VISIBLE);
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
        public Love2 love2;
//        public CircleImageView mIvHead;
//        public RelativeLayout mRlAttent;
//        public TextView mTvLike;
//        public TextView mTvMessage;
//        public TextView mTvShare;
        public ImageView mImgPlay;
        public SeekBar seekBar;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvThumb = (ImageView) rootView.findViewById(R.id.iv_thumb);
//            this.mIvHead = (CircleImageView) rootView.findViewById(R.id.iv_head);
//            this.mRlAttent = (RelativeLayout) rootView.findViewById(R.id.rl_attent);
//            this.mTvLike = (TextView) rootView.findViewById(R.id.tv_like);
//            this.mTvMessage = (TextView) rootView.findViewById(R.id.tv_message);
//            this.mTvShare = (TextView) rootView.findViewById(R.id.tv_share);
            this.mImgPlay = (ImageView) rootView.findViewById(R.id.img_play);
            this.seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
//            this.love2 = (Love2) rootView.findViewById(R.id.love);
        }
    }

    // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
    @Override
    public boolean onDown(MotionEvent e) {
        System.out.println("onDown");
        return false;
    }

    /*
     * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
     * 注意和onDown()的区别，强调的是没有松开或者拖动的状态 (单击没有松开或者移动时候就触发此事件，再触发onLongPress事件)
     */
    @Override
    public void onShowPress(MotionEvent e) {
        System.out.println("onShowPress");
    }

    // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.println("onSingleTopUp");
        return false;
    }

    // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        System.out.println("onScroll");
        return false;
    }

    // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
    @Override
    public void onLongPress(MotionEvent e) {
        System.out.println("onLongPress");
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
