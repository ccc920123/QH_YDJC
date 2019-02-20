package com.scxd.beans.pdaBeans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Auther:陈攀
 * @Description:强制措施列表
 * @Date:Created in 15:01 2018/7/5
 * @Modified By:
 */
public class Q18ReturnBean {

    private String jdsbh;
    private String hpzl;
    private String hphm;
    private String dsr;
    private String jszh;
    private String qzcs="";

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
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

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public String getQzcs() {
        return qzcs;
    }

    public void setQzcs(String qzcs) {
        this.qzcs = qzcs;
    }
}
