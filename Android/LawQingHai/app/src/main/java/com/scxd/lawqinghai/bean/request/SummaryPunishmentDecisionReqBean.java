package com.scxd.lawqinghai.bean.request;

/**
 * 描述： 请求简易处决书bean
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/4
 * 修改人：
 * 修改时间：
 */


public class SummaryPunishmentDecisionReqBean {

    private String ksrq;
    private String jsrq;
    private String user;
    private String bmbh;
    private int page;
    private int pageSize;

    public SummaryPunishmentDecisionReqBean() {
    }

    public SummaryPunishmentDecisionReqBean(String ksqr, String jsrq,
                                            String user, String bmbh,
                                            int page, int pageSize) {
        this.ksrq = ksqr;
        this.jsrq = jsrq;
        this.user = user;
        this.bmbh = bmbh;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getKsqr() {
        return ksrq;
    }

    public void setKsqr(String ksqr) {
        this.ksrq = ksqr;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
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
