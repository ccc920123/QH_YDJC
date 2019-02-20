package com.scxd.beans.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文件描述：菜单bean
 * 作者：齐遥遥
 * 时间：2017/9/9
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class MenuBean implements Cloneable {

    /* 菜单ID */
    private String id;

    /* 上级菜单ID */
    private String pId;

    /* 菜单名称 */
    private String name;

    /* 菜单等级 */
    private int level;

    /* 菜单访问路径 */
    private String doPath;

    /* 菜单类型 */
    private String type;

    /* 打开方式 */
    private String target;

    /* 图标 */
    private String icon;

    /* 菜单创建时间 */
    @JsonIgnore
    private String createtime;

    /* 菜单创建用户 */
    @JsonIgnore
    private String createuser;

    /* 菜单修改时间 */
    @JsonIgnore
    private String modifytime;

    /* 菜单修改人 */
    @JsonIgnore
    private String modifyuser;

    /* 有效标志 */
    @JsonIgnore
    private String validity;

    /* 排序值 */
    private int sortcode;

    /* 所属模块 */
    private int mid;

    @Override
    public MenuBean clone() throws CloneNotSupportedException {
        return (MenuBean) super.clone();
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getSortcode() {
        return sortcode;
    }

    public void setSortcode(int sortcode) {
        this.sortcode = sortcode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDoPath() {
        return doPath;
    }

    public void setDoPath(String doPath) {
        this.doPath = doPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
