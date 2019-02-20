package com.stardon.lib.mainframeuilib.activity.impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类名: IBase
 * <br/>功能描述:功能接口
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/7
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public interface IBase {
    /**
     * 方法名称: createView
     * <br/>方法详述: 创建view，通过getContentLayout（）来获取layout文件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 方法名称: bindView
     * <br/>方法详述: 重写此方法来处理逻辑，如设置view的点击事件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    void bindView(Bundle savedInstanceState);

    /**
     * 方法名称: getView
     * <br/>方法详述: 返回view ，通过createView（）来得到view
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    View getView();

    /**
     * 方法名称: getContentLayout
     * <br/>方法详述: 提供布局文件，activity的布局文件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    int getContentLayout();


}
