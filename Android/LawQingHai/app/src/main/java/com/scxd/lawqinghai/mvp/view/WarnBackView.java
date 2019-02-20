package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.WarnbackRspBean;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface WarnBackView extends BaseView {
    
    void showDatas(WarnbackRspBean.DataBean bean);
    
    void commitDatas(String message);
    
    void commitPhotos(String message);
    
    void showFailed(String message);
    
}
//jhfghfh