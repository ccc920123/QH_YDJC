package com.scxd.beans.biz;


import java.util.Date;

/**
 * 功能描述：照片信息类
 */
public class BizPhotoInfo {

    private String id;
    private String glid;
    private String zpzl;
    private Date pssj;
    private String psry;
    private byte[] zp;
    private String sx1;
    private String sx2;
    private String sx3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public String getZpzl() {
        return zpzl;
    }

    public void setZpzl(String zpzl) {
        this.zpzl = zpzl;
    }

    public Date getPssj() {
        return pssj;
    }

    public void setPssj(Date pssj) {
        this.pssj = pssj;
    }

    public String getPsry() {
        return psry;
    }

    public void setPsry(String psry) {
        this.psry = psry;
    }

    public byte[] getZp() {
        return zp;
    }

    public void setZp(byte[] zp) {
        this.zp = zp;
    }

    public String getSx1() {
        return sx1;
    }

    public void setSx1(String sx1) {
        this.sx1 = sx1;
    }

    public String getSx2() {
        return sx2;
    }

    public void setSx2(String sx2) {
        this.sx2 = sx2;
    }

    public String getSx3() {
        return sx3;
    }

    public void setSx3(String sx3) {
        this.sx3 = sx3;
    }

    @Override
    public String toString() {
        return "BizPhotoInfo{" +
                "id='" + id + '\'' +
                ", glid='" + glid + '\'' +
                ", zpzl='" + zpzl + '\'' +
                ", pssj=" + pssj +
                ", psry='" + psry + '\'' +
                ", zp=" + zp +
                '}';
    }
}
