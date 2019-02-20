package com.scxd.lawqinghai.bean.response;

/**
 * 描述：{"meta":{"success":true,"message":"ok"},"data":{"zp":"http://11.101.4.57:8087/Web/getPhotos?id=043ce7f5-a003-46d1-8c62-e8af005710b0","jdsbh":"6301000000002604"}}
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class PrintImageResBean {
    /**
     * 文书校验位
     */
    private String wsjyw="";
    private String jdsbh="";

    public String getWsjyw() {
        return wsjyw;
    }

    public void setWsjyw(String wsjyw) {
        this.wsjyw = wsjyw;
    }

    public String getXh() {
        return jdsbh;
    }

    public void setXh(String xh) {
        this.jdsbh = xh;
    }
}
