package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.PhotoReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:       预警反馈
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface WarnBackModel extends BaseModel {

    void loadDatas(Context context, String yjxh,String sjbmbh,  MVPCallBack mvpCallBack);

    
    void commit(Context context, WarnBackReqBean object, MVPCallBack mvpCallBack);
    
    void commitPhoto(Context context, String url, String user, String yjxh, String psr, List<String> beans, MVPCallBack 
            mvpCallBack);
}
