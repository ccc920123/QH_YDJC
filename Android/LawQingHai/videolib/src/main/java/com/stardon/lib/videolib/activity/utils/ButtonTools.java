package com.stardon.lib.videolib.activity.utils;
/**类名: ButtonTools
 * <br/>功能描述:判读按钮是否多次点击
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/8
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class ButtonTools {
    /**
     * 最后一次点击时间
     */
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}