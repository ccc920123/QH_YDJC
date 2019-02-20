package com.scxd.beans.pojo;

import java.util.Date;

public class BizPoliceGps {
    private String id;

    private String gpsbh;

    private Short rylx;

    private String rybh;

    private Date sbsj;

    private String jd;

    private String wd;

    private String sd;

    private String fx;

    private Short scbj;

    private String sbyy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGpsbh() {
        return gpsbh;
    }

    public void setGpsbh(String gpsbh) {
        this.gpsbh = gpsbh == null ? null : gpsbh.trim();
    }

    public Short getRylx() {
        return rylx;
    }

    public void setRylx(Short rylx) {
        this.rylx = rylx;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh == null ? null : rybh.trim();
    }

    public Date getSbsj() {
        return sbsj;
    }

    public void setSbsj(Date sbsj) {
        this.sbsj = sbsj;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd == null ? null : jd.trim();
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd == null ? null : wd.trim();
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd == null ? null : sd.trim();
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx == null ? null : fx.trim();
    }

    public Short getScbj() {
        return scbj;
    }

    public void setScbj(Short scbj) {
        this.scbj = scbj;
    }

    public String getSbyy() {
        return sbyy;
    }

    public void setSbyy(String sbyy) {
        this.sbyy = sbyy == null ? null : sbyy.trim();
    }
}