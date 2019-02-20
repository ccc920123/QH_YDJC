package com.scxd.beans.pdaBeans.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 15:38 2018/6/12
 * @Modified By:
 */
public class PdaResponse {
    private String loginname;
    private String xm;
    @JsonIgnore
    private String password;
    private String bmmc;
    private String yhqx;
    private  String ssbmbh;
    private  String sjbmbh;
    @JsonIgnore
    private int sfsypda;
    @JsonIgnore
    private int zt;

    private  String sgdj;
    private  String fwzbh;

    public String getFwzbh() {
        return fwzbh;
    }

    public void setFwzbh(String fwzbh) {
        this.fwzbh = fwzbh;
    }

    public String getSgdj() {
        return sgdj;
    }

    public void setSgdj(String sgdj) {
        this.sgdj = sgdj;
    }

    public String getSsbmbh() {
        return ssbmbh;
    }

    public void setSsbmbh(String ssbmbh) {
        this.ssbmbh = ssbmbh;
    }

    public String getSjbmbh() {
        return sjbmbh;
    }

    public void setSjbmbh(String sjbmbh) {
        this.sjbmbh = sjbmbh;
    }

    public int getZt() {
        return zt;
    }

    public void setZt(int zt) {
        this.zt = zt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getYhqx() {
        return yhqx;
    }

    public void setYhqx(String yhqx) {
        this.yhqx = yhqx;
    }

    public int getSfsypda() {
        return sfsypda;
    }

    public void setSfsypda(int sfsypda) {
        this.sfsypda = sfsypda;
    }
}
