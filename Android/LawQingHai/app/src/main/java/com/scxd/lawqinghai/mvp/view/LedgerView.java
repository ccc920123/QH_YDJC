package com.scxd.lawqinghai.mvp.view;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.response.LedgerCarReqBean;
import com.scxd.lawqinghai.bean.response.LedgerDetailsRespBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface LedgerView  extends BaseView {

    /**
     * 车辆查询
     */
    void queryCar(LedgerQueyCarReqBean.DataBean bean);

    /**
     * 驾驶人查询
     */
    void queryDriver(Object bean);

    /**
     * 提交数据
     */
    void loadCommit(String id);

    void commitPhotos(String message);
    
    void showFailed(String message);
    
    
    void queryDatas(LedgerDetailsRespBean.DataBean bean);
    
}
