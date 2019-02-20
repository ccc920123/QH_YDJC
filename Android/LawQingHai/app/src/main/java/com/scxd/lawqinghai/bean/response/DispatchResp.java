package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述：卡控数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class DispatchResp {


    private List<DispatchRespListCode> sbkk;

    private String jszt;

    public List<DispatchRespListCode> getSbkk() {
        return sbkk;
    }

    public void setSbkk(List<DispatchRespListCode> sbkk) {
        this.sbkk = sbkk;
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }
}
