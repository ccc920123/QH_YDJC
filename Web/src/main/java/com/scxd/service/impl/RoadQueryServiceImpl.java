package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.pdaBeans.request.PdaFeedBackRequest;
import com.scxd.beans.pdaBeans.response.Q26ReturnBean;
import com.scxd.beans.pdaBeans.response.Q27ReturnBean;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.BizRoadBlocksMapper;
import com.scxd.service.RoadQueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 查询道路,路段代码
 */
@Service
public class RoadQueryServiceImpl implements RoadQueryService {

    @Resource
    BizRoadBlocksMapper bizRoadBlocksMapper;

    @Override
    public Response getRoadItem(String querydoc) throws Exception {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
            List<Q26ReturnBean> list = bizRoadBlocksMapper.getRoadItem(map);
            if (list!=null&&list.size()>0){
                response = new Response().success(list);
            }else{
                response=new Response().failure("未查询到当前部门下的道路");
            }

        } else {
            response = new Response().failure("写入失败,传入的数据不正确");
        }
        return response;
    }

    @Override
    public Response getRoadSegItem(String querydoc) throws Exception {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
            List<Q27ReturnBean> list = bizRoadBlocksMapper.getRoadSegItem(map);
            if (list!=null&&list.size()>0){
                response = new Response().success(list);
            }else{
                response=new Response().failure("未查询到当前道路的路段信息");
            }

        } else {
            response = new Response().failure("写入失败,传入的数据不正确");
        }
        return response;
    }
}
