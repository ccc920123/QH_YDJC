package com.scxd.lawqinghai.mvp.view;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface WelcomeView extends BaseView {

    /**
     * 请求升级
     * @param obj
     */
    void upDataDownload(Object obj);

    /**
     * <br/> 方法名称: setVersionNumToView
     * <br/> 方法详述: 设置版本号到界面
     * <br/> 参数: version 版本号
     */
    void setVersionNumToView(String version);

    /**
     * <br/> 方法名称: showFailedMessage
     * <br/> 方法详述: 显示成功
     * <br/> 参数: failedMessage 失败信息
     */
    void showSuccessMessage(String Message);
    /**
     * 打开系统GPS
     */
    void openSystemGPS();
    /**
     * <br/> 方法名称: startGPSServiceSuccess
     * <br/> 方法详述: 开启定位服务成功后的回调
     * <br/> 参数: message(成功返回的信息)
     */
    void startGPSServiceSuccess(String message);


    /**
     * 下载字典成功
     */
    void  downLoadDictionaSuccess(String zdbb);

    void comitLogSueed(String pathFile);

    void comitLogError();
}
//jhfghfh