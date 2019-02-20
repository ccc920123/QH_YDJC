package com.scxd.service.impl;

import com.scxd.beans.common.RoadblockExtend;
import com.scxd.common.Response;
import com.scxd.dao.mapper.BizRoadBlock;
import com.scxd.service.RoadblockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *  卡口备案服务接口实现类
 */
@Service
public class RoadblockServiceImpl implements RoadblockService {

    @Autowired
    private BizRoadBlock brb;

    /**
     *  获取卡口备案信息
     * @param map 中的参数有page页码 fwzbh服务站编号 kkmc卡口名称 bmbh部门编号
     */
    @Override
    public Response getRoadblockInfo(Map map) throws Exception {
        if (map.size() == 0)return  new Response().failure("查询数据为空");
        //查询信息总数，并将信息总数装入map，如果总数为0则直接返回，方法结束，
        //若不为0，则分页查询信息
        int total = brb.selectTotal(map);
        map.put("total",total);
        if (total == 0)return  new Response().failure("未能查询到数据");

        //取出页码
        int page = (int)map.get("page");
        int start = (page - 1) * 10;
        int end = page * 10;
        map.put("start",start);
        map.put("end",end);

        List<RoadblockExtend> result = brb.selectRoadblock(map);
        map.put("result",result);
        return new Response().success(map);
    }
}
