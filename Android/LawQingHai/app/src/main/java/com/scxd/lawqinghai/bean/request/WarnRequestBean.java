package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnRequestBean {
    
    private String yjxh;
    private String sjbmbh;

    public WarnRequestBean(String yjxh, String sjbmbh) {
        this.yjxh = yjxh;
        this.sjbmbh = sjbmbh;
    }

    public WarnRequestBean(String yjxh) {
        this.yjxh = yjxh;
    }
    
}
