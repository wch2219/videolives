package com.hg.kotlin.api

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

class CookieInter:CookieJar {
//    private var cookieStore = HashMap<HttpUrl, List<Cookie>>()
//    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
//        cookieStore[url] = cookies//保存cookie
//    }
//
//    override fun loadForRequest(url: HttpUrl): List<Cookie> {
//        val cookies = cookieStore[url]//取出cookie
//        return cookies ?: ArrayList()
//    }


    val  cache = mutableListOf<Cookie>()

    //Http请求结束，Response中有Cookie时候回调
    override fun  saveFromResponse(url:HttpUrl , cookies:MutableList<Cookie>) {
        //内存中缓存Cookie
        cache.addAll(cookies)
    }

    //Http发送请求前回调，Request中设置Cookie
    override
    fun loadForRequest(url:HttpUrl ):MutableList<Cookie> {
        //过期的Cookie
       val invalidCookies = mutableListOf<Cookie>()
        //有效的Cookie
       val validCookies = mutableListOf<Cookie>()

        for (cookie in cache) {

            if (cookie.expiresAt() < System.currentTimeMillis()) {
                //判断是否过期
                invalidCookies.add(cookie)
            } else if (cookie.matches(url)) {
                //匹配Cookie对应url
                validCookies.add(cookie)
            }
        }

        //缓存中移除过期的Cookie
        cache.removeAll(invalidCookies)

        //返回List<Cookie>让Request进行设置
        return validCookies
    }
}