package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.mvp.MVPCallBack;


/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface WelcomModel extends BaseModel {


    /**
     * <br/> 方法名称: getSystemvesion
     * <br/> 方法详述: 获取版本号
     * <br/> 参数:
     * <br/> &nbsp &nbsp &nbsp &nbsp context:上下文
     * <br/> &nbsp &nbsp &nbsp &nbsp mvpCallBack:回调接口
     */
    void getSystemVersion(Context context, MVPCallBack<String> mvpCallBack);

    /**
     * <br/> 方法名称: startLocationService
     * <br/> 方法详述: 启动GPS定位服务
     * <br/> 参数:
     * <br/> &nbsp &nbsp &nbsp &nbsp context:上下文
     * <br/> &nbsp &nbsp &nbsp &nbsp mvpCallBack:回调接口
     */
    void startLocationService(Context context, MVPCallBack mBack);

    /**
     * 获取服务的版本号
     *
     * @param context
     * @param mBack
     */
    void getSerViceVerDion(Context context, String bmbh, MVPCallBack mBack);

    /**
     * 获取字典
     *
     * @param bmbh
     * @param mBack
     */
    void getDictiona(String bmbh, MVPCallBack mBack);

    /**
     * 上传异常日志
     * @param wym
     * @param loginname
     * @param indata
     * @param mvpCallBack
     */
    void comitLog(String wym ,String loginname , String indata,MVPCallBack mvpCallBack);

}