package com.example.kottlinbaselib.utils.badgeicon;

import android.app.Application;
import android.app.Notification;
import androidx.annotation.NonNull;

public interface BadgeNumModel {
    Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception;
}
