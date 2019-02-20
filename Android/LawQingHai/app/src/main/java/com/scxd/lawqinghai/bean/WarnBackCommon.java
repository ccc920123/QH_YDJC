package com.scxd.lawqinghai.bean;

import com.scxd.lawqinghai.bean.request.WarnBackReqBean;

import java.io.Serializable;
import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述: 预警反馈传值Bean
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnBackCommon implements Serializable {
    
    private WarnBackReqBean mWarnBackReqBean;
    private List<String> photos;
    private String yjxh;
    private String hphm;
    private String hpzl;
    private String dldm;
    private String dlmc;
    private String xzqh;
    private String lddm;
    private String ldmc;
    private String yjsj;

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
    }

    public WarnBackCommon(WarnBackReqBean warnBackReqBean, List<String> photos, String yjxh, String hphm, String 
            hpzl, String dldm, String dlmc, String xzqh, String lddm, String ldmc, String yjsj) {
            
        mWarnBackReqBean = warnBackReqBean;
        this.photos = photos;
        this.yjxh = yjxh;
        this.hphm = hphm;
        this.hpzl = hpzl;
        this.dldm = dldm;
        this.dlmc = dlmc;
        this.xzqh = xzqh;
        this.lddm = lddm;
        this.ldmc = ldmc;
        this.yjsj = yjsj;
    }

    public String getDldm() {
        return dldm;
    }

    public void setDldm(String dldm) {
        this.dldm = dldm;
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

    public WarnBackCommon(WarnBackReqBean warnBackReqBean, List<String> photos, String yjxh, String hphm, String 
            hpzl, String dldm, String dlmc, String xzqh, String lddm, String ldmc) {
            
        mWarnBackReqBean = warnBackReqBean;
        this.photos = photos;
        this.yjxh = yjxh;
        this.hphm = hphm;
        this.hpzl = hpzl;
        this.dldm = dldm;
        this.dlmc = dlmc;
        this.xzqh = xzqh;
        this.lddm = lddm;
        this.ldmc = ldmc;
    }

    public WarnBackCommon() {
    }

    public WarnBackReqBean getWarnBackReqBean() {
    
        return mWarnBackReqBean;
    }

    public void setWarnBackReqBean(WarnBackReqBean warnBackReqBean) {
        mWarnBackReqBean = warnBackReqBean;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getYjxh() {
        return yjxh;
    }

    public void setYjxh(String yjxh) {
        this.yjxh = yjxh;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public WarnBackCommon(WarnBackReqBean warnBackReqBean, List<String> photos, String yjxh, String hphm, String 
            hpzl) {
        mWarnBackReqBean = warnBackReqBean;
        this.photos = photos;
        this.yjxh = yjxh;
        this.hphm = hphm;
        this.hpzl = hpzl;
    }
}
