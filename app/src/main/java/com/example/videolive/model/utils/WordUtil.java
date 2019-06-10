package com.example.videolive.model.utils;

import android.content.res.Resources;
import com.example.videolive.MyApplication;


import java.util.Objects;

/**
 * Created by cxf on 2017/10/10.
 * 获取string.xml中的字
 */

public class WordUtil {

    private static Resources sResources;

    static {
        sResources = Objects.requireNonNull(MyApplication.Companion.getSInstance()).getResources();
    }

    public static String getString(int res) {
        return sResources.getString(res);
    }
}
