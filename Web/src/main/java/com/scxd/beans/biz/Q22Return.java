package com.scxd.beans.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 电子监控决定书实体类
 */
public class Q22Return {
    private		String	xh = UUID.randomUUID().toString();
    private   String jdsbh="";
    private		String	cjjg = "";
    private		String	clfl = "";
    private		String	hpzl = "";
    private		String	hphm = "";
    private		String	jdcsyr = "";
    private		String	syxz = "";
    private		String	fdjh = "";
    private		String	clsbdh = "";
    private		String	csys = "";
    private		String	clpp = "";
    private		String	jtfs = "";
    private		String	fzjg = "";
    private		String	zsxzqh = "";
    private		String	zsxxdz = "";
    private		String	dh = "";
    private		String	lxfs = "";
    private		String	tzsh = "";
    private     Date tzrq;
    private		String	cjfs = "";
    private		Date	wfsj;
    private		String	xzqh = "";
    private		String	wfdd = "";
    private		String	lddm = "";
    private		String	ddms = "";
    private		String	ddgls = "";
    private		String	wfdz = "";
    private		String	wfxw = "";
    private		String	scz = "";
    private		String	bzz = "";
    private		String	zqmj = "";
    private		String	spdz = "";
    private		String	sbbh = "";
    private String wfnr = "";
    private String flyj = "";
    private int dybj;
    private int dycs;
    private String lrr = "";
    private Date cjsj;
    private String hpzlname = "";
    private String lrrname = "";
    private  String bmqc="";
    private  String cllx="";
    private List<String> zps;

    public String getDdgls() {
        return ddgls;
    }

    public void setDdgls(String ddgls) {
        this.ddgls = ddgls;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getBmqc() {
        return bmqc;
    }

    public void setBmqc(String bmqc) {
        this.bmqc = bmqc;
    }

    public int getDybj() {
        return dybj;
    }

    public void setDybj(int dybj) {
        this.dybj = dybj;
    }

    public int getDycs() {
        return dycs;
    }

    public void setDycs(int dycs) {
        this.dycs = dycs;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public List<String> getZps() {
        return zps;
    }

    public void setZps(List<String> zps) {
        this.zps = zps;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getCjjg() {
        return cjjg;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    public String getClfl() {
        return clfl;
    }

    public void setClfl(String clfl) {
        this.clfl = clfl;
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

    public String getJdcsyr() {
        return jdcsyr;
    }

    public void setJdcsyr(String jdcsyr) {
        this.jdcsyr = jdcsyr;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public String getJtfs() {
        return jtfs;
    }

    public void setJtfs(String jtfs) {
        this.jtfs = jtfs;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getZsxzqh() {
        return zsxzqh;
    }

    public void setZsxzqh(String zsxzqh) {
        this.zsxzqh = zsxzqh;
    }

    public String getZsxxdz() {
        return zsxxdz;
    }

    public void setZsxxdz(String zsxxdz) {
        this.zsxxdz = zsxxdz;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getTzsh() {
        return tzsh;
    }

    public void setTzsh(String tzsh) {
        this.tzsh = tzsh;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getTzrq() {
        return tzrq;
    }

    public void setTzrq(Date tzrq) {
        this.tzrq = tzrq;
    }

    public String getCjfs() {
        return cjfs;
    }

    public void setCjfs(String cjfs) {
        this.cjfs = cjfs;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWfsj() {
        return wfsj;
    }

    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getDdms() {
        return ddms;
    }

    public void setDdms(String ddms) {
        this.ddms = ddms;
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz;
    }

    public String getWfxw() {
        return wfxw;
    }

    public void setWfxw(String wfxw) {
        this.wfxw = wfxw;
    }

    public String getScz() {
        return scz;
    }

    public void setScz(String scz) {
        this.scz = scz;
    }

    public String getBzz() {
        return bzz;
    }

    public void setBzz(String bzz) {
        this.bzz = bzz;
    }

    public String getZqmj() {
        return zqmj;
    }

    public void setZqmj(String zqmj) {
        this.zqmj = zqmj;
    }

    public String getSpdz() {
        return spdz;
    }

    public void setSpdz(String spdz) {
        this.spdz = spdz;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getWfnr() {
        return wfnr;
    }

    public void setWfnr(String wfnr) {
        this.wfnr = wfnr;
    }

    public String getFlyj() {
        return flyj;
    }

    public void setFlyj(String flyj) {
        this.flyj = flyj;
    }

    public String getHpzlname() {
        return hpzlname;
    }

    public void setHpzlname(String hpzlname) {
        this.hpzlname = hpzlname;
    }

    public String getLrrname() {
        return lrrname;
    }

    public void setLrrname(String lrrname) {
        this.lrrname = lrrname;
    }
}
