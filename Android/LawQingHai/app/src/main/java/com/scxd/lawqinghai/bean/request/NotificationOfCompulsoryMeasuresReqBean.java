package com.scxd.lawqinghai.bean.request;

/**
 * 描述： 电子录入bean
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/4
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresReqBean {

    private String user;
    private String bmbh;
    private String ksrq;
    private String jsrq;
    private int page;
    private int pageSize;

    public NotificationOfCompulsoryMeasuresReqBean(String user, String bmbh,
                                                   String ksrq, String jsrq,
                                                   int page, int pageSize) {
        this.user = user;
        this.bmbh = bmbh;
        this.ksrq = ksrq;
        this.jsrq = jsrq;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getKsqr() {
        return ksrq;
    }

    public void setKsqr(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
