package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.Q11Return;
import com.scxd.common.Response;
import com.scxd.dao.mapper.CodeDao;
import com.scxd.service.CodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {

    @Resource
    private CodeDao cd ;

    @Override
    public Response getCode(String querydoc) {
        try{
            Map<String,String> map = (Map<String,String >) JSON.parse(querydoc);
          List<Q11Return> result = cd.selectCode(map.get("bmbh"));
          return (result != null)?new Response().success(result):new Response().failure();
        }catch (Exception e){
            return null;
        }
    }
}
