package com.scxd.beans.pojo;

/**
 * 执法站实体扩展类，用于平台详情查询
 */
public class BizQstationExtend extends BizQstationBean{
    private String glbmmc = "";//管理部门名称
    private String dlmc = "";//道路名称

    public String getGlbmmc() {
        return glbmmc;
    }

    public void setGlbmmc(String glbmmc) {
        this.glbmmc = glbmmc;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }
}
