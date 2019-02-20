package com.scxd.beans.management;

import java.util.Date;

/**
 * 标题：部门
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
public class Dept {
    private String id;
    private String parentid;
    private String bmbh;
    private String sjzdbmbh;
    private String levelnum;
    private String type;
    private String name;
    private String fax;
    private String lxr;
    private String lxrdh;
    private String lxdz;
    private String zt;
    private String bz;
    private String czryzh;
    private String czsj;
    private String fzjg;
    private String sfzsbm;
    private String zsbmbhs;
    private String code;
    private String xzqh;
    private String prent;
    private String rn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getSjzdbmbh() {
        return sjzdbmbh;
    }

    public void setSjzdbmbh(String sjzdbmbh) {
        this.sjzdbmbh = sjzdbmbh;
    }

    public String getLevelnum() {
        return levelnum;
    }

    public void setLevelnum(String levelnum) {
        this.levelnum = levelnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = (type == null || type.equals(""))?"1":type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxrdh() {
        return lxrdh;
    }

    public void setLxrdh(String lxrdh) {
        this.lxrdh = lxrdh;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = (zt == null || zt.equals(""))?"1":zt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCzryzh() {
        return czryzh;
    }

    public void setCzryzh(String czryzh) {
        this.czryzh = czryzh;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = (czsj == null || czsj.equals(""))?new Date().toString() :czsj;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getSfzsbm() {
        return sfzsbm;
    }

    public void setSfzsbm(String sfzsbm) {
        this.sfzsbm = sfzsbm;
    }

    public String getZsbmbhs() {
        return zsbmbhs;
    }

    public void setZsbmbhs(String zsbmbhs) {
        this.zsbmbhs = zsbmbhs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getPrent() {
        return prent;
    }

    public void setPrent(String prent) {
        this.prent = prent;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }
}
