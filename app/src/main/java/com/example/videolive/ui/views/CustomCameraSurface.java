package com.example.videolive.ui.views;

import android.app.Activity;
import android.content.Context;

import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.hardware.Camera;
public class CustomCameraSurface extends SurfaceView implements SurfaceHolder.Callback {
    static private CustomCameraSurface instance;
    private LinearLayout frame = null;
    private RelativeLayout innerFrame = null;
    private Camera camera;
    private SurfaceHolder previewHolder;
    private boolean inPreview = false;
    private boolean cameraConfigured = false;
    private Camera.Size size;

     public static CustomCameraSurface getInstance(Activity activity) {
        if (CustomCameraSurface.instance == null) {
            CustomCameraSurface.instance = new CustomCameraSurface(activity);
        }
        return CustomCameraSurface.instance;
    }

    public Camera onStart() {
        camera = Camera.open(1);
        if (size != null) {
            initPreview(size.width, size.height);
        }
        startPreview();
        return camera;
    }

    public void onStop() {
        if (inPreview && camera != null) {
            camera.stopPreview();
        }
        if (camera != null) camera.release();
        camera = null;
        inPreview = false;
        setVisibility(GONE);
    }

    private CustomCameraSurface(Context context) {
        super(context);
        initView();
    }

    public CustomCameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomCameraSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //初始化
    void initView() {
        previewHolder = getHolder();
        previewHolder.addCallback(this);
        previewHolder.setFormat(PixelFormat.TRANSPARENT);
        innerFrame = new RelativeLayout(getContext());
        innerFrame.addView(this);
        frame = new LinearLayout(getContext());
        frame.addView(innerFrame);
    }

    public LinearLayout getFrame() {
        return frame;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
        initPreview(width, height);
        startPreview();
    }

    public void surfaceCreated(SurfaceHolder holder) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // 记得销毁，释放相机
        onStop();
    }

    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }
        }
        this.size = result;
        return (result);
    }

    private void initPreview(int width, int height) {
        //利用布局，重设宽高来撑开界面，超出的部分直接撑出屏幕，至于为什么用这两个布局，自己去谷歌了！
        if (camera != null && previewHolder.getSurface() != null) {
            if (!cameraConfigured) {
                Camera.Parameters parameters = camera.getParameters();
                Camera.Size size = getBestPreviewSize(width, height, parameters);
                if (size != null) {
                    parameters.setPreviewSize(size.width, size.height);
                    camera.setParameters(parameters);
                    cameraConfigured = true;
                    // Setting up correctly the view
                    double ratio = size.height / (double) size.width;
                    int Y = getResources().getDisplayMetrics().heightPixels;
                    int X = getResources().getDisplayMetrics().widthPixels;
                    ViewGroup.LayoutParams params = innerFrame.getLayoutParams();
                    params.height = Y;
                    params.width = (int) (Y * ratio);
                    innerFrame.setLayoutParams(params);
                    int deslocationX = (int) (params.width / 2.0 - X / 2.0);
                    innerFrame.animate().translationX(-deslocationX);
                }
            }
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setDisplayOrientation(90);
            } catch (Throwable t) {
                //错误处理，自己做相应的逻辑
                setVisibility(GONE);
            }
        }
    }

    private void startPreview() {
        if (cameraConfigured && camera != null) {
            camera.startPreview();
            inPreview = true;
        }
    }
}

