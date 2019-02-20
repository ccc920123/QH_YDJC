package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CarLawListModel extends BaseModel{

    /**
     * Query Car Law List Information 
     * @param context
     * @param bean
     */
    void loadCarDatas(Context context, String hpzl, String hphm, int page, MVPCallBack mvpCallBack);

}
