package com.scxd.beans.pdaBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:09 2018/5/25
 * @Modified By:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@ApiModel(value ="ICS_SERVICE")
public class BaseRequest {
    @ApiModelProperty(value = "接口序列号", required = true)
    public String jkxlh;
    @ApiModelProperty(value = "接口ID", required = true)
    public String jkid;
    @ApiModelProperty(value = "时间戳", required = true)
    public String sjc;
    @ApiModelProperty(value = "用户", required = true)
    public String user;
    @ApiModelProperty(value = "PDA唯一码", required = true)
    public String wym;
    @ApiModelProperty(value = "写入参数")
    public  String writedoc;
    @ApiModelProperty(value = "查询参数")
    public  String querydoc;

    public String getJkxlh() {
        return jkxlh;
    }

    public void setJkxlh(String jkxlh) {
        this.jkxlh = jkxlh;
    }

    public String getJkid() {
        return jkid;
    }

    public void setJkid(String jkid) {
        this.jkid = jkid;
    }

    public String getSjc() {
        return sjc;
    }

    public void setSjc(String sjc) {
        this.sjc = sjc;
    }

    public String getWritedoc() {
        return writedoc;
    }

    public void setWritedoc(String writedoc) {
        this.writedoc = writedoc;
    }

    public String getQuerydoc() {
        return querydoc;
    }

    public void setQuerydoc(String querydoc) {
        this.querydoc = querydoc;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWym() {
        return wym;
    }

    public void setWym(String wym) {
        this.wym = wym;
    }
}
