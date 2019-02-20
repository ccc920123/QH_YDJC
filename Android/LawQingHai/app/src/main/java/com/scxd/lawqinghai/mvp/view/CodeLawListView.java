package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.DriverLawListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeRspBean;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:      违法信息
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface CodeLawListView extends BaseView {

    /**
     * 查询违法信息
     * @param dataBean
     */
    void showCodeDatas(QueryCodeRspBean.DataBean dataBean);

}
