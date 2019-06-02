package com.example.videolive.ui.activitys


import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.Camera
import android.graphics.PixelFormat
import android.graphics.drawable.AnimationDrawable


import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.LogUtils
import com.example.videolive.R
import com.example.videolive.model.utils.CameraSizeUtils
import com.example.videolive.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_record_video.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * 录制视频
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class RecordVideoActivity : BaseActivity<BasePresenter<IView>, IView>(), SurfaceHolder.Callback {
    private var outfilePath: File? = null//录制输出路径
    private var mediaRecorder: MediaRecorder? = null
    private var startmilliTime: Long = 0
    private var videoFile: File? = null
    private val maxMillTime = 300//最大时间 秒
    private var mCamera: Camera? = null
    private var holder: SurfaceHolder? = null
    private val FRONT = 1//前置摄像头标记
    private val BACK = 2//后置摄像头标记
    private var currentCameraType = -1//当前打开的摄像头标记
    private var isRecorder: Boolean = false
    private var curmilliTime = 0
    override fun getlayoutId(): Int {

        return R.layout.activity_record_video
    }

    override fun initView() {
        outfilePath = createFile()
        // 选择支持半透明模式,在有surfaceview的activity中使用。
        window.setFormat(PixelFormat.TRANSLUCENT)
        holder = sfv_view.holder

        this.holder?.addCallback(this)
    }

    override fun initData() {
    }

    override fun initListener() {
        cb_start.setOnCheckedChangeListener(changeListener)
        iv_direction.setOnCheckedChangeListener(backListener)
    }

    private val changeListener = CompoundButton.OnCheckedChangeListener { _, b ->
        if (b) {
            start()
        } else {
            stop()
        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        holder = surfaceHolder
        if (holder?.getSurface() == null) {
            //检查SurfaceView是否存在
            return;
        }

        //改变设置前先关闭相机
        try {
            mCamera?.stopPreview();
        } catch (e: Exception) {
            e.printStackTrace();
        }
        try {

            val params = mCamera?.parameters
            val dm = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(dm)
            val screenWidth = dm.widthPixels
            val screenHeight = dm.heightPixels
            val preSize = getCloselyPreSize(true, width, height, params!!.supportedPreviewSizes)
            val  size = CameraSizeUtils.getBestPreviewSize(width,height,mCamera)
            params.set("orientation", "portrait")
            params.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
//            params.setPreviewSize(size.width, size.height)
            mCamera?.parameters = params
            mCamera?.startPreview()//该方法只有相机开启后才能调用
            mCamera?.setDisplayOrientation(90)
            mCamera?.setPreviewDisplay(holder)//整个程序的核心，相机预览的内容放在 holder
            mCamera?.cancelAutoFocus()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder?) {
        holder = surfaceHolder
        if (mCamera != null) {
            mCamera?.release()//释放相机资源
            mCamera = null
        }
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
        holder = surfaceHolder
        if (mCamera == null) {
            mCamera = Camera.open()//开启相机，可以放参数 0 或 1，分别代表前置、后置摄像头，默认为 0


        }
    }

    private val backListener = CompoundButton.OnCheckedChangeListener { _, b ->
        if (b) {
            currentCameraType = BACK
        } else {
            currentCameraType = FRONT
        }
        try {
            changeCamera()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun start() {
        try {
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
            val format = simpleDateFormat.format(Date().time)
            videoFile = File(outfilePath, format + ".mp4")
            LogUtils.I(videoFile!!)
            tv_upvideo.visibility = View.GONE
            mCamera?.unlock()
            mediaRecorder = MediaRecorder()
            mediaRecorder?.reset()
            mediaRecorder?.setCamera(mCamera)


            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.CAMCORDER)//采集声音
            mediaRecorder?.setVideoSource(MediaRecorder.VideoSource.SURFACE)//采集图像
            mediaRecorder?.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P))
//            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
//
//            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)//声音格式
//            mediaRecorder?.setVideoEncoder(MediaRecorder.VideoEncoder.H264)//图像编码格式
//            //设置录制的视频编码比特率
//            mediaRecorder?.setVideoEncodingBitRate(5*1024 * 1024)
//
//            mediaRecorder?.setVideoSize(1080, 720)
//                        mediaRecorder?.setVideoFrameRate(50);
            mediaRecorder?.setOutputFile(videoFile?.absolutePath)
            // 判断是前置摄像头还是后置摄像头 然后设置视频旋转 如果不加上 后置摄像头没有问题 但是前置摄像头录制的视频会导致上下翻转
            if (currentCameraType === FRONT) {
                mediaRecorder?.setOrientationHint(180)
            } else {
                mediaRecorder?.setOrientationHint(90)
            }
//            val params = mCamera?.parameters?.supportedPreviewSizes;
//            for (i in 0..params?.size!!){
//                Log.i("wch","width="+params.get(i).width+";height="+params.get(i).height)
//            }

            mediaRecorder?.setMaxDuration(maxMillTime * 1000)
            mediaRecorder?.setPreviewDisplay(holder?.surface)
            mediaRecorder?.prepare()
            isRecorder = true
            iv_direction.setVisibility(View.GONE)
            mediaRecorder?.start()
            progressBar.max = maxMillTime * 1000
            startanimation(cb_start)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        startmilliTime = SystemClock.currentThreadTimeMillis()
        StartProgress()

    }

    private fun stop() {
        if (mediaRecorder != null) {
            isRecorder = false
            mediaRecorder?.pause()
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
            val localUri = Uri.fromFile(videoFile)
            val localIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri)
            sendBroadcast(localIntent)
            val intent = Intent(mContext, VideoPreViewActivity::class.java)
            LogUtils.I(videoFile.toString())

            intent.putExtra("path", videoFile?.absoluteFile.toString())
            startActivity(intent)

        }

    }

    private fun StartProgress() {
        //开辟新的Thread用于定期刷新SeekBar;
        val dThread = DelayThread(100)
        dThread.start()
    }

    @Throws(IOException::class)
    private fun changeCamera() {
        mCamera?.stopPreview()
        mCamera?.release()
        if (currentCameraType == FRONT) {
            mCamera = openCamera(BACK)
        } else if (currentCameraType == BACK) {
            mCamera = openCamera(FRONT)
        }
        val params = mCamera?.parameters
        params?.set("orientation", "portrait")
        params?.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
        mCamera?.parameters = params
        mCamera?.cancelAutoFocus()
        mCamera?.setDisplayOrientation(90)
        mCamera?.setPreviewDisplay(holder)
        mCamera?.startPreview()

    }

    @SuppressLint("NewApi")
    private fun openCamera(type: Int): Camera? {
        var frontIndex = -1
        var backIndex = -1
        val cameraCount = Camera.getNumberOfCameras()
        val info = Camera.CameraInfo()
        for (cameraIndex in 0 until cameraCount) {
            Camera.getCameraInfo(cameraIndex, info)
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                frontIndex = cameraIndex
            } else if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                backIndex = cameraIndex
            }
        }

        currentCameraType = type
        if (type == FRONT && frontIndex != -1) {
            return Camera.open(frontIndex)
        } else if (type == BACK && backIndex != -1) {
            return Camera.open(backIndex)
        }
        return null
    }

    inner class DelayThread(internal var milliseconds: Int) : Thread() {

        override fun run() {
            while (isRecorder) {
                try {
                    curmilliTime += 100
                    Thread.sleep(milliseconds.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                handler.sendEmptyMessage(0)
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        @SuppressLint("SimpleDateFormat")
        override fun handleMessage(msg: Message) {
            progressBar.progress = curmilliTime
            val format = SimpleDateFormat("mm:ss")
            val date = Date().time
            val tiem = format.format(date)
            tv_time.text = tiem
            if (curmilliTime >= maxMillTime * 1000) {
                Toast.makeText(mContext, "录制完成", Toast.LENGTH_SHORT).show()
                stop()
            }
        }
    }

    private fun startanimation(view: View) {
        val drawable = view.background as AnimationDrawable

        if (!drawable.isRunning) {

            drawable.start()
        } else {
            drawable.stop()
        }
    }


    private fun createFile(): File {
        val sd = Environment.getExternalStorageDirectory()
        val path = sd.path
        val file = File(path)
        if (!file.exists()) {
            file.mkdir()
        }
        return sd
    }

    /**
     * 通过对比得到与宽高比最接近的预览尺寸（如果有相同尺寸，优先选择）
     *
     * @param isPortrait 是否竖屏
     * @param surfaceWidth 需要被进行对比的原宽
     * @param surfaceHeight 需要被进行对比的原高
     * @param preSizeList 需要对比的预览尺寸列表
     * @return 得到与原宽高比例最接近的尺寸
     */
    public fun getCloselyPreSize(
        isPortrait: Boolean,
        surfaceWidth: Int,
        surfaceHeight: Int,
        preSizeList: MutableList<Camera.Size>
    ): Camera.Size {
        var reqTmpWidth: Int
        var reqTmpHeight: Int;
        // 当屏幕为垂直的时候需要把宽高值进行调换，保证宽大于高
        if (isPortrait) {
            reqTmpWidth = surfaceHeight;
            reqTmpHeight = surfaceWidth;
        } else {
            reqTmpWidth = surfaceWidth;
            reqTmpHeight = surfaceHeight;
        }
        //先查找preview中是否存在与surfaceview相同宽高的尺寸
        for (size: Camera.Size in preSizeList) {
            if ((size.width == reqTmpWidth) && (size.height == reqTmpHeight)) {
                return size;
            }
        }

        // 得到与传入的宽高比最接近的size
        val reqRatio = (reqTmpWidth.toFloat()) / reqTmpHeight;
        var curRatio: Int
        var deltaRatio: Int
        var deltaRatioMin = Float.MAX_VALUE;
        var retSize: Camera.Size? = null;
        for (size: Camera.Size in preSizeList) {
            curRatio = (((size.width).toFloat()) / size.height).toInt();
            deltaRatio = Math.abs(reqRatio - curRatio).toInt();
            if (deltaRatio < deltaRatioMin) {
                deltaRatioMin = deltaRatio.toFloat();
                retSize = size;
            }
        }

        return retSize!!;
    }

    override fun onDestroy() {
        isRecorder = false
        if (mediaRecorder != null) {

            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
        }
        if (mCamera != null) {
            mCamera?.release()
            mCamera = null
        }
        super.onDestroy()
    }
}
