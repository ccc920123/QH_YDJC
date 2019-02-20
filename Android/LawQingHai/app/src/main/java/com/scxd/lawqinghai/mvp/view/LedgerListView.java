package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.LedgerListRespBean;
import com.scxd.lawqinghai.bean.response.WarnListBean;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface LedgerListView extends BaseView {
    
    void showDatas(LedgerListRespBean.DataBean bean);
    
    void showFailed(String message);
    
}
//jhfghfh