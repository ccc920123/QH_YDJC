package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.LawDetailsRsp;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;

/**
 * @类名: ${type_name}
 * @功能描述:      违法信息详情
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface LawDetailsView extends BaseView {

    /**
     * 查询违法信息
     * @param dataBean
     */
    void showCodeDatas(LawDetailsRsp.DataBean dataBean);


    
}
