package com.scxd.lawqinghai.bean;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @类名 :BaseResponseHeadVO.java
 * @author CHENYUJIN
 * @功能描述:
 * @创建时间:
 */
public class BaseResponseHeadVO {
	/**
	 * 应答信息, 服务器的提示信息
	 */
	private String message;
	/**
	 * 应答码
	 */
	private String code;

	/**
	 * 行数
	 */
	@XStreamOmitField
	private int rownum;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		if("0".equals(code)){
			return 0;
		}else if("1".equals(code)){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public BaseResponseHeadVO(String message, String code, int rownum) {
		super();
		this.message = message;
		this.code = code;
		this.rownum = rownum;
	}

	public BaseResponseHeadVO() {
		super();
	}

}
