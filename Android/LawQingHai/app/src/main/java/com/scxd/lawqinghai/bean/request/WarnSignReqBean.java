package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnSignReqBean {
    
    private String user;
    private String yjxh;
    private String qsjg;
    private String sfcj;
    private String bmbh;

    public WarnSignReqBean(String user, String yjxh, String qsjg, String sfcj,String bmbh) {
        this.user = user;
        this.yjxh = yjxh;
        this.qsjg = qsjg;
        this.sfcj = sfcj;
        this.bmbh=bmbh;
    }
}
