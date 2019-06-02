package com.example.kottlinbaselib.utils.badgeicon;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class LuncherUtils {
    public static final String UNABLE_TO_RESOLVE_INTENT_ERROR_ = "unable to resolve intent: ";

    private static LuncherUtils instance;

    public static LuncherUtils getInstance() {
        if (instance == null) {
            instance = new LuncherUtils();
        }
        return instance;
    }

    public boolean canResolveBroadcast(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> receivers = packageManager.queryBroadcastReceivers(intent, 0);
        return receivers != null && receivers.size() > 0;
    }

    public String getLaunchIntentForPackage(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
    }
}
