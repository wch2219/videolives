package com.example.kottlinbaselib.utils.badgeicon;

import android.app.Application;
import android.app.Notification;
import androidx.annotation.NonNull;

public class MeizuModelImpl implements BadgeNumModel {
    private static final String NOTIFICATION_ERROR = "not support : meizu";

    @Override
    public Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception {
        if (true) {
            throw new Exception(NOTIFICATION_ERROR);
        }
        return null;
    }
}
