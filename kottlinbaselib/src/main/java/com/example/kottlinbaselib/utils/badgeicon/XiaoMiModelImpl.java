package com.example.kottlinbaselib.utils.badgeicon;

import android.app.Application;
import android.app.Notification;
import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class XiaoMiModelImpl implements BadgeNumModel {


    private static final String NOTIFICATION_ERROR = "Xiaomi phones must send notification";

    @Override
    public Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception {
        if (notification == null) {
            throw new Exception(NOTIFICATION_ERROR);
        }
        Field field = notification.getClass().getDeclaredField("extraNotification");
        Object extraNotification = field.get(notification);
        Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
        method.invoke(extraNotification, count);
        return notification;
    }
}
