package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.bean.response.QueryDriverBean;

/**
 * @类名: ${type_name}
 * @功能描述:      驾驶人基本信息
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface DriverBasicView extends BaseView {

    /**
     * 查询驾驶人基本信息
     * @param dataBean
     */
    void showDriverDatas(QueryDriverBean.DataBean dataBean);
    
    
    
    
}
