package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnJpushBean {
    
    private String user;
    private String bmbh;
    private String jd;
    private String wd;

    public WarnJpushBean(String user, String bmbh, String jd, String wd) {
        this.user = user;
        this.bmbh = bmbh;
        this.jd = jd;
        this.wd = wd;
    }

}
