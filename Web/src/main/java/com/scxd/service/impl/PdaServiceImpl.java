package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.Q02Return;
import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;
import com.scxd.dao.mapper.SysPdaVersionMapper;
import com.scxd.service.PdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PdaServiceImpl implements PdaService {

    @Autowired
    private SysPdaVersionMapper pdaV;

//    @Override
//    public List<SysPdaVersion> getPdaVersionByPaging(int pagnation, int numLine) {
//        int arg0 = (pagnation - 1 ) * numLine;
//        int arg1 = pagnation * numLine;
//        return pdaV.selectPdaVersion(bmbh, version, desc, arg0,arg1);
//    }

    @Override
    public int getPdaVersionTotal() {
        return pdaV.selectPdaVersionTotal();
    }

    //获取最新Pda版本信息
    @Override
    public Response getPdaVersion(String querydoc) {
        try{
            Map<String,String> map = (Map<String,String>) JSON.parse(querydoc);
            System.out.println(map);
            Q02Return result = pdaV.selectPdaVersionBybmbh(map.get("bmbh"));
            System.out.println(result);
            return (result != null)?new Response().success(result):new Response().failure();
        }catch (Exception e){
            return null;
        }
    }
}
