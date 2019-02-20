package com.scxd.beans.management;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:44 2018/6/6
 * @Modified By:bmbh
:
"633100000010"
bmmc
:
""
csmc
:
""
csz
:
"农业银行"
gjz
:
"JKYH"
id
:
""
ms
:
"null"
rn
:
0

 */
public class ParamsBean {
    private String id;

    private String gjz;
    private String csmc;
    private String bmbh;
    private String bmmc;
    private String csz;
    private String sfjc;
    private String ms;
    private String czsj;
    private int rn;

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGjz() {
        return gjz;
    }

    public void setGjz(String gjz) {
        this.gjz = gjz;
    }

    public String getCsmc() {
        return csmc;
    }

    public void setCsmc(String csmc) {
        this.csmc = csmc;
    }

    public String getCsz() {
        return csz;
    }

    public void setCsz(String csz) {
        this.csz = csz;
    }

    public String getSfjc() {
        return sfjc;
    }

    public void setSfjc(String sfjc) {
        this.sfjc = sfjc;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }
}
