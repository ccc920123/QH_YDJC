package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.ElectronicMonitoringInputResBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresResBean;

import java.util.List;

/**
 * 描述：电子监控录入
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/3
 * 修改人：
 * 修改时间：
 */


public interface ElectronicMonitoringInputView extends BaseView {
    /**
     * 返回查询据列表
     * @param obj
     */
    void backElectronicMonitoringInput(ElectronicMonitoringInputResBean.DataBean obj);

}
