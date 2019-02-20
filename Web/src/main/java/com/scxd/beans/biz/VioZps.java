package com.scxd.beans.biz;

import com.scxd.common.JaxbDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * 违法照片对应综合平台实体类
 */
public class VioZps {
    private String xh;
    private String hpzl;
    private String hphm;
    private Date lrsj;
    private String zpstr1;
    private String zpstr2;
    private String zpstr3;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
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
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }


    public String getZpstr1() {
        return zpstr1;
    }

    public void setZpstr1(String zpstr1) {
        this.zpstr1 = zpstr1;
    }

    public String getZpstr2() {
        return zpstr2;
    }

    public void setZpstr2(String zpstr2) {
        this.zpstr2 = zpstr2;
    }

    public String getZpstr3() {
        return zpstr3;
    }

    public void setZpstr3(String zpstr3) {
        this.zpstr3 = zpstr3;
    }
}
