package com.advantech.recruit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static SimpleDateFormat FORMAT_NORMAL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat FORMAT_NORMAL2 = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     *
     *
     * @Description 获取当前时间14位String<br>
     * @Author dongdong.jiang <br>
     * @Since 2018年7月2日 14:17:52 <br>
     * @Parameter <br>
     */
    public static String getCurrentTime(){
        return FORMAT_NORMAL.format(new Date());
    }
    public static String getCurrentTimeType2(){
        return FORMAT_NORMAL2.format(new Date());
    }


}
