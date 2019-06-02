package com.example.kottlinbaselib.mvp.base;


import com.example.kottlinbaselib.mvp.presenter.IPresenter;
import com.example.kottlinbaselib.mvp.view.IView;

public interface BaseDelegateCallback<P extends IPresenter,V extends IView>  {

    P getPresenter();

    P createPresenter();

    V getMvpView();
}
