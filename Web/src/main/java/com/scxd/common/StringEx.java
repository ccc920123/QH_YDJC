package com.scxd.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Clob;

/**
 * 文件描述：字符串扩展类
 * 作者：齐遥遥
 * 时间：2017/2/9 17:56
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class StringEx {

    /**
     * 功能描述：将null转换成""
     * 作者：齐遥遥
     * 时间：2017/2/9 17:56
     */
    public static String nullToStr(Object str) {

        if(null == str) {
            return "";
        } else {
           return str.toString();
        }
    }
    /**
     * 功能描述：将null转换成""
     * 作者：齐遥遥
     * 时间：2017/2/9 17:56
     */
    public static String nullToStrUtf(Object str) {

        if(null == str) {
            return "";
        } else {
            try {
                return URLEncoder.encode(str.toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * 功能描述：将Null转换成"0"字符串返回
     * 作者：齐遥遥
     * 时间：2017-02-20
     * @param str 待转换字符串
     * @return 结果
     */
    public static String nullToZero(String str) {

        if(null == str || "".equals(str)) {
            return "0";
        } else {
            return str;
        }
    }

    /**
     * 功能描述：从ORACLE中的CLOB获取字符串
     * 作者：齐遥遥
     * 时间：2017-05-15
     * @param clob CLOB字段
     * @return CLOB转的字符串
     * @throws Exception 异常
     */
    public static String readCLOB(Clob clob) throws Exception {

        String str = "";

        if(null != clob) {
            str = clob.getSubString((long)1,(int)clob.length());
        }

        return str;
    }
}
