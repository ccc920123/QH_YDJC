package com.scxd.beans.common;

import com.scxd.beans.pojo.BizRoadBlocks;

/**
 *  卡口备案 平台返回实体类
 */
public class RoadblockExtend extends BizRoadBlocks {

    private String fwzmc = "";//服务站名称

    private String bmmc = "";//部门名称

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
