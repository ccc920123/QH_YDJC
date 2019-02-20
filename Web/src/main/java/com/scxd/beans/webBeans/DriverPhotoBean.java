package com.scxd.beans.webBeans;

import com.scxd.common.JaxbStringAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class DriverPhotoBean {

    private String zpStr;
    private String sfzmhm;
    private String sfzmmc;
    @XmlJavaTypeAdapter(JaxbStringAdapter.class)
    public String getZpStr() {
        return zpStr;
    }
    @XmlJavaTypeAdapter(JaxbStringAdapter.class)
    public String getSfzmhm() {
        return sfzmhm;
    }
    @XmlJavaTypeAdapter(JaxbStringAdapter.class)
    public String getSfzmmc() {
        return sfzmmc;
    }

    public void setZpStr(String zpStr) {
        this.zpStr = zpStr;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public void setSfzmmc(String sfzmmc) {
        this.sfzmmc = sfzmmc;
    }
}
