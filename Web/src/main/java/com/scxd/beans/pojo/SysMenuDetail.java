package com.scxd.beans.pojo;

public class SysMenuDetail {
    private int rn;
    private String id;
    private int menuId;
    private int parentid;

    private int qxid;
    private String menuName;
    private String qxName;
    private int sfxzjy;
    private int type;

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSfxzjy() {
        return sfxzjy;
    }

    public void setSfxzjy(int sfxzjy) {
        this.sfxzjy = sfxzjy;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQxid() {
        return qxid;
    }

    public void setQxid(int qxid) {
        this.qxid = qxid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getQxName() {
        return qxName;
    }

    public void setQxName(String qxName) {
        this.qxName = qxName;
    }
}