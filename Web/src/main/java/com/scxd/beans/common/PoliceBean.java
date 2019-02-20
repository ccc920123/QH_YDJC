package com.scxd.beans.common;

import com.scxd.beans.pojo.BizPoliceInfo;

/**
 * 平台扩展类
 */
public class PoliceBean extends BizPoliceInfo {

    private String fwzmc = "";//服务站名字
    private String bmmc = "";//部门名字

    public String getFwzmc() {
        return fwzmc;
    }

    public void setFwzmc(String fwzmc) {
        this.fwzmc = fwzmc;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }
}
