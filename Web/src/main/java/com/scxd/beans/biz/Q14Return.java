package com.scxd.beans.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * 违法记录详细信息实体类
 */
public class Q14Return {
    private String id = "";
    private Date wfsj;
    private String zsxzqh = "";
    private String wfdd = "";
    private String wfdz = "";
    private String zqmj = "";
    private String fxjg = "";
    private String jtfs = "";
    private String xxly = "";
    private String lrr = "";
    private Date lrsj ;
    private String jsjqbj = "";
    private String jszh = "";
    private String fzjg = "";
    private String dabh = "";
    private String dsr = "";
    private String zjcx = "";
    private String dh = "";
    private String lxzsxzqh = "";
    private String syxz = "";
    private String hpzl = "";
    private String hphm = "";
    private String lxfs = "";
    private String zsxxdz = "";
    private String clfl = "";
    private String cfzl = "";
    private int wfjfs;
    private int fkje;
    private String jbr = "";
    private String clsj = "";
    private String cljg = "";
    private String jkbj = "";
    private	String	dldm = "";
    private	String	sgly = "";
    private	String	syr = "";
    private	String	clyt = "";
    private	String	wfdm = "";
    private	String	wfmc = "";
    private	String	zkys = "";
    private	String	jlts = "";
    private	String	cflb = "";
    private	String	cfbh = "";
    private List<String> zps;

    private	String	cjjgmc = "";
    private	String	jdsbh = "";
    private	String	jdslb = "";

    public String getCjjgmc() {
        return cjjgmc;
    }

    public void setCjjgmc(String cjjgmc) {
        this.cjjgmc = cjjgmc;
    }

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public String getJdslb() {
        return jdslb;
    }

    public void setJdslb(String jdslb) {
        this.jdslb = jdslb;
    }

    public String getWfmc() {
        return wfmc;
    }

    public void setWfmc(String wfmc) {
        this.wfmc = wfmc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getZps() {
        return zps;
    }

    public void setZps(List<String> zps) {
        this.zps = zps;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWfsj() {
        return wfsj;
    }

    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    public String getZsxzqh() {
        return zsxzqh;
    }

    public void setZsxzqh(String zsxzqh) {
        this.zsxzqh = zsxzqh;
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd;
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz;
    }

    public String getZqmj() {
        return zqmj;
    }

    public void setZqmj(String zqmj) {
        this.zqmj = zqmj;
    }

    public String getFxjg() {
        return fxjg;
    }

    public void setFxjg(String fxjg) {
        this.fxjg = fxjg;
    }

    public String getJtfs() {
        return jtfs;
    }

    public void setJtfs(String jtfs) {
        this.jtfs = jtfs;
    }

    public String getXxly() {
        return xxly;
    }

    public void setXxly(String xxly) {
        this.xxly = xxly;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getJsjqbj() {
        return jsjqbj;
    }

    public void setJsjqbj(String jsjqbj) {
        this.jsjqbj = jsjqbj;
    }

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getLxzsxzqh() {
        return lxzsxzqh;
    }

    public void setLxzsxzqh(String lxzsxzqh) {
        this.lxzsxzqh = lxzsxzqh;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
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

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getZsxxdz() {
        return zsxxdz;
    }

    public void setZsxxdz(String zsxxdz) {
        this.zsxxdz = zsxxdz;
    }

    public String getClfl() {
        return clfl;
    }

    public void setClfl(String clfl) {
        this.clfl = clfl;
    }

    public String getCfzl() {
        return cfzl;
    }

    public void setCfzl(String cfzl) {
        this.cfzl = cfzl;
    }

    public int getWfjfs() {
        return wfjfs;
    }

    public void setWfjfs(int wfjfs) {
        this.wfjfs = wfjfs;
    }

    public int getFkje() {
        return fkje;
    }

    public void setFkje(int fkje) {
        this.fkje = fkje;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getCljg() {
        return cljg;
    }

    public void setCljg(String cljg) {
        this.cljg = cljg;
    }

    public String getJkbj() {
        return jkbj;
    }

    public void setJkbj(String jkbj) {
        this.jkbj = jkbj;
    }

    public String getDldm() {
        return dldm;
    }

    public void setDldm(String dldm) {
        this.dldm = dldm;
    }

    public String getSgly() {
        return sgly;
    }

    public void setSgly(String sgly) {
        this.sgly = sgly;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getClyt() {
        return clyt;
    }

    public void setClyt(String clyt) {
        this.clyt = clyt;
    }

    public String getWfdm() {
        return wfdm;
    }

    public void setWfdm(String wfdm) {
        this.wfdm = wfdm;
    }

    public String getZkys() {
        return zkys;
    }

    public void setZkys(String zkys) {
        this.zkys = zkys;
    }

    public String getJlts() {
        return jlts;
    }

    public void setJlts(String jlts) {
        this.jlts = jlts;
    }

    public String getCflb() {
        return cflb;
    }

    public void setCflb(String cflb) {
        this.cflb = cflb;
    }

    public String getCfbh() {
        return cfbh;
    }

    public void setCfbh(String cfbh) {
        this.cfbh = cfbh;
    }
}
