package com.scxd.beans.management;

import java.util.Date;

public class RoleBean {

    private String userId;
    private int roleId;
    private Date createtime;
    private String creater;

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String userId) {
        this.userId = userId;
    }

    public int getRole_id() {
        return roleId;
    }

    public void setRole_id(int roleId) {
        this.roleId = roleId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
