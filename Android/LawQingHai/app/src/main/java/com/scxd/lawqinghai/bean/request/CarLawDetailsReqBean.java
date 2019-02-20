package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawDetailsReqBean {
    
    private String xh;
    private String cxly;

    public CarLawDetailsReqBean(String xh, String cxly) {
        this.xh = xh;
        this.cxly = cxly;
    }

    public CarLawDetailsReqBean(String xh) {
        this.xh = xh;
    }
}
