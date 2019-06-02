package com.example.kottlinbaselib.utils;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.example.kottlinbaselib.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wang.avi.AVLoadingIndicatorView;

public class MyProgressLoading extends Dialog {

    private AVLoadingIndicatorView iv_loading;
    private Context mContext;
    public MyProgressLoading(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
//        lp.x = 100; // 新位置X坐标
//        lp.y = 100; // 新位置Y坐标
        lp.width = 300; // 宽度
        lp.height = 300; // 高度
        lp.alpha = 1.0f; // 透明度

        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_press);
        iv_loading = findViewById(R.id.iv_loading);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Uri uri = Uri.parse("http://img.huofar.com/data/jiankangrenwu/shizi.gif");
//
//        DraweeController draweeController =
//                Fresco.newDraweeControllerBuilder()
//
//                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
//                        .build();
//
//        iv_loading.setController(draweeController);
    }
}

