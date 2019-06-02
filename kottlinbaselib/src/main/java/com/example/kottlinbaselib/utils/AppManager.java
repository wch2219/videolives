package com.example.kottlinbaselib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AppManager {
    private static Stack activityStack;

    private static AppManager instance;
    private static List<Activity> mActivities = new ArrayList<>();
    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }
        activityStack.add(activity);
        mActivities.add(activity);
    }

    public void finishArrActivity(Class<?> classS){
        for (Activity activity : mActivities) {
            if (activity.getClass() == classS) {
                activity.finish();
                mActivities.remove(activity);
                activity = null;
                return;
            }
        }

    }

    public  List<Activity> getAllActivity(){


        return mActivities;
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = (Activity) activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = (Activity) activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Object activity) {
        if (activity != null) {
            activityStack.remove(activity);
            ((Activity)activity).finish();
            activity = null;
        }
    }
    public void removeAvtivity(Activity activity){
        activityStack.remove(activity);
        mActivities.remove(activity);

    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Object activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }



    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                ((Activity)activityStack.get(i)).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
