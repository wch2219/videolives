package com.example.videolive.mvp.model;

import android.annotation.SuppressLint;
import com.example.kottlinbaselib.mvp.IModel;
import com.example.kottlinbaselib.utils.LogUtils;
import com.example.videolive.api.HttpManager;
import com.hg.kotlin.api.ApiServer;
import com.hg.kotlin.api.CustomObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Model implements IModel {

    public Model() {
        super();
    }

    @SuppressLint("CheckResult")
    public static <B>Observable<B> getObservable(Observable<B> observable, CustomObserver<B> customObserver) {

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(customObserver);

        return observable;
    }

    public static ApiServer getServer() {
        return HttpManager.getRequest(ApiServer.class);
    }

//    public static ApiServer getServerJson(){
//        return HttpManager.getRequestJson(ApiServer.class);
//    }



    public static RequestBody getRequestBody(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject(map);
        LogUtils.Companion.I( String.valueOf(jsonObject));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                String.valueOf(jsonObject));

        return requestBody;
    }

     public static RequestBody getRequestBody(String key,Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject(map);
        Map<String,String> map1 = new HashMap<>();
        map1.put(key,jsonObject.toString());
        jsonObject = new JSONObject(map1);
        LogUtils.Companion.I( String.valueOf(jsonObject));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                String.valueOf(jsonObject));

        return requestBody;
    }


}
