package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.activity.NotificationOfCompulsoryMeasuresActivity;
import com.scxd.lawqinghai.activity.NotificationOfCompulsoryMeasuresDetailActivity;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresDetailResBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDetailResBean;

import java.util.List;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public interface NotificationOfCompulsoryMeasuresDetailView extends BaseView {

    /**
     * 车辆查询
     */
    void backQueryCar(LedgerQueyCarReqBean.DataBean bean);

    /**
     * 驾驶人查询
     */
    void backQueryDriver(Object bean);

    /**
     * 查询强制详情
     * @param beanRes
     */
    void backQueryDetail(NotificationOfCompulsoryMeasuresDetailResBean beanRes);

    /**
     * 提交强制决定书数据 返回决定书编号和图片
     * @param bean
     */
    void backComitData(PrintImageResBean bean);


    /**
     * 查询违法信息
     * @param dataBean
     */
    void showCodeDatas(QueryCodeListBean dataBean);
    /**
     * 查询违法信息
     * @param dataBean
     */
    void showCodeItemDatas(QueryCodeListBean dataBean,NotificationOfCompulsoryMeasuresDetailActivity.ViewHolder holder);



    /**
     * 提交反馈信息
     * @param message
     */
    void commitBackDatas(String message);
    /**
     * 提交反馈照片
     * @param message
     */
    void commitBackPhotos(String message);

    /**
     *  查询道路和路段
     * @param bean
     */
    void backDL(List<DLRespBean> bean);

    void backLD(List<LDRespBean> bean);
    void getWsbh(String wsbh);
}
