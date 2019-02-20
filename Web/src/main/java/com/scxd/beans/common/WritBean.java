package com.scxd.beans.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

/**
 * 文书管理返回平台实体类
 */
public class WritBean {
    private String id;
    private String xh;
    private String wsbh;
    private String glbm;
    private String wslb;
    private Date scsj;
    private String cjry;


    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh == null ?null:xh.trim();
    }

    public String getWsbh() {
        return wsbh;
    }

    public void setWsbh(String wsbh) {
        this.wsbh = wsbh == null ? null : wsbh.trim();
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm == null?null:glbm.trim();
    }

    public String getWslb() {
        return wslb;
    }

    public void setWslb(String wslb) {
       this.wslb = wslb==null?null:wslb.trim();
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getScsj() {
        return scsj;
    }

    public void setScsj(Date scsj) {
        this.scsj = scsj == null?null:scsj;
    }

    public String getCjry() {
        return cjry;
    }

    public void setCjry(String cjry) {
        this.cjry = cjry == null?null:cjry.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WritBean{" +
                "xh='" + xh + '\'' +
                ", wsbh='" + wsbh + '\'' +
                ", glbm='" + glbm + '\'' +
                ", wslb='" + wslb + '\'' +
                ", scsj=" + scsj +
                ", cjry='" + cjry + '\'' +
                '}';
    }
}
