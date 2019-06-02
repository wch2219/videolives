package com.example.kottlinbaselib.mvp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.kottlinbaselib.R;
import com.example.kottlinbaselib.mvp.presenter.IPresenter;
import com.example.kottlinbaselib.mvp.view.IView;
import com.example.kottlinbaselib.utils.*;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

public abstract class AbstractBaseFragment<P extends IPresenter, V extends IView> extends Fragment implements
        ActivityMvpDelegateCallback<P, V>,IView {


    public Context mContext;

    private MyProgressLoading mLoading;
    private P mPresenter;
    private View mRootViewm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootViewm = inflater.inflate(getlayoutId(), null);

        mPresenter = createPresenter();
        mLoading = new MyProgressLoading(mContext, R.style.DialogStyle);


        return mRootViewm;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(mRootViewm);
        initData();
        initListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract int getlayoutId();


    protected abstract void initView(View rootView);

    protected abstract void initData();

    protected void initListener() {
    }

    @Override
    public P getPresenter() {
        return createPresenter();
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
    public void onError(String string) {
        Message message = new Message();
        message.obj = string;
        message.what = 0;
        mHandler.sendMessage(message);
    }

    @Override
    public void onAgainLogin() {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void showLoading() {
        mLoading.show();
    }

    @Override
    public void dismissLoading() {

            mLoading.dismiss();
    }

    @Override
    public void showDialog(@org.jetbrains.annotations.Nullable String mess, int code) {

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

        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        //refreshLayout.setRefreshHeader(new FalsifyHeader(mContext));
//        refreshLayout.autoRefresh();
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
        new ArrayList<String>();
    }

    /**
     * 上拉加载更多
     */
    public void loadMore() {

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }
}
