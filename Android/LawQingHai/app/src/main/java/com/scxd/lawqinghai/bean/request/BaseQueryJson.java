package com.scxd.lawqinghai.bean.request;

/**
 * 描述：查询JSON  基类
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/12
 * 修改人：
 * 修改时间：
 */


public class BaseQueryJson {

    private  String wym;
    private  String jkxlh;
    private  String jkid;
    private  String sjc;
    private  String user="";
    private String querydoc;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWym() {
        return wym;
    }

    public void setWym(String wym) {
        this.wym = wym;
    }

    public String getJkxlh() {
        return jkxlh;
    }

    public void setJkxlh(String jkxlh) {
        this.jkxlh = jkxlh;
    }

    public String getJkid() {
        return jkid;
    }

    public void setJkid(String jkid) {
        this.jkid = jkid;
    }

    public String getSjc() {
        return sjc;
    }

    public void setSjc(String sjc) {
        this.sjc = sjc;
    }

    public String getQueryDoc() {
        return querydoc;
    }

    public void setQueryDoc(String queryDoc) {
        querydoc = queryDoc;
    }
}
