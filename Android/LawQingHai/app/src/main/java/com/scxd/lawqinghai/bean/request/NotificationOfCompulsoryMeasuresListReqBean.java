package com.scxd.lawqinghai.bean.request;

import java.io.Serializable;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/10
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresListReqBean implements Serializable {


    private String wfxw;
    private String scz;
    private String bzz;


    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    public String getScz() {
        return scz;
    }

    public void setScz(String scz) {
        this.scz = scz;
    }

    public String getBzz() {
        return bzz;
    }

    public void setBzz(String bzz) {
        this.bzz = bzz;
    }


}
