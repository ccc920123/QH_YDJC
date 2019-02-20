package com.scxd.beans.pdaBeans.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 15:23 2018/6/15
 * @Modified By:{"cfxylx":"04","cjry":"","czdw":"633100000010","czfs":"8","fxyxlyy":"02",
 * "jyw":"","ljqk":"1","ljqkms":"asdasdasdasd","sfxycl":"1","user":"000718","wljdyy":"","wsbh":"","wslb":"","yjbm":"","yjxh":"6300A00061652398"}
 * {"cfxylx":"04","cjry":"李一川","czdw":"633102000010","czfs":"2","fxyclyy":"",
 * "jyw":"2","ljqk":"1","ljqkms":"民警拦截并处罚","lxdh":"","lxr":"","sfxycl":"1","user":"000718","wljdyy":"","wsbh":"633102100053542","wslb":"1","yjbm":"","yjxh":"6300A00062456621"}
 */

public class PdaFeedBackRequest {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String user="";
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String yjxh="";
    private String yjsj="";
    private String ljqk="";
    private String wljdyy="";
    private String sfxycl="";
    private String fxyclyy="";
    private String ljqkms="";
    private String cjry="";
    private String czfs="";
    private String wsbh="";
    private String wslb="";
    private String yjbm="";
    private String cfxylx="";
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cfxyzlx="";
    private String jyw="";
    private String czdw="";
    private String lxdh="";
    private String lxr="";
    private String dldm="";
    private String dlmc="";
    private String xzqh="";
    private String lddm="";
    private String ldmc="";
    private String czr="";

    public String getCfxyzlx() {
        return cfxyzlx;
    }

    public void setCfxyzlx(String cfxyzlx) {
        this.cfxyzlx = cfxyzlx;
    }

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getDldm() {
        return dldm;
    }

    public void setDldm(String dldm) {
        this.dldm = dldm;
    }

    public String getFxyclyy() {
        return fxyclyy;
    }

    public void setFxyclyy(String fxyclyy) {
        this.fxyclyy = fxyclyy;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getWsbh() {
        return wsbh;
    }

    public void setWsbh(String wsbh) {
        this.wsbh = wsbh;
    }

    public String getJyw() {
        return jyw;
    }

    public void setJyw(String jyw) {
        this.jyw = jyw;
    }

    public String getCzdw() {
        return czdw;
    }

    public void setCzdw(String czdw) {
        this.czdw = czdw;
    }

    public String getCfxylx() {
        return cfxylx;
    }

    public void setCfxylx(String cfxylx) {
        this.cfxylx = cfxylx;
    }

    public String getWslb() {
        return wslb;
    }

    public void setWslb(String wslb) {
        this.wslb = wslb;
    }

    public String getYjbm() {
        return yjbm;
    }

    public void setYjbm(String yjbm) {
        this.yjbm = yjbm;
    }

    private List<String> zpid;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getYjxh() {
        return yjxh;
    }

    public void setYjxh(String yjxh) {
        this.yjxh = yjxh;
    }

    public String getLjqk() {
        return ljqk;
    }

    public void setLjqk(String ljqk) {
        this.ljqk = ljqk;
    }

    public String getWljdyy() {
        return wljdyy;
    }

    public void setWljdyy(String wljdyy) {
        this.wljdyy = wljdyy;
    }

    public String getSfxycl() {
        return sfxycl;
    }

    public void setSfxycl(String sfxycl) {
        this.sfxycl = sfxycl;
    }

    public String getFxyxlyy() {
        return fxyclyy;
    }

    public void setFxyxlyy(String fxyxlyy) {
        this.fxyclyy = fxyxlyy;
    }

    public String getLjqkms() {
        return ljqkms;
    }

    public void setLjqkms(String ljqkms) {
        this.ljqkms = ljqkms;
    }

    public String getCjry() {
        return cjry;
    }

    public void setCjry(String cjry) {
        this.cjry = cjry;
    }

    public String getCzfs() {
        return czfs;
    }

    public void setCzfs(String czfs) {
        this.czfs = czfs;
    }

    public List<String> getZpid() {
        return zpid;
    }

    public void setZpid(List<String> zpid) {
        this.zpid = zpid;
    }
}
