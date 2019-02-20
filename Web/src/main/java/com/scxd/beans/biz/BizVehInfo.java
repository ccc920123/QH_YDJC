package com.scxd.beans.biz;

import java.util.Date;

public class BizVehInfo {

    private String zdcllx;
    private String cllx;
    private int wfcs;
    private String sfwfwcl;
    private String sfyqwjy;
    private String sfyqwbf;
    private String lxdh;
    private String zsxxdz;
    private Date xrsj;
    private String id;
    private String hphm;
    private String hpzl;
    private String clpp1;
    private String sfdqc;
    private String csys;
    private String zsxzqh;
    private String jdcbgyy;
    private Date hpqyrq;
    private Date ccdjrq;
    private String syr;
    private String syxz;
    private String clsbdh;
    private String gcjk;
    private Date bxzzrq;
    private String hgzbh;
    private String fdjh;
    private String xszbh;
    private String zt;
    private int pl;
    private int gl;
    private String dybj;
    private float cwkc;
    private Date yxqz;
    private float cwkk;
    private float cwkg;
    private String fzjg;

//新加字段
    private Date   qzbfqz;//	date	y			强制报废期止
    private String   clxh;//	varchar2(32)	y			车辆型号
    private String   zbzl;//	number	y			整备质量
    private String   fdjxh;//	varchar2(20)	y			发动机型号
    private Date   ccrq;//	date	y			出厂日期
    private String   hlj;//	number	y			后轮距
    private String   zzl;//	number	y			总质量
    private String   rlzl;//	varchar2(1)	y			燃料种类
    private String   hdzk;//	number	y			核定载客
    private String   qlj;//	number	y			前轮距
    private String   zj	;//number	y			轴距
    private String   hdzzl;//	number	y			核定载质量
    private String   zs;//	number	y			轴数

    public Date getQzbfqz() {
        return qzbfqz;
    }

    public void setQzbfqz(Date qzbfqz) {
        this.qzbfqz = qzbfqz;
    }

    public String getClxh() {
        return clxh.replaceAll("-","");
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getZbzl() {
        return zbzl.replaceAll("-","");
    }

    public void setZbzl(String zbzl) {
        this.zbzl = zbzl;
    }

    public String getFdjxh() {
        return fdjxh;
    }

    public void setFdjxh(String fdjxh) {
        this.fdjxh = fdjxh;
    }

    public Date getCcrq() {
        return ccrq;
    }

    public void setCcrq(Date ccrq) {
        this.ccrq = ccrq;
    }

    public String getHlj() {
        return hlj;
    }

    public void setHlj(String hlj) {
        this.hlj = hlj;
    }

    public String getZzl() {
        return zzl;
    }

    public void setZzl(String zzl) {
        this.zzl = zzl;
    }

    public String getRlzl() {
        return rlzl;
    }

    public void setRlzl(String rlzl) {
        this.rlzl = rlzl;
    }

    public String getHdzk() {
        return hdzk;
    }

    public void setHdzk(String hdzk) {
        this.hdzk = hdzk;
    }

    public String getQlj() {
        return qlj;
    }

    public void setQlj(String qlj) {
        this.qlj = qlj;
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getHdzzl() {
        return hdzzl;
    }

    public void setHdzzl(String hdzzl) {
        this.hdzzl = hdzzl;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getZdcllx() {
        return zdcllx;
    }

    public void setZdcllx(String zdcllx) {
        this.zdcllx = zdcllx;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public int getWfcs() {
        return wfcs;
    }

    public void setWfcs(int wfcs) {
        this.wfcs = wfcs;
    }

    public String getSfwfwcl() {
        return sfwfwcl;
    }

    public void setSfwfwcl(String sfwfwcl) {
        this.sfwfwcl = sfwfwcl;
    }

    public String getSfyqwjy() {
        return sfyqwjy;
    }

    public void setSfyqwjy(String sfyqwjy) {
        this.sfyqwjy = sfyqwjy;
    }

    public String getSfyqwbf() {
        return sfyqwbf;
    }

    public void setSfyqwbf(String sfyqwbf) {
        this.sfyqwbf = sfyqwbf;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getZsxxdz() {
        return zsxxdz;
    }

    public void setZsxxdz(String zsxxdz) {
        this.zsxxdz = zsxxdz;
    }

    public Date getXrsj() {
        return xrsj;
    }

    public void setXrsj(Date xrsj) {
        this.xrsj = xrsj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getClpp1() {
        return clpp1;
    }

    public void setClpp1(String clppl) {
        this.clpp1 = clppl;
    }

    public String getSfdqc() {
        return sfdqc;
    }

    public void setSfdqc(String sfdqc) {
        this.sfdqc = sfdqc;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getZsxzqh() {
        return zsxzqh;
    }

    public void setZsxzqh(String zsxzqh) {
        this.zsxzqh = zsxzqh;
    }

    public String getJdcbgyy() {
        return jdcbgyy;
    }

    public void setJdcbgyy(String jdcbgyy) {
        this.jdcbgyy = jdcbgyy;
    }

    public Date getHpqyrq() {
        return hpqyrq;
    }

    public void setHpqyrq(Date hpqyrq) {
        this.hpqyrq = hpqyrq;
    }

    public Date getCcdjrq() {
        return ccdjrq;
    }

    public void setCcdjrq(Date ccdjrq) {
        this.ccdjrq = ccdjrq;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getGcjk() {
        return gcjk;
    }

    public void setGcjk(String gcjk) {
        this.gcjk = gcjk;
    }

    public Date getBxzzrq() {
        return bxzzrq;
    }

    public void setBxzzrq(Date bxzzrq) {
        this.bxzzrq = bxzzrq;
    }

    public String getHgzbh() {
        return hgzbh;
    }

    public void setHgzbh(String hgzbh) {
        this.hgzbh = hgzbh;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getXszbh() {
        return xszbh;
    }

    public void setXszbh(String xszbh) {
        this.xszbh = xszbh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public int getPl() {
        return pl;
    }

    public void setPl(int pl) {
        this.pl = pl;
    }

    public int getGl() {
        return gl;
    }

    public void setGl(int gl) {
        this.gl = gl;
    }

    public String getDybj() {
        return dybj;
    }

    public void setDybj(String dybj) {
        this.dybj = dybj;
    }

    public float getCwkc() {
        return cwkc;
    }

    public void setCwkc(float cwkc) {
        this.cwkc = cwkc;
    }

    public Date getYxqz() {
        return yxqz;
    }

    public void setYxqz(Date yxqz) {
        this.yxqz = yxqz;
    }

    public float getCwkk() {
        return cwkk;
    }

    public void setCwkk(float cwkk) {
        this.cwkk = cwkk;
    }

    public float getCwkg() {
        return cwkg;
    }

    public void setCwkg(float cwkg) {
        this.cwkg = cwkg;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }
}
