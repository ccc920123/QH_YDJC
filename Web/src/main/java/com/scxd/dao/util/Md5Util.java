package com.scxd.dao.util;

import com.scxd.beans.pojo.SysOpLogBean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenpan
 * @类名 :Md5Util.java
 * @功能描述:md5加密文件
 * @创建时间:2015-7-20
 */
public class Md5Util {

    public static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String MD5 = "MD5";

    private static String MD5_SUFFIX = "JDCCY";

    private Md5Util() {
    }

    /**
     * @param
     * @return
     * @throws IOException
     * @throws NullPointerException
     * @功能描述：字符串加密 <p>方法详述</P>
     */
    public static String md5(String s) {

        MessageDigest sMd5MessageDigest = null;
        try {
          //  获得MD5摘要算法的 MessageDigest 对象
            sMd5MessageDigest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sMd5MessageDigest.reset();
        try {
            //使用指定的字节更新摘要
            sMd5MessageDigest.update(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
//获得密文
        byte digest[] = sMd5MessageDigest.digest();

        StringBuilder sStringBuilder = new StringBuilder();
        //把密文转换成十六进制的字符串形式
        for (int i = 0; i < digest.length; i++) {
            final int b = digest[i] & 255;
            if (b < 16) {
                sStringBuilder.append('0');
            }
            sStringBuilder.append(Integer.toHexString(b));
        }

        String ss = sStringBuilder.toString();
        sStringBuilder.delete(0, sStringBuilder.length() - 1);
        return ss;
    }

    /**
     * 有key的加密
     * @param s
     * @return
     */
    public static String md5ForKey(String s) {
        return md5(s  + MD5_SUFFIX);
    }

    public static String md5ForFile(String path) throws Exception {
        InputStream fis;
        fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(md5.digest());
    }

    public static String md5ForFile(File file) {
        MessageDigest md5 = null;
        try {
            InputStream fis;
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            md5 = MessageDigest.getInstance(MD5);
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
        } catch (Exception e) {
            // TODO handle exception
            e.printStackTrace();
        }
        return toHexString(md5.digest());
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }


    /**
     * string+key加mi
     * @param string
     * @param key
     * @return
     */
    /*public static String md5(String string, String key) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + key).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/
}
