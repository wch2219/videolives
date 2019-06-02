package com.example.videolive.model.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.kottlinbaselib.utils.LogUtils;
import com.example.videolive.R;
import com.example.videolive.api.download.DownloadHelper;
import com.example.videolive.api.download.FileDownloadCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class GlideUtils {

    public static void show(Context context, ImageView imageView, Object url) {
        Glide.with(context).load(url)
                .apply(new RequestOptions())
                .into(imageView);
    }


    public static void show(Context context, ImageView imageView, Object url, RequestOptions options) {
        Glide.with(context).load(url)
                .apply(options)
                .into(imageView);
    }


    public static void showHead(Context context, ImageView imageView, Object url) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().error(R.mipmap.icon_userhead))
                .into(imageView);


    }
//    public static RequestOptions transform(){
//       return RequestOptions.bitmapTransform(new BlurTransformation(0,30));
//    }
    public static void url2Bitmap(Context context,String url,Url2bitmapPicListener url2bitmapPicListener){
        mUrl2bitmapPicListener = url2bitmapPicListener;
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(5);
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(context).asBitmap().load(url).apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        url2bitmapPicListener.transSucc(resource);
                    }
                });
    }

    public static void savePic(final Context context, Object url, SavePicListener savePicListener) {
        mSavePicListener = savePicListener;
        // 插入图库
        long time = new Date().getTime();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/YaopaiDown";
        final File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }


        Glide.with(context).asFile().load(url).into(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, Transition<? super File> transition) {
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), resource.getAbsolutePath(), resource.getName(), resource.getName());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 发送广播，通知刷新图库的显示
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + resource)));
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                if (mSavePicListener != null) {
                    mSavePicListener.saveSuccess(resource);
                }
            }

        });
    }

    public static void saveBitmap(final Context context, Bitmap bitmap, SavePicListener savePicListener){
        Bitmap mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mSavePicListener = savePicListener;
        // 插入图库
        long time = new Date().getTime();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/YaopaiDown";
        final File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String fileUrl = file.getAbsolutePath() +"/"+time+ ".png";
        try {
            FileOutputStream fos = new FileOutputStream(new File(fileUrl));
            //此处注意为Bitmap.CompressFormat.PNG
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            mSavePicListener.saveSuccess(new File(fileUrl));
            fos.flush();
            fos.close();
            Toast.makeText(context, "保存成功，请到相册查看", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downPic(final Context context, String url, SavePicListener savePicListener) {
        mSavePicListener = savePicListener;
        // 插入图库
        long time = new Date().getTime();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/YaopaiDown";
        final File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        // 初始化
        DownloadHelper mDownloadHelper = new DownloadHelper();

        mDownloadHelper.downloadFile(url, file.getAbsolutePath(), time + ".jpg", new FileDownloadCallback<File>() {
            @Override
            public void onDownLoadSuccess(File file) {
               LogUtils.Companion.I(file.getAbsoluteFile());
                File absoluteFile = file.getAbsoluteFile();
                try {
//                    MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
                    // 发送广播，通知刷新图库的显示
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + absoluteFile)));
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onDownLoadFail(Throwable e) {
                Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int progress, int total) {
                LogUtils.Companion.I(progress + ";" + total);
            }
        });
    }

    public interface SavePicListener {

        void saveSuccess(File file);
    }

     public interface Url2bitmapPicListener {

        void transSucc(Bitmap file);
    }



    private static SavePicListener mSavePicListener;
    private static Url2bitmapPicListener mUrl2bitmapPicListener;

}
