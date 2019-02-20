package com.scxd.beans.common;


/**
 * Created by Administrator on 2017/10/14.
 */
public class MenuRoleBean {
    private  int[] id;
    private Role role;
    private String creater;

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;

    }


}
