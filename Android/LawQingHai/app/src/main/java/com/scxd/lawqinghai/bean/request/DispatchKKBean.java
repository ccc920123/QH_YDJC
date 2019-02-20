package com.scxd.lawqinghai.bean.request;

/**
 * 描述： 布控卡口请求类
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class DispatchKKBean {


    private String user;
    private String sjbmbh;
    private String ssbmbh;

    public String getSsbmbh() {
        return ssbmbh;
    }

    public void setSsbmbh(String ssbmbh) {
        this.ssbmbh = ssbmbh;
    }

    public String getSjbmbh() {
        return sjbmbh;
    }

    public void setSjbmbh(String sjbmbh) {
        this.sjbmbh = sjbmbh;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public DispatchKKBean(String user,String sjbmbh,String ssbmbh) {
        this.user = user;
        this.sjbmbh=sjbmbh;
        this.ssbmbh=ssbmbh;
    }

    public DispatchKKBean() {
    }
}
