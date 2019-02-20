package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.Q24Return;
import com.scxd.beans.common.ListTotal;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pojo.BizLawsInfoBean;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.BizLawsInfoBeanMapper;
import com.scxd.service.LawService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 16:27 2018/9/3
 * @Modified By:
 */
@Service
public class LawServiceImpl implements LawService {
    @Resource
    BizLawsInfoBeanMapper lawsInfoBeanMapper;
    @Resource
    LoggerService loggerService;

    @Override
    public Response getLawList(String querydoc) throws Exception {

        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询条件为空");
        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
        String name = (String) map.get("name");
        //查询违法代码总条数
        int total = lawsInfoBeanMapper.getLawListTotal(name);
        if (total == 0) return new Response().failure("信息条数为0");
        //获取页码和页数大小
        int page = Integer.valueOf((int) map.get("page"));
        int pageSize = Integer.valueOf((int) map.get("pageSize"));
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        List<BizLawsInfoBean> result = lawsInfoBeanMapper.getLawList(name, start, end);
        ListTotal listTotal=new ListTotal(result,total);
        return  new Response().success(listTotal);
    }

    @Override
    public Response getLawDetail(BaseRequest baseRequest) throws Exception {
        String querydoc=baseRequest.getQuerydoc();
        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询条件为空");
        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
        BizLawsInfoBean result = lawsInfoBeanMapper.getLawDetail((String) map.get("id"));
        loggerService.writeOperaLogger(baseRequest.getUser(),18,(String) map.get("id"),baseRequest.getWym());
        return (result != null) ? new Response().success(result) : new Response().failure("获取法律法规详情为空");
    }
}
