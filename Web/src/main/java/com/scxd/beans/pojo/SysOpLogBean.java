package com.scxd.beans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

public class SysOpLogBean {
    private String id;

    private String userid;

    private String orgid;

    private Date opTime;

    private int opType;

    private int opModule;

    private String opContent;

    private int opResult;

    private String opResultInfo;

    private String opIp;

    private Date ctime;

    private String uname;
    private String realname;
    private String checkdigit;
    private int logType;
    private int funcType;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public int getOpModule() {
        return opModule;
    }

    public void setOpModule(int opModule) {
        this.opModule = opModule;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public int getOpResult() {
        return opResult;
    }

    public void setOpResult(int opResult) {
        this.opResult = opResult;
    }

    public String getOpResultInfo() {
        return opResultInfo;
    }

    public void setOpResultInfo(String opResultInfo) {
        this.opResultInfo = opResultInfo;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCheckdigit() {
        return checkdigit;
    }

    public void setCheckdigit(String checkdigit) {
        this.checkdigit = checkdigit;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public int getFuncType() {
        return funcType;
    }

    public void setFuncType(int funcType) {
        this.funcType = funcType;
    }

    @Override
    public String toString() {
        return   userid+orgid+opTime.getTime()+opType+opModule+opContent+opResult+opResultInfo
                +opIp+ctime.getTime()+uname+logType+funcType;
    }
}