package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.LawListResp;

/**
 * @类名: ${type_name}
 * @功能描述:      法律园地
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface LawListView extends BaseView {

    /**
     * 查询
     * @param dataBean
     */
    void showCodeDatas(LawListResp.DataBean dataBean);

}
