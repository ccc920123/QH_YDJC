package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDetailResBean;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CarLawPrintView extends BaseView {
    /**
     * 车辆查询
     */
    void backQueryCar(LedgerQueyCarReqBean.DataBean bean);
    /**
     * 驾驶人查询
     */
    void backQueryDriver(Object bean);

    void getWsbh(String wsbh);
    
    /**
     * 查询驾驶人详情
     * @param beanRes
     */
    void backQueryDetail(SummaryPunishmentDetailResBean beanRes);
    
    void showDatas(PrintImageResBean bean);
}
