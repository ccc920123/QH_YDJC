package com.scxd.beans.pdaBeans.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 20:05 2018/6/14
 * @Modified By:
 */
public class Q07ReturnBean {
    @JsonIgnore
    private String yjxh;
    private String hpzl;
    private String hphm;
    private String yjlx;
    private String yjzlx;
    private String yjsj;
    private String cllx;
    private String gcsj;
    private String xssd;
    private String xsqy;
    private Short xsdd;
    private String syxz;
    private String sbzl;
    private String sbmc;
    private String jszzt;
    private Short qsjg;
    private Short sfcj;
    private Short qszt;
    private String kkmc;
    private String jszh="";
    private String zjcx="";
    private String cclzrq="";
    private String jszxbh="";
    private String dabh="";
    private String ljjf="";
    private String jszyxqz="";
    private List<String> zpid;

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getCclzrq() {
        return cclzrq;
    }

    public void setCclzrq(String cclzrq) {
        this.cclzrq = cclzrq;
    }

    public String getJszxbh() {
        return jszxbh;
    }

    public void setJszxbh(String jszxbh) {
        this.jszxbh = jszxbh;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getLjjf() {
        return ljjf;
    }

    public void setLjjf(String ljjf) {
        this.ljjf = ljjf;
    }

    public String getJszyxqz() {
        return jszyxqz;
    }

    public void setJszyxqz(String jszyxqz) {
        this.jszyxqz = jszyxqz;
    }

    public String getKkmc() {
        return kkmc;
    }

    public void setKkmc(String kkmc) {
        this.kkmc = kkmc;
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

    public String getYjlx() {
        return yjlx;
    }

    public void setYjlx(String yjlx) {
        this.yjlx = yjlx;
    }

    public String getYjzlx() {
        return yjzlx;
    }

    public void setYjzlx(String yjzlx) {
        this.yjzlx = yjzlx;
    }

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getGcsj() {
        return gcsj;
    }

    public void setGcsj(String gcsj) {
        this.gcsj = gcsj;
    }

    public Short getSfcj() {
        return sfcj;
    }

    public void setSfcj(Short sfcj) {
        this.sfcj = sfcj;
    }

    public String getXssd() {
        return xssd;
    }

    public void setXssd(String xssd) {
        this.xssd = xssd;
    }

    public String getXsqy() {
        return xsqy;
    }

    public void setXsqy(String xsqy) {
        this.xsqy = xsqy;
    }

    public Short getXsdd() {
        return xsdd;
    }

    public void setXsdd(Short xsdd) {
        this.xsdd = xsdd;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    public String getSbzl() {
        return sbzl;
    }

    public void setSbzl(String sbzl) {
        this.sbzl = sbzl;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getJszzt() {
        return jszzt;
    }

    public void setJszzt(String jszzt) {
        this.jszzt = jszzt;
    }

    public Short getQsjg() {
        return qsjg;
    }

    public void setQsjg(Short qsjg) {
        this.qsjg = qsjg;
    }

    public Short getQszt() {
        return qszt;
    }

    public void setQszt(Short qszt) {
        this.qszt = qszt;
    }

    public List<String> getZpid() {
        return zpid;
    }

    public void setZpid(List<String> zpid) {
        this.zpid = zpid;
    }
}
