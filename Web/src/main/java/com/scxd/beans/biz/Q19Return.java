package com.scxd.beans.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

/**
 * 违法电子监控列表清单返回实体类
 */
public class Q19Return {
    private String jdsbh = "";
    private String hpzl = "";
    private String hphm = "";
    private String wfxw = "";
    private Date wfsj;

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWfsj() {
        return wfsj;
    }

    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    @Override
    public String toString() {
        return "Q19Return{" +
                "jdsbh='" + jdsbh + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", hphm='" + hphm + '\'' +
                ", wfxw='" + wfxw + '\'' +
                ", wfsj=" + wfsj +
                '}';
    }
}
