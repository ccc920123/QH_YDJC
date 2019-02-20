package com.scxd.lawqinghai.bean.request;

/**
 * 描述： 布控设置请求类
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class DispatchBean {


    private String user;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public DispatchBean(String user) {
        this.user = user;
    }

    public DispatchBean() {
    }
}
