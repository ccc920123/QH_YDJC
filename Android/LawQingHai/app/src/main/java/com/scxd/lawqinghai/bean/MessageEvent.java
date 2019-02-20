package com.scxd.lawqinghai.bean;

/**
 * 描述：自定义消息事件
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/13
 * 修改人：
 * 修改时间：
 */


public class MessageEvent {
    /**
     * 接收到预警标识
     */
    private boolean isWarn;
    private String yjxh;
    private String hphm;
    private String cllx;
    private String yjsj;
    private String yjlx;
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

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
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

    public boolean isWarn() {
        return isWarn;
    }

    public void setWarn(boolean warn) {
        isWarn = warn;
    }

    public MessageEvent(boolean isWarn, String yjxh, String hphm, String cllx, String yjsj, String yjlx, String status) {
        this.isWarn = isWarn;
        this.yjxh = yjxh;
        this.hphm = hphm;
        this.cllx = cllx;
        this.yjsj = yjsj;
        this.yjlx = yjlx;
        this.status = status;
    }
}
