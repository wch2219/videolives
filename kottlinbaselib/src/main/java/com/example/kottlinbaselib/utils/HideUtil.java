package com.example.kottlinbaselib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class HideUtil {

    /**
     * Initialization method
     *
     * @param activity
     */
    public static void init(Activity activity) {
        new HideUtil(activity, null);
    }

    /**
     * Can pass the outer layout
     *
     * @param activity
     * @param content
     */
    public static void init(Activity activity, ViewGroup content) {
        new HideUtil(activity, content);
    }

    /**
     * Forced hidden keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        if (null == activity) {
            throw new RuntimeException("参数错误");
        }
        View view = activity.getCurrentFocus();
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public static void toggleSoftInput(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }
    /**
     * Forced hidden keyboard
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            throw new RuntimeException("参数错误");
        }
    }

    /**
     * Forced hidden keyboard
     *
     * @param dialog
     */
    public static void hideDialogSoftKeyboard(Dialog dialog) {
        if (null == dialog) {
            throw new RuntimeException("参数错误");
        }
        View view = dialog.getCurrentFocus();
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) dialog.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @param activity
     */
    private HideUtil(final Activity activity, ViewGroup content) {
        if (content == null) {
            content = (ViewGroup) activity.findViewById(android.R.id.content);
        }
        getScrollView(content, activity);
        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                dispatchTouchEvent(activity, motionEvent);

                return false;
            }
        });
    }

    private void getScrollView(ViewGroup viewGroup, final Activity activity) {
        if (null == viewGroup) {
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ScrollView) {
                ScrollView newDtv = (ScrollView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            } else if (view instanceof AbsListView) {
                AbsListView newDtv = (AbsListView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            } else if (view instanceof RecyclerView) {
                RecyclerView newDtv = (RecyclerView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            } else if (view instanceof ViewGroup) {
                this.getScrollView((ViewGroup) view, activity);
            }

            if (view.isClickable() && view instanceof TextView && !(view instanceof EditText)) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            }
        }
    }

    /**
     * @param mActivity
     * @param ev
     * @return
     */
    public boolean dispatchTouchEvent(Activity mActivity, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = mActivity.getCurrentFocus();
            if (null != v && isShouldHideInput(v, ev)) {
                hideSoftInput(mActivity, v.getWindowToken());
            }
        }
        return false;
    }

    /**
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v instanceof EditText) {
            Rect rect = new Rect();
            v.getHitRect(rect);
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param mActivity
     * @param token
     */
    private void hideSoftInput(Activity mActivity, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
