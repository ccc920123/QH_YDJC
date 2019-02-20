package com.scxd.beans.common;

import java.io.Serializable;

/**
 * 文件描述：角色实体类
 * 作者：齐遥遥
 * 时间：2017/9/1
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class Role implements Serializable,Cloneable{

	private int role_id;//主键标识 角色id
	private String name;//角色名称
	private int levelnum;//角色层级 1省 2市 3区县
	private int type;//角色类型 1监管部门 2机构
	private int state;//状态 0停用 1正常
	private String czryzh;//操作人员帐号
	private String czsj;//操作时间
	private String cjbmbh;//创建部门编号
	private int total;
	private int rn;

	@Override
	public Role clone() throws CloneNotSupportedException {
		return (Role) super.clone();
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

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	public String getCjbmbh() {
		return cjbmbh;
	}

	public void setCjbmbh(String cjbmbh) {
		this.cjbmbh = cjbmbh;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
}
