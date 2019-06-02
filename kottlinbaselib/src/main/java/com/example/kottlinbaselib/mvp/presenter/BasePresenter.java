package com.example.kottlinbaselib.mvp.presenter;


import com.example.kottlinbaselib.mvp.IModel;
import com.example.kottlinbaselib.mvp.view.IView;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> referenceView;
    private WeakReference<IModel> referenceModel;
    public BasePresenter(V view) {
        attachView(view);

    }

    @Override
    public void attachView(V view) {
        if (view == null)
            throw new NullPointerException("view can not be null when in attachview() in BasePresenter");
        else {
            if (referenceView == null)
                referenceView = new WeakReference<>(view);//将View置为弱引用，当view被销毁回收时，依赖于view的对象（即Presenter）也会被回收，而不会造成内存泄漏 }

        }

    }

    @Override
    public void attachModel(IModel model) {
        if (model == null)
            throw new NullPointerException("view can not be null when in attachview() in BasePresenter");
        else {
            if (referenceView == null)
                referenceModel = new WeakReference<>(model);//将View置为弱引用，当view被销毁回收时，依赖于view的对象（即Presenter）也会被回收，而不会造成内存泄漏 }

        }

    }



    @Override
    public void detachView() {
        if (referenceView != null) {
            referenceView.clear();
            referenceView = null;
        }


    }

    public V getMvpView() {
        if (isAttachView()) return referenceView.get();
        else throw new NullPointerException("have you ever called attachView() in BasePresenter");
    }


    public boolean isAttachView() {
        return referenceView != null && referenceView.get() != null;
    }







}
