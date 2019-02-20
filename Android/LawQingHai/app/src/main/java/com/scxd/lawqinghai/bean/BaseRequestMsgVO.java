package com.scxd.lawqinghai.bean;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * root节点
 *BaseRequestMsgVO.java
 * @author CXY
 * @Description
 * @Create Time 2015-9-16
 */
public class BaseRequestMsgVO{

	@XStreamAsAttribute
	private Object QueryCondition;

	public Object getquerycondition() {
		return QueryCondition;
	}

	public void setquerycondition(Object querycondition) {
		this.QueryCondition = querycondition;
	}

	public BaseRequestMsgVO() {
		super();
	}

	public BaseRequestMsgVO(Object querycondition) {
		super();
		this.QueryCondition = querycondition;
	}

}
