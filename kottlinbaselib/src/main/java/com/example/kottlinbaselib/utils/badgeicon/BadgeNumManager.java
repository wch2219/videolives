package com.example.kottlinbaselib.utils.badgeicon;

import android.app.Application;
import android.app.Notification;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class BadgeNumManager implements BadgeNumModel {

    private static final String NOT_SUPPORT_PHONE = "not support your phone [ Build.MANUFACTURER is null ]";
    private static final String NOT_SUPPORT_MANUFACTURER_ = "not support ";
    private static final String NOT_SUPPORT_LAUNCHER = "not support your launcher [ default launcher is null ]";
    private static final String NOT_SUPPORT_LAUNCHER_ = "not support ";
    private static Map<String, BadgeNumModel> iconBadgeNumModelMap;
    private static LauncherHelper launcherHelper;
    private static BadgeNumManager mBadgeNumManager;

    public static BadgeNumManager getInstance() {
        launcherHelper = new LauncherHelper();
        iconBadgeNumModelMap = new HashMap<>();
        mBadgeNumManager = new BadgeNumManager();
        return mBadgeNumManager;
    }

    @Override
    public Notification setIconBadgeNum(@NonNull Application context, Notification notification, int count) throws Exception {
        BadgeNumModel iconBadgeNumModel = getSetIconBadgeNumModel(context);
        return iconBadgeNumModel.setIconBadgeNum(context, notification, count);
    }

    /**
     * 根据手机类型获取相应Model
     *
     * @deprecated 判断当前launcher更加准确
     */
    @Deprecated
    @NonNull
    private BadgeNumModel getIconBadgeNumModelByManufacturer() throws Exception {
        if (TextUtils.isEmpty(Build.MANUFACTURER)) {
            throw new Exception(NOT_SUPPORT_PHONE);
        }
        switch (Build.MANUFACTURER.toLowerCase()) {
            case LauncherHelper.HUAWEI:
                return new GoogleModelImpl();
            case LauncherHelper.XIAOMI:
                return new XiaoMiModelImpl();
            case LauncherHelper.VIVO:
                return new VIVOModelImpl();
            case LauncherHelper.OPPO:
                return new OPPOModelImpl();
            case LauncherHelper.SAMSUNG:
                return new SamsungModelImpl();
            case LauncherHelper.MEIZU:
                return new MeizuModelImpl();
            case LauncherHelper.GOOGLE:
                return new GoogleModelImpl();
            default:
                throw new Exception(NOT_SUPPORT_MANUFACTURER_ + Build.MANUFACTURER);
        }
    }

    /**
     * 根据手机当前launcher获取相应Model
     */
    @NonNull
    private BadgeNumModel getIconBadgeNumModelByLauncher(@NonNull String launcherType) throws Exception {
        switch (launcherType) {
            case LauncherHelper.HUAWEI:
                return new HuaWeiModelImpl();
            case LauncherHelper.XIAOMI:
                return new XiaoMiModelImpl();
            case LauncherHelper.VIVO:
                return new VIVOModelImpl();
            case LauncherHelper.OPPO:
                return new OPPOModelImpl();
            case LauncherHelper.SAMSUNG:
                return new SamsungModelImpl();
            case LauncherHelper.MEIZU:
                return new MeizuModelImpl();
            case LauncherHelper.GOOGLE:
                return new GoogleModelImpl();
            default:
                throw new Exception(NOT_SUPPORT_LAUNCHER_ + launcherType);
        }
    }

    @NonNull
    private BadgeNumModel getSetIconBadgeNumModel(@NonNull Application context) throws Exception {
        String launcherName = launcherHelper.getLauncherPackageName(context);
        if (TextUtils.isEmpty(launcherName)) {
            throw new Exception(NOT_SUPPORT_LAUNCHER);
        }
        String launcherType = launcherHelper.getLauncherTypeByName(launcherName);
        if (TextUtils.isEmpty(launcherType)) {
            throw new Exception(NOT_SUPPORT_LAUNCHER_ + launcherName);
        }
        if (iconBadgeNumModelMap.containsKey(launcherType)) {
            return iconBadgeNumModelMap.get(launcherType);
        }
        BadgeNumModel iconBadgeNumModel = getIconBadgeNumModelByLauncher(launcherType);
        iconBadgeNumModelMap.put(launcherType, iconBadgeNumModel);
        return iconBadgeNumModel;
    }
}
