package com.scxd.beans.pdaBeans.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:23 2018/8/22
 * @Modified By:
 */
public class Q30ReturnBean {
    private String tzlsh = "";//台账流水号	varchar2	32	否
    private String fwzbh = "";//执法服务站编号	varchar2	18	否
    private String clzt = "";//车辆状态	varchar2	1	否	1-正常 2-无牌 3-套牌 4-假牌
    private String hpzl = "";//号牌种类	varchar2	2	否
    private String hphm = "";//号牌号码	varchar2	15	否
    private String jccllx = "";//	检查车辆类型	varchar2	2	否
    private Date jcsj;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getJcsj() {
        return jcsj;
    }

    public void setJcsj(Date jcsj) {
        this.jcsj = jcsj;
    }

    public String getTzlsh() {
        return tzlsh;
    }

    public void setTzlsh(String tzlsh) {
        this.tzlsh = tzlsh;
    }

    public String getFwzbh() {
        return fwzbh;
    }

    public void setFwzbh(String fwzbh) {
        this.fwzbh = fwzbh;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
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

    public String getJccllx() {
        return jccllx;
    }

    public void setJccllx(String jccllx) {
        this.jccllx = jccllx;
    }
}
