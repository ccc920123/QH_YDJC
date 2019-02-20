package com.scxd.lawqinghai.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_U {
    /**
     * 方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String data(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 方法输入所要转换的时间输入例如（"2014-06-14"）返回时间戳
     *
     * @param time
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static long datayyyyMMdd(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        long times = 0;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
//            String stf = String.valueOf(l);
            times = l;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 根据传递的类型格式化时间
     *
     * @return
     */
    public static String getDateTimeByMillisecond() {

        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

        String time = format.format(date);

        return time;
    }

    /**
     * 根据传递的类型格式化时间
     *
     * @return
     */
    public static String getDateTimeByMilli() {

        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = format.format(date);

        return time;
    }

    /**
     * 将字符串转为时间戳
     *
     * @param user_time
     * @return
     */
    public static String dateToTimestamp(String user_time) {
        if (TextUtils.isEmpty(user_time)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(user_time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    /**
     * @param
     * @return
     * @throws NullPointerException
     * @功能描述：将传入的时间字符串转化为固定格式的字符串
     */
    public static String TimestampToTimestamp(String user_time) {
        SimpleDateFormat sdfdata = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!TextUtils.isEmpty(user_time)) {
            //            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdfdata.parse(user_time);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return sdfdata.format(date);
        } else {
            return sdfdata.format(new Date().getTime());
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss转换成yyyy年MM月dd日 HH时mm分ss秒
     *
     * @param user_time
     * @return
     */
    public static String TimestampToTimestamp2(String user_time) {

        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(user_time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sfstr;

    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * @param
     * @return
     * @throws NullPointerException
     * @功能描述： <p>得到當前系統的hms</P>
     */
    public static String getCurrentTimeHuors() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return " " + sdf.format(new Date());
    }

    //时间戳转字符串
    public static String getStrTime(long timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeString = sdf.format(new Date(timeStamp));//单位秒
        return timeString;
    }

    public static String pictureName() {
        String str = "";
        Time t = new Time();
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month + 1;
        int date = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;
        if (month < 10)
            str = String.valueOf(year) + "0" + String.valueOf(month);
        else {
            str = String.valueOf(year) + String.valueOf(month);
        }
        if (date < 10)
            str = str + "0" + String.valueOf(date + "_");
        else {
            str = str + String.valueOf(date + "_");
        }
        if (hour < 10)
            str = str + "0" + String.valueOf(hour);
        else {
            str = str + String.valueOf(hour);
        }
        if (minute < 10)
            str = str + "0" + String.valueOf(minute);
        else {
            str = str + String.valueOf(minute);
        }
        if (second < 10)
            str = str + "0" + String.valueOf(second);
        else {
            str = str + String.valueOf(second);
        }
        return str;
    }


    public static String getDateTimeFromMillisecond(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String getDateTimeFromMillisecond2(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static boolean compareDate(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date3 = format.parse(date1);
            Date date4 = format.parse(date2);
            return compareDateByGetTime(date3, date4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param date1
     * @param date2
     * @Description: 用Date的getTime()方法
     */
    public static boolean compareDateByGetTime(Date date1, Date date2) {
        if (date1.getTime() < date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }
}