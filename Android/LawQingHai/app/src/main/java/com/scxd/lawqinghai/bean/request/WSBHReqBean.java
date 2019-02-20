package com.scxd.lawqinghai.bean.request;

/**
 * 描述：请求文书编号
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/8
 * 修改人：
 * 修改时间：
 */


public class WSBHReqBean {
    private  String bmbh;
    private  String user;
    private  String wslb;

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWslb() {
        return wslb;
    }

    public void setWslb(String wslb) {
        this.wslb = wslb;
    }
}
