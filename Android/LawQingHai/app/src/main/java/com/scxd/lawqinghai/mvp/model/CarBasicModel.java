package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CarBasicModel extends BaseModel{

    /**
     * Query Car Basic Information 
     * @param context
     * @param bean
     */
    void loadCarDatas(Context context, QueryCarRepBean bean, MVPCallBack mvpCallBack);

}
