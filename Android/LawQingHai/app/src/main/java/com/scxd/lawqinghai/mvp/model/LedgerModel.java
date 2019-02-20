package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.mvp.MVPCallBack;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   台账录入
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface LedgerModel extends BaseModel {

    /**
     * 车辆查询
     * @param context
     * @param hpzl
     * @param hphm
     * @param mvpCallBack
     */
    void queryCar(Context context, String hpzl, String hphm, MVPCallBack mvpCallBack);

    /**
     * 驾驶人查询
     * @param context
     * @param sfzh
     * @param mvpCallBack
     */
    void queryDriver(Context context, String sfzh, MVPCallBack mvpCallBack);

    /**
     * 提交数据
     * @param context
     * @param bean
     * @param mvpCallBack
     */
    void loadCommit(Context context, Object bean, MVPCallBack mvpCallBack);


    void commitPhoto(Context context, String url, String user, String yjxh, String psr, List<String> beans, MVPCallBack
            mvpCallBack);


    /**
     * 详情查询
     * @param context
     * @param sfzh
     * @param mvpCallBack
     */
    void queryDetails(Context context, String lsh, MVPCallBack mvpCallBack);
}
