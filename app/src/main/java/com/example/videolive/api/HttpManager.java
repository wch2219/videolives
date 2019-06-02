package com.example.videolive.api;

import android.content.Context;
import android.os.Handler;
import com.example.videolive.model.utils.Contents;
import com.hg.kotlin.api.ApiServer;
import com.hg.kotlin.api.CookieInter;
import com.hg.kotlin.api.OkHttpInterce;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class HttpManager {

    private static HttpManager mInstance;
    private static Retrofit retrofits;
    private static volatile ApiServer request = null;
    private Handler handler;
    private Retrofit.Builder retrofit;
    private Retrofit.Builder retrofitJson;
    private static Retrofit retrofitsJson;

    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }



    /**
     * 初始化必要对象和参数
     */
    public void init(Context context) {
        //创建Cache
        File httpCacheDirectory = new File(context.getExternalCacheDir(), "OkHttpCache");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        // 初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
//                .sslSocketFactory(HttpsUtils.getSSLCertifcation(context),HttpsUtils.UnSafeTrustManager)
//                .addInterceptor(OkHttpInterce.getLog())
                .addInterceptor(OkHttpInterce.Companion.getRequestHeader(context))
                .addInterceptor(OkHttpInterce.Companion.getErrorCode())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cookieJar(new CookieInter())
                .cache(cache)
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Contents.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        retrofits = retrofit.build();
    }
    public static ApiServer getRequest(Class<ApiServer> serverClass) {
        if (request == null) {
            synchronized (serverClass) {
                request = retrofits.create(serverClass);
            }
        }
        return request;
    }

//    /**
//     * 初始化必要对象和参数
//     */
//    public void initJson(Context context) {
//        //创建Cache
//        File httpCacheDirectory = new File(context.getExternalCacheDir(), "OkHttpCache");
//        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//
//        // 初始化okhttp
//        OkHttpClient client = new OkHttpClient.Builder()
////                .sslSocketFactory(HttpsUtils.getSSLCertifcation(context),HttpsUtils.UnSafeTrustManager)
//                .addInterceptor(OkHttpInterce.getLog())
//                .addInterceptor(OkHttpInterce.getRequestHeaderJson())
//                .addInterceptor(OkHttpInterce.getErrorCode())
//                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .cookieJar(new CookieInter())
//                .cache(cache)
//                .build();
//
//        // 初始化Retrofit
//        retrofitJson = new Retrofit.Builder()
//                .client(client)
//                .baseUrl(Constants.DEV_BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create());
//        retrofitsJson = retrofitJson.build();
//    }


//    public static ApiServer getRequestJson(Class<ApiServer> serverClass) {
//
//        if (request == null) {
//            synchronized (serverClass) {
//                request = retrofitsJson.create(serverClass);
//            }
//        }
//        return request;
//    }

    /**
     * 获取下载时使用 OkHttpClient
     *
     * @param interceptorArray
     * @return
     */
    public OkHttpClient getOkHttpClientDownload(Interceptor... interceptorArray) {
        final long timeout = 60;//超时时长
        final TimeUnit timeUnit = TimeUnit.SECONDS;//单位秒
        return getOkHttpClient(true, timeout, timeUnit, interceptorArray);
    }


    /**
     * 获取OkHttpClient
     * 备注:下载时不能使用OkHttpClient单例,在拦截器中处理进度会导致多任务下载混乱
     *
     * @param newClient        是否新建 OkHttpClient
     * @param timeout
     * @param interceptorArray
     * @return
     */
    public OkHttpClient getOkHttpClient(boolean newClient, long timeout, TimeUnit timeUnit, Interceptor... interceptorArray) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        /*if (newClient) {
            okHttpClient = new OkHttpClient.Builder();
        }*/
        //超时设置
        okHttpClient.connectTimeout(timeout, timeUnit)
                .writeTimeout(timeout, timeUnit)
                .readTimeout(timeout, timeUnit);

        /**
         * https设置
         * 备注:信任所有证书,不安全有风险
         */
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        okHttpClient.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        /**
         * 配置https的域名匹配规则，不需要就不要加入，使用不当会导致https握手失败
         * 备注:google平台不允许直接返回true
         */
        //okHttpClient.hostnameVerifier(new HostnameVerifier() {        });

        //Interceptor设置
        if (interceptorArray != null) {
            for (Interceptor interceptor : interceptorArray) {
                okHttpClient.addInterceptor(interceptor);
            }
        }
        return okHttpClient.build();
    }


    /**
     * 获取Retrofit
     *
     * @param baseUrl
     * @param client
     * @return
     */
    public Retrofit getRetrofit(String baseUrl, OkHttpClient client) {
        // Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return retrofit.build();
    }



}
