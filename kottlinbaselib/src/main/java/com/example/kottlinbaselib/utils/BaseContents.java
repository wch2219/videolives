package com.example.kottlinbaselib.utils;

public class BaseContents {
    public static final String Token = "Token";
    public static final float TopHeight = 25;//沉浸式顶部距离
    public static final int Code_200 =  200	;//请求成功	接口成功执行会返回该状态码
    public static final int Code_201 = 201;//新增成功	新增数据成功会返回该状态码
    public static final int Code_400 =400;//请求失败	请求出现错误, 该错误一般是由于传递的参数错误导致的
    public static final int Code_401 =401;//	未授权	访问的接口需要授权, 即需要登录令牌 Token
    public static final int Code_403 =403;//	禁止访问	一般是由于 CORS 跨域认证失败导致的
    public static final int Code_404 =404;//数据未找到	即请求的数据不存在
    public static final int Code_405 =405;//不支持请求的方法	接口只支持 POST 请求
    public static final int Code_415 =415;//	不支持请求的媒体类型	POST 请求只支持 application/x-www-form-urlencoded
    public static final int Code_500 =500;//后端服务异常	后端服务存在 BUG
    public static final String IMAccoune = "imaccound";//网易登录账号
    public static final String IMToken = "imtoken";//网易登录成功 token
}