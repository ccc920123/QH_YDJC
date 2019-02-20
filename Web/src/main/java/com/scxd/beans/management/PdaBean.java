package com.scxd.beans.management;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:44 2018/6/6
 * @Modified By:
 */
public class PdaBean {
    private  String id;
    private  String mc;
    private  String wym;
    private  String bmbh;
    private  String bmmc;
    private String zt;
    private int rn;

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
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
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getWym() {
        return wym;
    }

    public void setWym(String wym) {
        this.wym = wym;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
}
