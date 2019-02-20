package com.stardon.lib.videolib.activity.impl;

/**
 * 类名: CameraBase
 * <br/>功能描述: 拍照模块接口，用于定义需要暴露的方法，方便在后期调用该lib，同时也能处理逻辑。
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/10
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public interface VideoBase {


    /**
     * 方法名称: bindView
     * <br/>方法详述: 重写此方法来处理逻辑，可以自己调用父类暴露的方法
     * </br>请在该activity实现SavePhoneImp接口，方便得到拍照后的图片，以及其他可操作设置
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    void bindView();



}
