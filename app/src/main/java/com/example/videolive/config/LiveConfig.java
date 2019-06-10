package com.example.videolive.config;

import com.ksyun.media.streamer.encoder.VideoEncodeFormat;
import com.ksyun.media.streamer.framework.AVConst;
import com.ksyun.media.streamer.kit.StreamerConstants;

/**
 * Created by cxf on 2018/10/7.
 * 直播相关的参数配置
 */

public class LiveConfig {
    //推流参数配置
    public static final int PUSH_CAP_RESOLUTION = StreamerConstants.VIDEO_RESOLUTION_720P;//采集分辨率
    public static final int PUSH_PREVIEW_RESOLUTION = StreamerConstants.VIDEO_RESOLUTION_1080P;//直播预览分辨率
    public static final int LINK_MIC_PUSH_PREVIEW_RESOLUTION = StreamerConstants.VIDEO_RESOLUTION_720P;//连麦预览分辨率
    public static final int PUSH_VIDEO_RESOLUTION = StreamerConstants.VIDEO_RESOLUTION_720P;//推流分辨率
    public static final int PUSH_ENCODE_TYPE = AVConst.CODEC_ID_AVC;//H264
    public static final int PUSH_ENCODE_METHOD = StreamerConstants.ENCODE_METHOD_SOFTWARE;//软编
    public static final int PUSH_ENCODE_SCENE = VideoEncodeFormat.ENCODE_SCENE_SHOWSELF;//秀场模式
    public static final int PUSH_ENCODE_PROFILE = VideoEncodeFormat.ENCODE_PROFILE_LOW_POWER;//低功耗
    public static final int PUSH_FRAME_RATE = 15;//采集帧率
    public static final int PUSH_VIDEO_BITRATE = 800;//视频码率
    public static final int PUSH_AUDIO_BITRATE = 48;//音频码率
}
