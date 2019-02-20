package com.scxd.beans.pojo;

import java.util.Date;

public class SysRole {
    private int roleId;//NUMBER	N			主键标识
    private String name;        //			角色名称
    private int levelnum;    //Y	1		角色层级  biz_codeinfo where dmlb=1040
    private int type;        //Y	//1		角色类型 biz_codeinfo where dmlb=1039
    private int state;    //N	1		状态  0：停用  1：正常
    private String czryzh;        //N			操作人员账号
    private Date czsj;    //N	SYSDATE		操作时间
    private String cjbmbh;    //Y			创建部门编号

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelnum() {
        return levelnum;
    }

    public void setLevelnum(int levelnum) {
        this.levelnum = levelnum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCzryzh() {
        return czryzh;
    }

    public void setCzryzh(String czryzh) {
        this.czryzh = czryzh;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getCjbmbh() {
        return cjbmbh;
    }

    public void setCjbmbh(String cjbmbh) {
        this.cjbmbh = cjbmbh;
    }
}