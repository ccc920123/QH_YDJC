package com.scxd.beans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scxd.common.CustomJsonDateDeserializer;

import java.util.Date;

public class BizVioSurveBean {


    private String id;
    private String bmbh;
    private String zsxzqh;

    private String zsxxdz;

    private String dh;

    private String lxfs;

    private String clfl;

    private String hpzl;

    private String hphm;

    private String jdcsyr;

    private String syxz;

    private String jtfs;

    private Date wfsj;

    private String xzqh;

    private String wfdd;

    private String lddm;

    private String ddms;
    private String ddgls;

    private String wfdz;

    private String wfxw;

    private String scz;

    private String bzz;

    private String fzjg;

    private String zqmj;

    private String cjjg;

    private String fdjh;

    private String clsbdh;

    private String csys;

    private String clpp;

    private String tzsh;

    private Date tzrq;

    private String cjfs;

    private String spdz;

    private String sbbh;

    private Short dybj;

    private Short dycs;

    private String xh;

    private String lrr;

    private Date cjsj;

    private String wsjyw;

    public String getDdgls() {
        return ddgls;
    }

    public void setDdgls(String ddgls) {
        this.ddgls = ddgls;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getZsxzqh() {
        return zsxzqh;
    }

    public void setZsxzqh(String zsxzqh) {
        this.zsxzqh = zsxzqh == null ? null : zsxzqh.trim();
    }

    public String getZsxxdz() {
        return zsxxdz;
    }

    public void setZsxxdz(String zsxxdz) {
        this.zsxxdz = zsxxdz == null ? null : zsxxdz.trim();
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh == null ? null : dh.trim();
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs == null ? null : lxfs.trim();
    }

    public String getClfl() {
        return clfl;
    }

    public void setClfl(String clfl) {
        this.clfl = clfl == null ? null : clfl.trim();
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl == null ? null : hpzl.trim();
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm == null ? null : hphm.trim();
    }

    public String getJdcsyr() {
        return jdcsyr;
    }

    public void setJdcsyr(String jdcsyr) {
        this.jdcsyr = jdcsyr == null ? null : jdcsyr.trim();
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz == null ? null : syxz.trim();
    }

    public String getJtfs() {
        return jtfs;
    }

    public void setJtfs(String jtfs) {
        this.jtfs = jtfs == null ? null : jtfs.trim();
    }

    public Date getWfsj() {
        return wfsj;
    }
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh == null ? null : xzqh.trim();
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd == null ? null : wfdd.trim();
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm == null ? null : lddm.trim();
    }

    public String getDdms() {
        return ddms;
    }

    public void setDdms(String ddms) {
        this.ddms = ddms == null ? null : ddms.trim();
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz == null ? null : wfdz.trim();
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw == null ? null : wfxw.trim();
    }

    public String getScz() {
        return scz;
    }

    public void setScz(String scz) {
        this.scz = scz == null ? null : scz.trim();
    }

    public String getBzz() {
        return bzz;
    }

    public void setBzz(String bzz) {
        this.bzz = bzz == null ? null : bzz.trim();
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg == null ? null : fzjg.trim();
    }

    public String getZqmj() {
        return zqmj;
    }

    public void setZqmj(String zqmj) {
        this.zqmj = zqmj == null ? null : zqmj.trim();
    }

    public String getCjjg() {
        return cjjg;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg == null ? null : cjjg.trim();
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh == null ? null : fdjh.trim();
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh == null ? null : clsbdh.trim();
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys == null ? null : csys.trim();
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp == null ? null : clpp.trim();
    }

    public String getTzsh() {
        return tzsh;
    }

    public void setTzsh(String tzsh) {
        this.tzsh = tzsh == null ? null : tzsh.trim();
    }

    public Date getTzrq() {
        return tzrq;
    }
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setTzrq(Date tzrq) {
        this.tzrq = tzrq;
    }

    public String getCjfs() {
        return cjfs;
    }

    public void setCjfs(String cjfs) {
        this.cjfs = cjfs == null ? null : cjfs.trim();
    }

    public String getSpdz() {
        return spdz;
    }

    public void setSpdz(String spdz) {
        this.spdz = spdz == null ? null : spdz.trim();
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh == null ? null : sbbh.trim();
    }

    public Short getDybj() {
        return dybj;
    }

    public void setDybj(Short dybj) {
        this.dybj = dybj;
    }

    public Short getDycs() {
        return dycs;
    }

    public void setDycs(Short dycs) {
        this.dycs = dycs;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr == null ? null : lrr.trim();
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getWsjyw() {
        return wsjyw;
    }

    public void setWsjyw(String wsjyw) {
        this.wsjyw = wsjyw == null ? null : wsjyw.trim();
    }
}