package com.scxd.beans.biz;

import java.sql.Date;

public class Q12Return {
  private String yjxh;
  private String hphm;
  private String yjsj;
  private String cllx;
  private String yjlx;
  private String yjzlx;
  private String status;

    public String getYjxh() {
        return yjxh;
    }

    public void setYjxh(String yjxh) {
        this.yjxh = yjxh;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getYjlx() {
        return yjlx;
    }

    public void setYjlx(String yjlx) {
        this.yjlx = yjlx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYjzlx() {
        return yjzlx;
    }

    public void setYjzlx(String yjzlx) {
        this.yjzlx = yjzlx;
    }

    @Override
    public String toString() {
        return "Q12Return{" +
                "yjxh='" + yjxh + '\'' +
                ", hphm='" + hphm + '\'' +
                ", yjsj='" + yjsj + '\'' +
                ", cllx='" + cllx + '\'' +
                ", yjlx='" + yjlx + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
