package com.scxd.lawqinghai.bean.request;

/**
 * 描述：查询详情bean
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class SummaryPunishmentQueryReqBean {

    private String jdsbh;

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public SummaryPunishmentQueryReqBean(String jdsbh) {
        this.jdsbh = jdsbh;
    }
}
