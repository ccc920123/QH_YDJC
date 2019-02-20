package com.stardon.lib.mainframeuilib.activity.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**类名: LayoutInflaterUtil
 * <br/>功能描述:加载界面类
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/7
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public class LayoutInflaterUtil {

    private static LayoutInflater inflater;
    /**方法名称: inflate
    * <br/>方法详述: 加载器
    * <br/>参数: context  res资源文件layout
    * <br/>返回值: LayoutInflater
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

    public static View inflate(Context context, int res){
        if(inflater==null) {
            inflater = LayoutInflater.from(context);
        }

        return inflater.inflate(res,null);
    }

}
