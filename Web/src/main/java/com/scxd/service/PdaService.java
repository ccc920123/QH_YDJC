package com.scxd.service;

import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;

import java.util.List;

public interface PdaService {
//    /*通过分页查询版本信息*/
//    public List<SysPdaVersion> getPdaVersionByPaging(int pagnation, int numLine);
    /*查询版本信息总数*/
    public int getPdaVersionTotal();
    //通过部门编号去查询PDA版本信息
    public Response getPdaVersion(String querydoc);
}



