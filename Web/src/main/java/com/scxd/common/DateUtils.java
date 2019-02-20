package com.scxd.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        if (StringUtil.isNotEmpty(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        } else {
            return new Date();
        }
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateORNULL(String strDate) {
        if (StringUtil.isNotEmpty(strDate) && !"-".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        } else {
            return null;
        }
    }

    public static Date strToDateORNULLDAY(String strDate) {
        if (StringUtil.isNotEmpty(strDate) && !"-".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        } else {
            return null;
        }
    }

    public static Date strToDateORNULLDAY2(String strDate) {
        if (StringUtil.isNotEmpty(strDate) && !"-".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            Calendar c = Calendar.getInstance();
            c.setTime(strtodate);
            c.add(Calendar.DAY_OF_MONTH, 1);            //利用Calendar 实现 Date日期+1天

            strtodate = c.getTime();
            return strtodate;
        } else {
            return null;
        }
    }

    public static String dateToStrMI(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    public static Object dateToStrDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    public static String dateToStrSS(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    public static String dateToStrSS2(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }

    /**
     * 判断时间是否在时间段内	 * @param nowTime	 * @param beginTime	 * @param endTime	 * @return
     */
    public  static  boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

}
