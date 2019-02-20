package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * @类名: ${type_name}
 * @功能描述:   预警详情内容
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface WarnDetailsModel extends BaseModel {
    
    void loadDatas(Context context, String yjxh, MVPCallBack mvpCallBack);
    
    void loadCarDatas(Context context, String hpzl, String hphm, MVPCallBack mvpCallBack);
    
    
    void committ(Context context, Object object, MVPCallBack mvpCallBack);
}
