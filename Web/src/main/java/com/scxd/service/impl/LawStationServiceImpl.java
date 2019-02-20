package com.scxd.service.impl;

import com.scxd.beans.common.KeyValueBean;
import com.scxd.beans.pojo.BizQstationExtend;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.BizQstation;
import com.scxd.service.LawStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 平台执法站信息查询接口实现类
 */
@Service
public class LawStationServiceImpl implements LawStationService {

    @Autowired
    private BizQstation qstation;

    @Override
    public Map queryLawStation(Map map)throws Exception{
        System.out.println(map);
        //查询信息总数
        int total = qstation.selectTotal(map);
        map.put("total",total);
        if (total == 0){
            return map;
        }
        //查询结果不为0，查询分页查询详情
        int pageNo = (int) map.get("pageNo");
        int start = (pageNo - 1) * 10;
        int end = pageNo * 10;
        map.put("start",start);
        map.put("end",end);
        List<BizQstationExtend> result = qstation.selectQstation(map);
        System.out.println(result);
        map.put("result",result);
        return map;
    }

    /**
     * 根据部门编号查询该部门下的所有执法站信息
     * @param bmbh
     * @return
     */
    @Override
    public Response queryByBmbh(String bmbh) {
        if(StringUtil.isEmpty(bmbh)){
            bmbh="630000000000";
        }
       List<KeyValueBean> list= qstation.queryByBmbh(bmbh);
       return new Response().success(list);
    }
}
