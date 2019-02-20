package com.scxd.beans.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：用户实体类
 * 作者：齐遥遥
 * 时间：2017/9/1
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class User implements Serializable {

    private String user_id;
    private String ssbmbh;//所属于部门编号
    private String loginname;//用户名
    private String realname;
    private String sfzhm;
    private String mmyxq;
    private String lxdh;
    private int kqipxz;
    private String ipaddress1;
    private String ipaddress2;
    private String ipaddress3;
    private int zt;
    private String lxdz;
    private String bz;
    private String czsj;
    private String czryzh;
    private String password;
    private String lasttime;
    private String preipaddress;
    private String sfmj;
    private String ygbh;
    private String yhyxq;
    private String yxqzt;
    private int sbcs;
    private String sbsj;
    private String roles;
    private String ssbmname;
    private int rn;
    private String rolesname;
    private String dlkssj;
    private String dljssj;
    private  int sfsypda;

    /* 总队部门ID */
    private int zdDeptId;

    /* 部门ID */
    private int deptId;

    // 用户权限列表
    private List<Role> roleList;

    public int getZdDeptId() {
        return zdDeptId;
    }

    public void setZdDeptId(int zdDeptId) {
        this.zdDeptId = zdDeptId;
    }

    // 用户菜单列表
    private List<MenuBean> menuBeanList;

    public List<Role> getRoleList() {

        if(null == roleList) {
            roleList = new ArrayList<>();
        }

        return roleList;
    }

    public String getDlkssj() {
        return dlkssj;
    }

    public void setDlkssj(String dlkssj) {
        this.dlkssj = dlkssj;
    }

    public String getDljssj() {
        return dljssj;
    }

    public void setDljssj(String dljssj) {
        this.dljssj = dljssj;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<MenuBean> getMenuBeanList() {
        return menuBeanList;
    }

    public void setMenuBeanList(List<MenuBean> menuBeanList) {
        this.menuBeanList = menuBeanList;
    }

    public String getSsbmname() {
        return ssbmname;
    }

    public void setSsbmname(String ssbmname) {
        this.ssbmname = ssbmname;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getRolesname() {
        return rolesname;
    }

    public void setRolesname(String rolesname) {
        this.rolesname = rolesname;
    }


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getMmyxq() {
        return mmyxq;
    }

    public void setMmyxq(String mmyxq) {
        this.mmyxq = mmyxq;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public int getKqipxz() {
        return kqipxz;
    }

    public void setKqipxz(int kqipxz) {
        this.kqipxz = kqipxz;
    }

    public String getIpaddress1() {
        return ipaddress1;
    }

    public void setIpaddress1(String ipaddress1) {
        this.ipaddress1 = ipaddress1;
    }

    public String getIpaddress2() {
        return ipaddress2;
    }

    public void setIpaddress2(String ipaddress2) {
        this.ipaddress2 = ipaddress2;
    }

    public String getIpaddress3() {
        return ipaddress3;
    }

    public void setIpaddress3(String ipaddress3) {
        this.ipaddress3 = ipaddress3;
    }

    public int getZt() {
        return zt;
    }

    public void setZt(int zt) {
        this.zt = zt;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    public String getCzryzh() {
        return czryzh;
    }

    public void setCzryzh(String czryzh) {
        this.czryzh = czryzh;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getPreipaddress() {
        return preipaddress;
    }

    public void setPreipaddress(String preipaddress) {
        this.preipaddress = preipaddress;
    }

    public String getSfmj() {
        return sfmj;
    }

    public void setSfmj(String sfmj) {
        this.sfmj = sfmj;
    }

    public String getYgbh() {
        return ygbh;
    }

    public void setYgbh(String ygbh) {
        this.ygbh = ygbh;
    }

    public String getYhyxq() {
        return yhyxq;
    }

    public void setYhyxq(String yhyxq) {
        this.yhyxq = yhyxq;
    }

    public String getYxqzt() {
        return yxqzt;
    }

    public void setYxqzt(String yxqzt) {
        this.yxqzt = yxqzt;
    }

    public int getSbcs() {
        return sbcs;
    }

    public void setSbcs(int sbcs) {
        this.sbcs = sbcs;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }

    public int getSfsypda() {
        return sfsypda;
    }

    public void setSfsypda(int sfsypda) {
        this.sfsypda = sfsypda;
    }

    @Override
    public String toString() {
        return "User{" +
                "roles='" + roles + '\'' +
                ", ssbmname='" + ssbmname + '\'' +
                ", rn=" + rn +
                ", rolesname='" + rolesname + '\'' +
                ", sfsypda=" + sfsypda +
                '}';
    }
}
