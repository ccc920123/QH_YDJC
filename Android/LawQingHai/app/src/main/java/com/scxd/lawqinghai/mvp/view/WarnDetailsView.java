package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.bean.response.WarnDetailsRspBean;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface WarnDetailsView extends BaseView {

    /**
     * 查询
     * @param bean
     */
    void showDatas(WarnDetailsRspBean.DataBean bean);
    
    /**
     * 查询机动车信息
     * @param bean
     */
    void showCarDatas(QueryCarBean.DataBean bean);
    
    
    
    /**
     * 签收
     */
    void commitDatas(String message);
    
    
    void Failed(String message);
}
