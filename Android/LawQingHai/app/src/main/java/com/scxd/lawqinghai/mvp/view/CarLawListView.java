package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.CarLawListBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:      机动车违法信息
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CarLawListView extends BaseView {

    /**
     * 查询车辆违法信息
     * @param dataBean
     */
    void showCarDatas(List<CarLawListBean> dataBean);


    
}
