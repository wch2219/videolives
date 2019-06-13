package com.example.videolive.ui.views;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.example.kottlinbaselib.utils.LogUtils;

public class OnDoubleClick extends GestureDetector.SimpleOnGestureListener{
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        LogUtils.Companion.I("双击");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        LogUtils.Companion.I("单击");
        return true;
    }
}
