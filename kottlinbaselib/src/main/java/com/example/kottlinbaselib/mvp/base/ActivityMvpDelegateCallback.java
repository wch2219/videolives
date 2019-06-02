package com.example.kottlinbaselib.mvp.base;


import com.example.kottlinbaselib.mvp.presenter.IPresenter;
import com.example.kottlinbaselib.mvp.view.IView;

public interface ActivityMvpDelegateCallback<P extends IPresenter,V extends IView>
        extends BaseDelegateCallback<P,V> {
}
