package com.scxd.lawqinghai.mvp.model;

import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public interface PassWordSetModel {
    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     * @param user
     */
    void setPassWordSet(String oldPwd, String newPwd, String user, String bmbh, MVPCallBack mvpCallBack);
}
