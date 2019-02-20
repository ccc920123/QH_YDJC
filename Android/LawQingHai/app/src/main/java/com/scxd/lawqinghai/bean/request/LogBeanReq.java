package com.scxd.lawqinghai.bean.request;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/19
 * 修改人：
 * 修改时间：
 */


public class LogBeanReq {

  private  String  wym;
  private String loginname;
  private String indata;


    public String getWym() {
        return wym;
    }

    public void setWym(String wym) {
        this.wym = wym;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getIndata() {
        return indata;
    }

    public void setIndata(String indata) {
        this.indata = indata;
    }

    public LogBeanReq(String wym, String loginname, String indata) {
        this.wym = wym;
        this.loginname = loginname;
        this.indata = indata;
    }
}
