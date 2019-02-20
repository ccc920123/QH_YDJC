package com.scxd.lawqinghai.bean.request;

/**
 * 描述：台账-查询车辆信息bean
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/19
 * 修改人：
 * 修改时间：
 */


public class LedgerDetailsBean {

    private String tzlsh;

    public String getTzlsh() {
        return tzlsh;
    }

    public void setTzlsh(String tzlsh) {
        this.tzlsh = tzlsh;
    }

    public LedgerDetailsBean(String tzlsh) {
        this.tzlsh = tzlsh;
    }
}
