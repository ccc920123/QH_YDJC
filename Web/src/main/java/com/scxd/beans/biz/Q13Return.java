package com.scxd.beans.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

/**
 * 机动车违法记录实体类
 */
public class Q13Return {
    private String xh = "";
    private String dsr = "";
    private Date wfsj ;
    private String wfdz = "";
    private String wfxw = "";
    private String wfzt = "";
    private String fkje = "";
    private String wfjf = "";

    public String getWfjf() {
        return wfjf;
    }

    public void setWfjf(String wfjf) {
        this.wfjf = wfjf;
    }

    public String getFkje() {
        return fkje;
    }

    public void setFkje(String fkje) {
        this.fkje = fkje;
    }

    public String getWfzt() {
        return wfzt;
    }

    public void setWfzt(String wfzt) {
        this.wfzt = wfzt;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWfsj() {
        return wfsj;
    }

    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    @Override
    public String toString() {
        return "Q13Return{" +
                "xh='" + xh + '\'' +
                ", dsr='" + dsr + '\'' +
                ", wfsj=" + wfsj +
                ", wfdz='" + wfdz + '\'' +
                ", wfxw='" + wfxw + '\'' +
                '}';
    }
}
