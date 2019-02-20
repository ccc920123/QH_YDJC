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
public interface MainModel extends BaseModel {


    /**
     * 获取服务的版本号
     *
     * @param context
     * @param mBack
     */
    void getSerViceVerDion(Context context, String bmbh, MVPCallBack mBack);

}