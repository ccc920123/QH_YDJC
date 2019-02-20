package com.scxd;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.StringUtil;
import com.scxd.dao.util.Md5Util;
import com.szdt.security.des.DESUtil;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by hdfs on 2018/5/23.
 */
public class Test {

//    private final static String PRIVATE_KEY = "e3c64b1fe69e3f691ad8c7b5e4f12871abcbb4c4b74ccbf8";
    private final static String PRIVATE_KEY = "$2a$10$xbZGbbVR.HgdNgJ9hv.bCehfIxwG764uqASttBWes89pYR/6FUJNLO/8i";
    private static final String SECERT_KEY = "ics";
    public static void main(String args[]) throws Exception {
       boolean j= Verify();
    }

    /**
     * 验证
     */
    static boolean Verify() {
        boolean verify = false;
        String PRI_KEY = "scxdics";
        String wym = "860187044180888";
        String sjc ="1529047455943";
        String jkxlh = "";
        String key = wym + PRI_KEY + sjc;
        String keyMD5 = null;
        try {
            keyMD5 = EncoderByMd5(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (StringUtil.isNotEmpty(keyMD5) && keyMD5.equals(jkxlh)) {
            verify = true;
        } else {
            verify = false;
        }
        return verify;
    }
    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String newstr = Md5Util.md5(str);
        return newstr;
    }
}
