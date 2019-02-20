package com.scxd.beans.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateYearSerializer;

import java.util.Date;

public class Q10Return {
    private String xm="";
    private String lxdh="";
    private String jszzt="";
    private Date cclzrq;
    private String sfcf="";
    private String yqwsy="";
    private String yqwhz="";
    private String fzjg="";
    private String zjcx="";
    private String lxzsxxdz="";
    private String sfzmc="";
    private Date csrq;
    private String dabh="";
    private String sfzmhm="";
    private String zsxzqy="";
    private String jzqx="";
    private Date zhqfrq;
    private int ljjf;
    private Date zhmfqfrq;
    private Date xyqfrq;
    private Date xytjrq;
    private String djzsxxdz="";
    private String zxbh="";
    private String xb="";
    private String zpid="";
    private String xzqh="";

    private  Date yxqs;
    private  Date yxqz;

    @JsonSerialize(using = CustomDateYearSerializer.class)public Date getYxqs() {
        return yxqs;
    }

    public void setYxqs(Date yxqs) {
        this.yxqs = yxqs;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getYxqz() {
        return yxqz;
    }


    public void setYxqz(Date yxqz) {
        this.yxqz = yxqz;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getCclzrq() {
        return cclzrq;
    }

    public void setCclzrq(Date cclzrq) {
        this.cclzrq = cclzrq;
    }

    public String getSfcf() {
        return sfcf;
    }

    public void setSfcf(String sfcf) {
        this.sfcf = sfcf;
    }

    public String getYqwsy() {
        return yqwsy;
    }

    public void setYqwsy(String yqwsy) {
        this.yqwsy = yqwsy;
    }

    public String getYqwhz() {
        return yqwhz;
    }

    public void setYqwhz(String yqwhz) {
        this.yqwhz = yqwhz;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getLxzsxxdz() {
        return lxzsxxdz;
    }

    public void setLxzsxxdz(String lxzsxxdz) {
        this.lxzsxxdz = lxzsxxdz;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getJzqx() {
        return jzqx;
    }

    public void setJzqx(String jzqx) {
        this.jzqx = jzqx;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getZhqfrq() {
        return zhqfrq;
    }

    public void setZhqfrq(Date zhqfrq) {
        this.zhqfrq = zhqfrq;
    }

    public int getLjjf() {
        return ljjf;
    }

    public void setLjjf(int ljjf) {
        this.ljjf = ljjf;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getXyqfrq() {
        return xyqfrq;
    }

    public void setXyqfrq(Date xyqfrq) {
        this.xyqfrq = xyqfrq;
    }

    public String getDjzsxxdz() {
        return djzsxxdz;
    }

    public void setDjzsxxdz(String djzsxxdz) {
        this.djzsxxdz = djzsxxdz;
    }

    public String getZxbh() {
        return zxbh;
    }

    public void setZxbh(String zxbh) {
        this.zxbh = zxbh;
    }

    public String getJszzt() {
        return jszzt;
    }

    public void setJszzt(String jszzt) {
        this.jszzt = jszzt;
    }

    public String getSfzmc() {
        return sfzmc;
    }

    public void setSfzmc(String sfzmc) {
        this.sfzmc = sfzmc;
    }

    public String getZsxzqy() {
        return zsxzqy;
    }

    public void setZsxzqy(String zsxzqy) {
        this.zsxzqy = zsxzqy;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getZhmfqfrq() {
        return zhmfqfrq;
    }

    public void setZhmfqfrq(Date zhmfqfrq) {
        this.zhmfqfrq = zhmfqfrq;
    }
    @JsonSerialize(using = CustomDateYearSerializer.class)
    public Date getXytjrq() {
        return xytjrq;
    }
    public void setXytjrq(Date xytjrq) {
        this.xytjrq = xytjrq;
    }
}
