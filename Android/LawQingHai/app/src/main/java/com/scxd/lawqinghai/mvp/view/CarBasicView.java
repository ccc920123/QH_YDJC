package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.QueryCarBean;

/**
 * @类名: ${type_name}
 * @功能描述:      机动车基本信息
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CarBasicView extends BaseView {

    /**
     * 查询车辆基本信息
     * @param dataBean
     */
    void showCarDatas(QueryCarBean.DataBean dataBean);
    
    
    
    
}
