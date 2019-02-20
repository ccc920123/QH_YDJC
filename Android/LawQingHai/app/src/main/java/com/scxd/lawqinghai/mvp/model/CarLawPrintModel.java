package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.CarPrintBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CarLawPrintModel extends BaseModel {

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

    void queryWsbh(WSBHReqBean bean, MVPCallBack mvpCallBack);

    void queryDetail(String jdsbh,MVPCallBack mvpCallBack);

    void comitData(Context context, CarPrintBean bean, MVPCallBack mvpCallBack);

}
