package com.scxd.lawqinghai.mvp.model;

import com.scxd.lawqinghai.bean.request.WriteDispatchBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public interface DispatchModel {

    void getDispatchsbkk(String user,String sjbmbh,String ssbmbh, MVPCallBack mvpCallBack);

    void getDispatchyjlx(String user, MVPCallBack mvpCallBack);

    void getDispatchcjry(String user, MVPCallBack mvpCallBack);

    void getWirteDispatch(WriteDispatchBean bean, MVPCallBack mvpCallBack);


}
