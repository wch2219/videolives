package com.example.kottlinbaselib.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.kottlinbaselib.R;


public class PermissionUtils {
    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码
    public static final int REQUEST_Location = 10112; //定位请求码
    public static final String CALL_PHONE= "";
    public static final int WriteAndRead = 10001;//读写请求码
    public static final int CACAMERA = 1003;

    /**
     * 判断是否有某项权限
     *
     * @param string_permission 权限
     * @param request_code      请求码
     * @return
     */
    public static boolean checkReadPermission(String[] string_permission, int request_code, Context context) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(context, string_permission[0]) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions((Activity) context, string_permission, request_code);
        }
        return flag;
    }

    /**
     * 引导设置 权限窗体
     * @param permissoncontent
     * @param context
     */
    public static void setPermission(@StringRes int permissoncontent, final Context context){
        //引导用户到设置中去进行设置
        new DialoghintUtils().init(context)
                .setAff(R.string.gosettion)
                .setClose(R.string.close)
                .setTitle(R.string.permisson)
                .setContent(permissoncontent)
                .setOnclick(new DialoghintUtils.ButtonOnClickListener() {
                    @Override
                    public void close(AlertDialog alertDialog) {
                        alertDialog.dismiss();
                    }

                    @Override
                    public void aff(AlertDialog alertDialog) {
                        new PermissionPageUtils(context).jumpPermissionPage();
                    }
                }).show();
    }


}
