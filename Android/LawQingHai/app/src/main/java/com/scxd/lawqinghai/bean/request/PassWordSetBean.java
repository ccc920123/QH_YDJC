package com.scxd.lawqinghai.bean.request;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class PassWordSetBean {

    private String oldpwd;
    private String newpwd;
    private String user;
    private String bmbh;

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public PassWordSetBean(String oldpwd, String newpwd, String user, String bmbh) {
        this.oldpwd = oldpwd;
        this.newpwd = newpwd;
        this.user = user;
        this.bmbh = bmbh;
    }

    public PassWordSetBean() {
    }
}
