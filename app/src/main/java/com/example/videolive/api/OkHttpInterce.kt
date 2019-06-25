package com.hg.kotlin.api

import android.content.Context
import com.example.kottlinbaselib.utils.LogUtils
import com.example.kottlinbaselib.utils.NetworkUtils
import com.example.kottlinbaselib.utils.SPUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.nio.charset.Charset

class OkHttpInterce : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response: Response

        val builder = request.newBuilder()

//        val requestBuilder = builder.method(request.method(), request.body())
        if (!NetworkUtils.isConnected()) {
            //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。0
            request = builder.cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        response = chain.proceed(request)

        /**
         * 设置缓存
         */
        if (NetworkUtils.isConnected()) {//有网络情况下，根据请求接口的设置，配置缓存。
            //这样在下次请求时，根据缓存决定是否真正发出请求。
//            val cacheControl = request.cacheControl().toString()
            //当然如果你想在有网络的情况下都直接走网络，那么只需要
            //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
            val maxAge = 60 * 60 // read from cache for 1 minute
            response = response.newBuilder()
                //                            .header("Cache-Control", cacheControl)
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        } else {
            //无网络
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            response = response.newBuilder()
                //                            .header("Cache-Control", "public,only-if-cached,max-stale=360000")
                .header("Cache-Control", "public,only-if-cached,max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }



        return response
    }

    companion object {
        private var mOnErrorCalBackListener: OnErrorCalBackListener? = null
        fun setOnErrorCalBackListener(onErrorCalBackListener: OnErrorCalBackListener) {
            mOnErrorCalBackListener = onErrorCalBackListener
        }

        val UTF8: Charset = Charset.forName("UTF-8")
        fun getErrorCode(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                val buffer = response.body()!!.source().buffer()
                var charset: Charset? = UTF8
                val contentType = response.body()!!.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                val contentLength = response.body()!!.contentLength()
                if (contentLength != 0L) {
                    val string = buffer.clone().readString(charset!!)
                    mOnErrorCalBackListener?.onError(response.code(), if (string.isNullOrEmpty()){response.message()}else string )
                }

                LogUtils.I(buffer.size())
                response
            }
        }

        /**
         * 拦截器Interceptors
         * 使用addHeader()不会覆盖之前设置的header,若使用header()则会覆盖之前的header
         *
         * @return
         */

        fun getRequestHeader(context: Context): Interceptor {
            return Interceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder()
//                builder.addHeader("User-Agent", getUserAgent(context))
                builder.addHeader("Accept", "application/json")
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded")
                val token = SPUtils.getString(ApiContents.Token)
//                if (!TextUtils.isEmpty(token)) {
//                    builder.addHeader("Authorization", "Bearer $token")
//                }
//                builder.addHeader("token", "Bearer")
                val requestBuilder = builder.method(originalRequest.method(), originalRequest.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }


        /**
         * 获取代理
         *
         * @return
         */
        fun getUserAgent(context: Context): String {

            var appVersion: String
            try {
                appVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName
            } catch (e: Exception) {
                appVersion = "1.0.0"
            }

            return "YAOPAI/Android $appVersion"
        }

        fun getLog(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }


}