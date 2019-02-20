package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputDetailReqBan;
import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputReqBean;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresDetailReqBan;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

import java.util.List;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public interface ElectronicMonitoringInputDetailModelImp {


    /**
     * 车辆查询
     * @param context
     * @param hpzl
     * @param hphm
     * @param mvpCallBack
     */
    void queryCar(Context context, String hpzl, String hphm, MVPCallBack mvpCallBack);


    /**
     * 提交数据
     * @param context
     * @param bean
     * @param mvpCallBack
     */
    void loadCommit(Context context, ElectronicMonitoringInputDetailReqBan bean, MVPCallBack mvpCallBack);


    /**
     * 查询电子录入详情
     * @param cfjdsbh
     * @param mvpCallBack
     */
    void queryDetail(String cfjdsbh, MVPCallBack mvpCallBack);

    /**
     * 提交图片
     * @param context
     * @param url
     * @param user
     * @param yjxh
     * @param zpzl
     * @param beans
     * @param mvpCallBack
     */
    void commitPhoto(Context context, String url, String user, String yjxh, String zpzl, List<String> beans, MVPCallBack
            mvpCallBack);





    /**
     * 提交反馈信息
     * @param context
     * @param object
     * @param mvpCallBack
     */
    void commitBackDetails(Context context, WarnBackReqBean object, MVPCallBack mvpCallBack);
    /**
     * 提交反馈照片
     * @param context
     * @param mvpCallBack
     */
    void commitBackPhoto(Context context, String url, String user, String yjxh, String zpzl, List<String> beans, MVPCallBack
            mvpCallBack);
    /**
     * Query Law Information
     * @param context
     */
    void loadCodeDatas(Context context, String wfdm, MVPCallBack mvpCallBack);


    void searchDL(SearchDLReqBean bean,
                  MVPCallBack mvpCallBack);


    void searchLD(SearchLDReqBean bean,
                  MVPCallBack mvpCallBack);

    void queryWsbh(WSBHReqBean bean, MVPCallBack mvpCallBack);
}
