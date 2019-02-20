package com.scxd.lawqinghai.bean.request;

/**
 * 描述：打印回执
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class PrintCallBackReqBean {

    private String xh;
    /***
     * 0 简易
     * 1 强制
     * 2 电子
     */
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    /**
     *
     * @param xh
     * @param type
     */
    public PrintCallBackReqBean(String xh,String type) {
        this.xh = xh;
        this.type=type;
    }
    public PrintCallBackReqBean(String xh) {
        this.xh = xh;
    }
}


