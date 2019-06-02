package com.example.kottlinbaselib.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验
 */
public class Regularutiles {

    /**
     * 正则表达式:验证用户名(不包含中文和特殊字符)如果用户名使用手机号码或邮箱 则结合手机号验证和邮箱验证
     */
    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式:验证密码(不包含特殊字符)
     */
    private static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式:验证手机号
     */
    private static final String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$";

    /**
     * 正则表达式:验证邮箱
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式:验证汉字(1-9个汉字)  {1,9} 自定义区间
     */
    private static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,9}$";

    /**
     * 正则表达式:验证身份证
     */
    private static final String REGEX_ID_CARD = "\\d{15}|\\d{17}[0-9Xx]";

    /**
     * 正则表达式:验证URL
     */
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式:验证IP地址
     */
    private static final String REGEX_IP_ADDR = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
    /**
     * 手机号正则判断
     * @param phone
     * @return
     */
    public static boolean Photo(String phone){

        if (TextUtils.isEmpty(phone)) {

            return false;
        }
        String reg = REGEX_MOBILE;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(phone);
        boolean b = matcher.matches();

        return b;
    }

    /**
     * 验证身份证
     * @param id
     * @return
     */
    public static boolean IdCart(String id){
        String reg = "";
        Pattern pattern = Pattern.compile(REGEX_ID_CARD);
        Matcher matcher = pattern.matcher(id);
        boolean b = matcher.matches();

        return b;
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean Email(String email){
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 校验是否是汉字
     * @param str
     * @return
     */
    public static boolean chineseChar(String str){
        String regex = "^[\\u4e00-\\u9fa5]*$";
        Matcher m = Pattern.compile(regex).matcher(str);
        boolean matches = m.matches();

        return matches;
    }
}
