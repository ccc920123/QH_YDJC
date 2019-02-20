package com.scxd.lawqinghai.bean;

/**
 * 
 *Md_cartype.java
 * @author CXY
 * @Description 自定义下拉列表取值
 * @Create Time 2015-6-30
 */
public class Md_cartype {
	//名称
	public String name="";
	//值
	public String value="";
	/**
	 * 构造方法初始化数据
	 * @param name
	 * @param value
	 */
	public Md_cartype(String name, String value){
		this.setName(name);
		this.setValue(value);
	}	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
	/**
	 *返回name
	 * @return 
	 */
	public String getName() {
		return name;
	}
	/**
	 *设置name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *返回value
	 * @return 
	 */
	public String getValue() {
		return value;
	}
	/**
	 *设置value
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
		
}
