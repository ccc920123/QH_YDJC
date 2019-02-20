package com.scxd.lawqinghai.bean.response;

/**
 * 描述：请求字典的类型表
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/13
 * 修改人：
 * 修改时间：
 */


public class DicationResBean {

    private String code;
    private String name;
    private String type;
    private boolean flg;

    public boolean isFlg() {
        return flg;
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
    }

    public DicationResBean(String code, String name, String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    public DicationResBean() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
