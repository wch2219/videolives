package com.example.videolive.ui.base;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.example.kottlinbaselib.mvp.base.AbstractBaseFragment;
import com.example.kottlinbaselib.mvp.presenter.IPresenter;
import com.example.kottlinbaselib.mvp.view.IView;
import com.example.kottlinbaselib.utils.AppManager;
import com.example.kottlinbaselib.utils.CookbarUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment<P extends IPresenter, V extends IView>  extends AbstractBaseFragment<P,V> {


    @Override
    public void onError(String string) {
        Message message = new Message();
        message.obj = string;
        mHandler.sendMessage(message);
        String[] array = {"", ""};
        List<String> list = new ArrayList<String>(array.length);
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
                AppManager.getAppManager().finishAllActivity();
//                startActivity(new Intent(mContext,SelectIdentityActivity.class).putExtra(Contents.HomeType,1));
            }
        }
    };
}
