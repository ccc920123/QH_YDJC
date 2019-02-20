package com.scxd.lawqinghai.bean.request;

/**
 * 描述：查询处决书详情bean
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresQueryReqBean {

    private String cfjdsbh;

    public String getCfjdsbh() {
        return cfjdsbh;
    }

    public void setCfjdsbh(String cfjdsbh) {
        this.cfjdsbh = cfjdsbh;
    }

    public NotificationOfCompulsoryMeasuresQueryReqBean(String cfjdsbh) {
        this.cfjdsbh = cfjdsbh;
    }
}
