package com.scxd.beans.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;
import java.util.UUID;

/**
 * 驾驶人违法记录返回实体类
 */
public class Q16Return {
    private String xh = "";
    private String hphm = "";
    private int fkje;
    private int wfjf;
    private Date wfsj;
    private String wfxw = "";
    private String wfdz = "";
    private String jkbj = "";
    private String wfzt = "";

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

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public int getFkje() {
        return fkje;
    }

    public void setFkje(int fkje) {
        this.fkje = fkje;
    }

    public int getWfjf() {
        return wfjf;
    }

    public void setWfjf(int wfjf) {
        this.wfjf = wfjf;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWfsj() {
        return wfsj;
    }

    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz;
    }

    public String getJkbj() {
        return jkbj;
    }

    public void setJkbj(String jkbj) {
        this.jkbj = jkbj;
    }
}
