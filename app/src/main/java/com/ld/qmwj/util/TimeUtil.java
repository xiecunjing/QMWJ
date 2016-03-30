package com.ld.qmwj.util;

import android.util.Log;

import com.ld.qmwj.Config;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 处理时间工具类
 * Created by zsg on 2016/3/5.
 */
public class TimeUtil {
    public static String getTimeStr(long oldTime, long currenttime) {

        long time = (currenttime - oldTime) / 1000;
        Log.d(Config.TAG, currenttime + "  " + oldTime + "时差：" + time);
        if (time >= 0 && time < 60) {
            return "刚才";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.format(oldTime);
        }
    }


    public static String getTimeStr2(long oldTime, long currenttime) {

        long time = (currenttime - oldTime) / 1000;
        if (time >= 0 && time < 60) {
            return "刚才";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            return sdf.format(oldTime);
        }
    }


    /**
     * 将秒数转化为时间
     *
     * @param second
     * @return
     */
    public static String getTimeBySecond(long second) {
        String s = "";
        if (second % 60 < 10)
            s += "0";
        s += second / 60 + ":";
        if (second % 60 > 9)
            s += second % 60;
        else
            s += "0" + second % 60;

        return s;
    }


    /**
     * 根据与当前时间的时差  得到不同格式的时间
     * 12：30   昨天12.30   前天12:30  2013/9/1 1330
     *
     * @param oldTime
     * @return
     */
    public static String getTimeStr2(long oldTime) {
        SimpleDateFormat format1 = new java.text.SimpleDateFormat("HH:mm");
        Date date = new Date(oldTime);

        Calendar current = Calendar.getInstance();      //默认初始值是当前时间

        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);        //将 小时 分钟 秒 置0  表示当天凌晨  比他大就是当天
        // 比他小且比昨天凌晨大就是昨天
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);      //将要比较的日期放入Calendar进行初始化

        if (current.after(today)) {
            //今天
            return format1.format(date);
        } else if (current.before(today) && current.after(yesterday)) {
            return "昨天 " + format1.format(date);
        } else {
            SimpleDateFormat format2 = new java.text.SimpleDateFormat("MM-dd HH:mm");
            return format2.format(date);
        }
    }


}
