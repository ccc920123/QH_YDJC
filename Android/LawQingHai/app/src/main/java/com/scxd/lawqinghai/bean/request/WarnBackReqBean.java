package com.scxd.lawqinghai.bean.request;

import com.scxd.lawqinghai.R;

import java.io.Serializable;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnBackReqBean implements Serializable {

    private String user;
    private String yjxh;
    private String ljqk;
    private String wljdyy;
    private String sfxycl;
    private String fxyclyy;
    private String ljqkms;
    private String cjry;
    private String czfs;
    private String wslb;
    private String yjbm;
    /**
     * 添加文书编号
     */
    private String wsbh = "";
    /**
     * 校验位
     */
    private String jyw = "";
    /**
     * 处罚嫌疑类型
     */
    private String cfxylx = "";
    /**
     * 处罚嫌疑zi类型
     */
    private String cfxyzlx = "";
    /**
     * 处置单位
     */
    private String czdw="";

    private String lxr="";
    private String lxdh="";


    public WarnBackReqBean(String user, String yjxh, String ljqk, String wljdyy, String sfxycl, String fxyclyy,
                           String ljqkms, String cjry, String czfs, String wslb, String yjbm, String wsbh, String jyw,
                           String cfxylx, String cfxyzlx,String czdw,String lxr,String lxdh) {
        this.user = user;
        this.yjxh = yjxh;
        this.ljqk = ljqk;
        this.wljdyy = wljdyy;
        this.sfxycl = sfxycl;
        this.fxyclyy = fxyclyy;
        this.ljqkms = ljqkms;
        this.cjry = cjry;
        this.czfs = czfs;
        this.wslb = wslb;
        this.yjbm = yjbm;
        this.wsbh = wsbh;
        this.jyw = jyw;
        this.cfxylx = cfxylx;
        this.cfxyzlx = cfxyzlx;
        this.czdw=czdw;
        this.lxr=lxr;
        this.lxdh=lxdh;

    }

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

    public String getFxyclyy() {
        return fxyclyy;
    }

    public void setFxyclyy(String fxyclyy) {
        this.fxyclyy = fxyclyy;
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

    public String getCfxylx() {
        return cfxylx;
    }

    public void setCfxylx(String cfxylx) {
        this.cfxylx = cfxylx;
    }

    public String getCfxyzlx() {
        return cfxyzlx;
    }

    public void setCfxyzlx(String cfxyzlx) {
        this.cfxyzlx = cfxyzlx;
    }

    public String getCzdw() {
        return czdw;
    }

    public void setCzdw(String czdw) {
        this.czdw = czdw;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
}
