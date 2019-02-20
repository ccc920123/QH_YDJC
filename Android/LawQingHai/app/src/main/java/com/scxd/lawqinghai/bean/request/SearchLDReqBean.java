package com.scxd.lawqinghai.bean.request;

/**
 * 描述：请求路段 类
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public class SearchLDReqBean {

    private String sjbmbh;

    private String dldm;

    public SearchLDReqBean(String sjbmbh, String dldm) {
        this.sjbmbh = sjbmbh;
        this.dldm = dldm;
    }

    public String getSjbmbh() {
        return sjbmbh;
    }

    public void setSjbmbh(String sjbmbh) {
        this.sjbmbh = sjbmbh;
    }

    public String getDldm() {
        return dldm;
    }

    public void setDldm(String dldm) {
        this.dldm = dldm;
    }
}
