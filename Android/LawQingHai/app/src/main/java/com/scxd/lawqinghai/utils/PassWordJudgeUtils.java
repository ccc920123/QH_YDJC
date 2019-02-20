/**
 * @文件名: PassWordJudgeUtils.java
 * @包名: scxd.cy.android.util
 * @描述:
 * @作者: MouTao
 * @创建日期: 2016年12月28日
 * @版本号: V1.0
 */
package com.scxd.lawqinghai.utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：修改校验工具
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */
public class PassWordJudgeUtils {
    private static String userName;
    private static String passWord;
    /**
     * 密码强度
     */
    private static int safeFlag = 0;

    public static int judgePassWord(Context context, String pass) {
        passWord = pass;
        return initJudge(context);
    }

    public static int judgePassWord(Context context, String user, String pass) {
        userName = user;
        passWord = pass;
        if (passWord.contains(userName)) {
            return 1;
        }
        return initJudge(context);
    }

    private static int initJudge(Context context) {
        if (passWord.length() < 6) {
            return 2;
        }
//不判断密码复杂度，直接判断长度大于等于6返回0
//        if (isContainDigtal()) {
//            safeFlag++;
//        }
//        if (isContainUpCase()) {
//            safeFlag++;
//        }
//        if (isContainLowerCase()) {
//            safeFlag++;
//        }
//        if (isContainSpecialChar()) {
//            safeFlag++;
//        }
//
//        if (safeFlag > 1) {
//            safeFlag = 0;
//            return 0;
//        } else {
//            safeFlag = 0;
//            return 3;
//        }
        return 0;
    }

    private static boolean isContainSpecialChar() {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(passWord);

        return m.find();
    }

    private static boolean isContainLowerCase() {
        String regEx = "[abcdefghijklmnopqrstuvwxyz]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(passWord);

        return m.find();
    }

    private static boolean isContainUpCase() {
        String regEx = "[ABCDEFGHIJKLMNOPQRSTUVWXYZ]";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(passWord);

        return m.find();
    }

    private static boolean isContainDigtal() {
        String regEx = "[0123456789]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(passWord);

        return m.find();

    }

}
