package com.scxd.beans.pdaBeans.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.beans.biz.Wfxwlist;
import com.scxd.common.CustomDateSerializer;
import com.scxd.common.CustomJsonDateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * @Auther:陈攀
 * @Description:强制措施详情
 * @Date:Created in 9:23 2018/7/6
 * @Modified By:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Q21ReturnBean {
    //只在序列化时忽略
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String bmbh = "";
    private String jdsbh = "";

    private String bz = "";
    private String clfl = "";
    private String cllx = "";
    private String clsbdh = "";
    private String csys = "";
    private String dabh = "";
    private String ddms = "";
    private String dh = "";
    private String dsr = "";
    private String dsryy = "";
    private String fdjh = "";
    private String flyj = "";
    private String fxjg = "";
    private String fzjg = "";
    private String gls = "";
    private String hphm = "";
    private String hpzl = "";
    private String jd = "";
    private String jdcsyr = "";
    private String jsjqbj = "";
    private String jszh = "";
    private String jszzt = "";
    private String jtfs = "";
    private String klwpcfd = "";
    private String lddm = "";
    private String ljjf = "";
    private String lrr = "";
    private String lxdz = "";
    private String lxfs = "";
    private String mjyj = "";
    private String qzcslx = "";
    private String sgdj = "";
    private String sjwpcfd = "";
    private String sjwpmc = "";
    private String sjxm = "";
    private String syxz = "";
    private String wd = "";
    private String wfdd = "";
    private String wfdz = "";
    private String wfnr = "";
    private Date wfsj;
    private List<Wfxwlist> wfxwlist;
    private String wslb = "";
    private String xzqh = "";
    private String zjcx = "";
    private String zqmj = "";
    private String zsxxdz = "";
    private String zsxzqh = "";
    private String ryfl = "";
    private String bmqc = "";
    private String xzss = "";
    private String sjbm = "";
    @JsonIgnore
    private String wsjyw = "";
    private String xzssjg = "";

    public String getXzssjg() {
        return xzssjg;
    }

    public void setXzssjg(String xzssjg) {
        this.xzssjg = xzssjg;
    }

    public String getWsjyw() {
        return wsjyw;
    }

    public void setWsjyw(String wsjyw) {
        this.wsjyw = wsjyw;
    }

    public String getRyfl() {
        return ryfl;
    }

    public void setRyfl(String ryfl) {
        this.ryfl = ryfl;
    }

    public String getBmqc() {
        return bmqc;
    }

    public void setBmqc(String bmqc) {
        this.bmqc = bmqc;
    }

    public String getXzss() {
        return xzss;
    }

    public void setXzss(String xzss) {
        this.xzss = xzss;
    }

    public String getSjbm() {
        return sjbm;
    }

    public void setSjbm(String sjbm) {
        this.sjbm = sjbm;
    }

    @JsonIgnore
    private String wfxw1 = "";

    @JsonIgnore
    private String scz1 = "";

    @JsonIgnore
    private String bzz1 = "";
    @JsonIgnore
    private String wfnr1 = "";

    @JsonIgnore
    private String flyj1 = "";

    @JsonIgnore
    private String wfxw2 = "";

    @JsonIgnore
    private String scz2 = "";

    @JsonIgnore
    private String bzz2 = "";
    @JsonIgnore
    private String wfnr2 = "";

    @JsonIgnore
    private String flyj2 = "";

    @JsonIgnore
    private String wfxw3 = "";

    @JsonIgnore
    private String scz3 = "";
    @JsonIgnore
    private String wfnr3 = "";

    @JsonIgnore
    private String flyj3 = "";

    @JsonIgnore
    private String bzz3 = "";

    @JsonIgnore
    private String wfxw4 = "";

    @JsonIgnore
    private String scz4 = "";

    @JsonIgnore
    private String bzz4 = "";
    @JsonIgnore
    private String wfnr4 = "";

    @JsonIgnore
    private String flyj4 = "";

    @JsonIgnore
    private String wfxw5 = "";

    @JsonIgnore
    private String scz5 = "";
    @JsonIgnore
    private String bzz5 = "";
    @JsonIgnore
    private String wfnr5 = "";

    @JsonIgnore
    private String flyj5 = "";

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public String getWfnr1() {
        return wfnr1;
    }

    public void setWfnr1(String wfnr1) {
        this.wfnr1 = wfnr1;
    }

    public String getFlyj1() {
        return flyj1;
    }

    public void setFlyj1(String flyj1) {
        this.flyj1 = flyj1;
    }

    public String getWfnr2() {
        return wfnr2;
    }

    public void setWfnr2(String wfnr2) {
        this.wfnr2 = wfnr2;
    }

    public String getFlyj2() {
        return flyj2;
    }

    public void setFlyj2(String flyj2) {
        this.flyj2 = flyj2;
    }

    public String getWfnr3() {
        return wfnr3;
    }

    public void setWfnr3(String wfnr3) {
        this.wfnr3 = wfnr3;
    }

    public String getFlyj3() {
        return flyj3;
    }

    public void setFlyj3(String flyj3) {
        this.flyj3 = flyj3;
    }

    public String getWfnr4() {
        return wfnr4;
    }

    public void setWfnr4(String wfnr4) {
        this.wfnr4 = wfnr4;
    }

    public String getFlyj4() {
        return flyj4;
    }

    public void setFlyj4(String flyj4) {
        this.flyj4 = flyj4;
    }

    public String getWfnr5() {
        return wfnr5;
    }

    public void setWfnr5(String wfnr5) {
        this.wfnr5 = wfnr5;
    }

    public String getFlyj5() {
        return flyj5;
    }

    public void setFlyj5(String flyj5) {
        this.flyj5 = flyj5;
    }

    public String getWfxw1() {
        return wfxw1;
    }

    public void setWfxw1(String wfxw1) {
        this.wfxw1 = wfxw1;
    }

    public String getScz1() {
        return scz1;
    }

    public void setScz1(String scz1) {
        this.scz1 = scz1;
    }

    public String getBzz1() {
        return bzz1;
    }

    public void setBzz1(String bzz1) {
        this.bzz1 = bzz1;
    }

    public String getWfxw2() {
        return wfxw2;
    }

    public void setWfxw2(String wfxw2) {
        this.wfxw2 = wfxw2;
    }

    public String getScz2() {
        return scz2;
    }

    public void setScz2(String scz2) {
        this.scz2 = scz2;
    }

    public String getBzz2() {
        return bzz2;
    }

    public void setBzz2(String bzz2) {
        this.bzz2 = bzz2;
    }

    public String getWfxw3() {
        return wfxw3;
    }

    public void setWfxw3(String wfxw3) {
        this.wfxw3 = wfxw3;
    }

    public String getScz3() {
        return scz3;
    }

    public void setScz3(String scz3) {
        this.scz3 = scz3;
    }

    public String getBzz3() {
        return bzz3;
    }

    public void setBzz3(String bzz3) {
        this.bzz3 = bzz3;
    }

    public String getWfxw4() {
        return wfxw4;
    }

    public void setWfxw4(String wfxw4) {
        this.wfxw4 = wfxw4;
    }

    public String getScz4() {
        return scz4;
    }

    public void setScz4(String scz4) {
        this.scz4 = scz4;
    }

    public String getBzz4() {
        return bzz4;
    }

    public void setBzz4(String bzz4) {
        this.bzz4 = bzz4;
    }

    public String getWfxw5() {
        return wfxw5;
    }

    public void setWfxw5(String wfxw5) {
        this.wfxw5 = wfxw5;
    }

    public String getScz5() {
        return scz5;
    }

    public void setScz5(String scz5) {
        this.scz5 = scz5;
    }

    public String getBzz5() {
        return bzz5;
    }

    public void setBzz5(String bzz5) {
        this.bzz5 = bzz5;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz() {
        return bz;
    }

    public void setClfl(String clfl) {
        this.clfl = clfl;
    }

    public String getClfl() {
        return clfl;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getCllx() {
        return cllx;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getCsys() {
        return csys;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDdms(String ddms) {
        this.ddms = ddms;
    }

    public String getDdms() {
        return ddms;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getDh() {
        return dh;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsryy(String dsryy) {
        this.dsryy = dsryy;
    }

    public String getDsryy() {
        return dsryy;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFlyj(String flyj) {
        this.flyj = flyj;
    }

    public String getFlyj() {
        return flyj;
    }

    public void setFxjg(String fxjg) {
        this.fxjg = fxjg;
    }

    public String getFxjg() {
        return fxjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setGls(String gls) {
        this.gls = gls;
    }

    public String getGls() {
        return gls;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getJd() {
        return jd;
    }

    public void setJdcsyr(String jdcsyr) {
        this.jdcsyr = jdcsyr;
    }

    public String getJdcsyr() {
        return jdcsyr;
    }

    public void setJsjqbj(String jsjqbj) {
        this.jsjqbj = jsjqbj;
    }

    public String getJsjqbj() {
        return jsjqbj;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public String getJszh() {
        return jszh;
    }

    public void setJszzt(String jszzt) {
        this.jszzt = jszzt;
    }

    public String getJszzt() {
        return jszzt;
    }

    public void setJtfs(String jtfs) {
        this.jtfs = jtfs;
    }

    public String getJtfs() {
        return jtfs;
    }

    public void setKlwpcfd(String klwpcfd) {
        this.klwpcfd = klwpcfd;
    }

    public String getKlwpcfd() {
        return klwpcfd;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLjjf(String ljjf) {
        this.ljjf = ljjf;
    }

    public String getLjjf() {
        return ljjf;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setMjyj(String mjyj) {
        this.mjyj = mjyj;
    }

    public String getMjyj() {
        return mjyj;
    }

    public void setQzcslx(String qzcslx) {
        this.qzcslx = qzcslx;
    }

    public String getQzcslx() {
        return qzcslx;
    }

    public void setSgdj(String sgdj) {
        this.sgdj = sgdj;
    }

    public String getSgdj() {
        return sgdj;
    }

    public void setSjwpcfd(String sjwpcfd) {
        this.sjwpcfd = sjwpcfd;
    }

    public String getSjwpcfd() {
        return sjwpcfd;
    }

    public void setSjwpmc(String sjwpmc) {
        this.sjwpmc = sjwpmc;
    }

    public String getSjwpmc() {
        return sjwpmc;
    }

    public void setSjxm(String sjxm) {
        this.sjxm = sjxm;
    }

    public String getSjxm() {
        return sjxm;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWd() {
        return wd;
    }

    public void setWfdd(String wfdd) {
        this.wfdd = wfdd;
    }

    public String getWfdd() {
        return wfdd;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz;
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfnr(String wfnr) {
        this.wfnr = wfnr;
    }

    public String getWfnr() {
        return wfnr;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setWfsj(Date wfsj) {
        this.wfsj = wfsj;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWfsj() {
        return wfsj;
    }

    public void setWfxwlist(List<Wfxwlist> wfxwlist) {
        this.wfxwlist = wfxwlist;
    }

    public List<Wfxwlist> getWfxwlist() {
        return wfxwlist;
    }

    public void setWslb(String wslb) {
        this.wslb = wslb;
    }

    public String getWslb() {
        return wslb;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZqmj(String zqmj) {
        this.zqmj = zqmj;
    }

    public String getZqmj() {
        return zqmj;
    }

    public void setZsxxdz(String zsxxdz) {
        this.zsxxdz = zsxxdz;
    }

    public String getZsxxdz() {
        return zsxxdz;
    }

    public void setZsxzqh(String zsxzqh) {
        this.zsxzqh = zsxzqh;
    }

    public String getZsxzqh() {
        return zsxzqh;
    }

}
