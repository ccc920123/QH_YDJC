package com.scxd.beans.common;

/**
 * 用户信息扩展类
 */
public class UserExtend{

    private User user;

    private String ztname = "";

    private String kqipxzstr = "";

    private String sfsypdaStr = "";

    public String getZtname() {
        return ztname;
    }

    public void setZtname(String ztname) {
        this.ztname = ztname;
    }

    public String getKqipxzstr() {
        return kqipxzstr;
    }

    public void setKqipxzstr(String kqipxzstr) {
        this.kqipxzstr = kqipxzstr;
    }

    public String getSfsypdaStr() {
        return sfsypdaStr;
    }

    public void setSfsypdaStr(String sfsypdaStr) {
        this.sfsypdaStr = sfsypdaStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
