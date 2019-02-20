package com.scxd.service.impl;

import com.scxd.beans.common.PoliceBean;
import com.scxd.beans.management.FwzbhAndFwzmc;
import com.scxd.common.Response;
import com.scxd.dao.mapper.PoliceDao;
import com.scxd.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    private PoliceDao police;

    @Override
    public Response getPoliceInfo(Map map) throws Exception {
        if (map.size() == 0)return new Response().failure("查询数据为空");
        //查询信息总数
        int total = police.selectPoliceTotal(map);
        map.put("total",total);
        if (total == 0)return new Response().failure("未查询到数据");
        //分页查询
        int page = (int)map.get("pageNo");
        int start = (page - 1) * 10;
        int end = page * 10;
        map.put("start",start);
        map.put("end",end);

        List<PoliceBean> result = police.selectPoliceList(map);
        map.put("police",makeInfo(result));
        return new Response().success(map);
    }

    @Override
    public List<FwzbhAndFwzmc> getFwz(String glbm) throws Exception {
        return police.selectFwz(glbm);
    }

    /**
     * 整理警员信息人员类型
     */
    private  List<PoliceBean> makeInfo( List<PoliceBean> list)throws Exception{

        for (int i = 0 ; i < list.size();i++){
            if (list.get(i).getRylx().equals("1") || list.get(i).getRylx().equals("A"))
                list.get(i).setRylx("民警");
            if (list.get(i).getRylx().equals("2") || list.get(i).getRylx().equals("B"))
                list.get(i).setRylx("协警");
        }
        return list;
    }
}
