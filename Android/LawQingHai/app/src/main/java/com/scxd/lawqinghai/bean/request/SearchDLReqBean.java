package com.scxd.lawqinghai.bean.request;

/**
 * 描述：请求道路 类
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public class SearchDLReqBean {

    private String sjbmbh;

    private String dl;

    public SearchDLReqBean(String sjbmbh, String dl) {
        this.sjbmbh = sjbmbh;
        this.dl = dl;
    }

    public String getSjbmbh() {
        return sjbmbh;
    }

    public void setSjbmbh(String sjbmbh) {
        this.sjbmbh = sjbmbh;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }
}
