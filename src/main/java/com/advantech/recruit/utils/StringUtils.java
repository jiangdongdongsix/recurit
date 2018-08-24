package com.advantech.recruit.utils;

/**
 * 字符串判断的工具类
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * 返回true代表字符串为空
     * 返回false代表字符串不为空
     * @param str
     * @return
     */
    public static Boolean isBlank(String str){
        if(str == null ||"".equals(str)){
            return true;
        }else{
            return false;
        }
    }
}
