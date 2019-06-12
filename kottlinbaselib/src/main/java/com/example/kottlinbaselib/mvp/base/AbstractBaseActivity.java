package com.example.kottlinbaselib.mvp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kottlinbaselib.R;
import com.example.kottlinbaselib.mvp.presenter.IPresenter;
import com.example.kottlinbaselib.mvp.view.IView;
import com.example.kottlinbaselib.utils.*;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.jetbrains.annotations.Nullable;

/**
 * 父类 activity
 */
public abstract class AbstractBaseActivity<P extends IPresenter, V extends IView> extends AppCompatActivity implements
        ActivityMvpDelegateCallback<P, V>, IView {

    protected Context mContext;
    private MyProgressLoading mLoading;
    public ImmersionBar immersionBar;

    protected boolean isBlack;
    public AppManager mAppManager;
    public Bundle mBundle;
    private P mPresenter;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //注意要清除 FLAG_TRANSLUCENT_STATUS flag
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }else{
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//        }

        this.mBundle = savedInstanceState;
        //设置是否可以左滑返回，必须在super.onCreate（）之前
        if (getlayoutId()!=0) {
            setContentView(LayoutInflater.from(this).inflate(getlayoutId(),null,false));
        }

        mPresenter = createPresenter();
        mContext = this;
        mLoading = new MyProgressLoading(mContext, R.style.DialogStyle);
        mAppManager = AppManager.getAppManager();
        mAppManager.addActivity(this);
        initView();
        setImmBar();
        initData();
        initListener();
        HideUtil.init(this);
    }

    protected abstract @LayoutRes
    int getlayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected void initListener(){}
    @Override
    public void showLoading() {
        mLoading.show();

    }

    @Override
    public void dismissLoading() {
        mLoading.dismiss();
    }

    @Override
    public void showDialog(@Nullable String mess, int code) {

    }



    @Override
    public void onError(String string) {
        Message message = new Message();
        message.obj = string;
        message.what = 0;
        mHandler.sendMessage(message);
    }

    @Override
    public P createPresenter() {
        return null;
    }

    @Override
    public V getMvpView() {

        return (V) this;
    }

    @Override
    public void onAgainLogin() {
        mHandler.sendEmptyMessage(1);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(mContext,(String)msg.obj, Toast.LENGTH_SHORT).show();
                CookbarUtils.show(mContext,(String)msg.obj,false);
            }else {
                SPUtils.Companion.remove(BaseContents.Token);
               AppManager.getAppManager().finishAllActivity();
//               startActivity(new Intent(mContext,SelectIdentityActivity.class));
            }
        }
    };
    public void initRefreshLayout(SmartRefreshLayout refreshLayout) {
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
//        if (isLoadmore) {
//
//        } else {
//            refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
//        }
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setRefreshHeader(new WaterDropHeader(mContext));
        //refreshLayout.setRefreshHeader(new FalsifyHeader(mContext));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
                downOnRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore();
                loadMore();
            }
        });

    }

    /**
     * 下拉刷新
     */
    public void downOnRefresh() {

    }

    /**
     * 上拉加载更多
     */
    public void loadMore() {

    }

    @Override
    public P getPresenter() {


        return mPresenter;
    }

    // 写一个广播的内部类，当收到动作时，结束activity
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };

    public void register() {
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("base_finish_activity");
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }

    public void close() {
        Intent intent = new Intent();
        intent.setAction("base_finish_activity"); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
        finish();
    }

    public void setImmBar() {

        // 判断api版本号是否大于等于19
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
//状态栏变色后的颜色
        immersionBar = ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
//                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
                .statusBarAlpha(1.0f)  //状态栏透明度，不写默认0.0f
                .navigationBarAlpha(1.0f)  //导航栏透明度，不写默认0.0F
                .barAlpha(1.0f)  //状态栏和导航栏透明度，不写默认0.0f
                .statusBarDarkFont(isBlack)   //状态栏字体是深色，不写默认为亮色
                .fullScreen(false)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .setViewSupportTransformColor(toolbar) //设置支持view变色，支持一个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .statusBarView(view)  //解决状态栏和布局重叠问题
                .fitsSystemWindows(false)    //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
//                .statusBarColorTransform(R.color.buy_color)
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView()  //移除通过setViewSupportTransformColor()方法指定的view
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
            .keyboardEnable(true);
        immersionBar.init(); //必须调用方可沉浸式
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void finish() {
        HideUtil.hideSoftKeyboard(this);
        super.finish();
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {

        if (immersionBar != null) {
            immersionBar.removeSupportAllView();
            immersionBar.destroy();
        }
        HideUtil.init(this);
        AppManager.getAppManager().removeAvtivity(this);
        super.onDestroy();

    }

}
