package com.scxd.beans.pdaBeans.response;

import java.util.List;

/**
 * @Auther:陈攀
 * @Description:pda 卡口接口返回数据
 * @Date:Created in 16:16 2018/6/13
 * @Modified By:
 */
public class PdaKKResponse {
    private List<CodeValueCommon> sbkk;
    private  String jszt;

    public List<CodeValueCommon> getSbkk() {
        return sbkk;
    }

    public void setSbkk(List<CodeValueCommon> sbkk) {
        this.sbkk = sbkk;
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }




}
