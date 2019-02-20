package com.scxd.lawqinghai.bean.request;

/**
 * 描述：写入布控数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/15
 * 修改人：
 * 修改时间：
 */


public class WriteDispatchBean {
    /**
     * 设备卡控
     */
    private String sbkk;
    /**
     * 预警类型
     */
    private String yjlx;
    /**
     * 出警人员
     */
    private String cjry;
    /**
     * user
     */
    private String user;
    /**
     * 接受状态
     */
    private String jszt;
    /**
     * bmbh
     */
    private String bmbh;

    public String getSbkk() {
        return sbkk;
    }

    public void setSbkk(String sbkk) {
        this.sbkk = sbkk;
    }

    public String getYjlx() {
        return yjlx;
    }

    public void setYjlx(String yjlx) {
        this.yjlx = yjlx;
    }

    public String getCjry() {
        return cjry;
    }

    public void setCjry(String cjry) {
        this.cjry = cjry;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public WriteDispatchBean(String sbkk, String yjlx, String cjry, String user, String jszt, String bmbh) {
        this.sbkk = sbkk;
        this.yjlx = yjlx;
        this.cjry = cjry;
        this.user = user;
        this.jszt = jszt;
        this.bmbh = bmbh;
    }

    public WriteDispatchBean() {
    }
}
