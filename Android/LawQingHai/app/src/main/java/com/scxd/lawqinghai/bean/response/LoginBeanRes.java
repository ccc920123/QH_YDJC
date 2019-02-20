package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述：登录成功的对象
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/13
 * 修改人：
 * 修改时间：
 */


public class LoginBeanRes {
    /**
     * 登录账号
     */
    private String loginname;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 部门名称
     */
    private String bmmc;
    /**
     * 权限
     */
    private String yhqx;
    /**
     * 所属部门编号
     */
    private String ssbmbh;
    /**
     * 上级部门
     */
    private String sjbmbh;
    /**
     * 事故等级
     */
    private String sgdj;
    /**
     * 服务站编号
     */
    private String fwzbh;

    /**
     * 是否接收预警消息
     */
    private String jszt;
    
    private List<String> menuIds;

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }

    public String getFwzbh() {
        return fwzbh;
    }

    public void setFwzbh(String fwzbh) {
        this.fwzbh = fwzbh;
    }

    public String getSgdj() {
        return sgdj;
    }

    public void setSgdj(String sgdj) {
        this.sgdj = sgdj;
    }

    public String getSjbm() {
        return sjbmbh;
    }

    public void setSjbm(String sjbmbh) {
        this.sjbmbh = sjbmbh;
    }

    public String getSsbmbh() {
        return ssbmbh;
    }

    public void setSsbmbh(String ssbmbh) {
        this.ssbmbh = ssbmbh;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getYhqx() {
        return yhqx;
    }

    public void setYhqx(String yhqx) {
        this.yhqx = yhqx;
    }
}
