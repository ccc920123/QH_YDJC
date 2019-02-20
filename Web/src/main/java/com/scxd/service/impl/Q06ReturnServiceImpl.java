package com.scxd.service.impl;

import com.scxd.beans.biz.Q12Return;
import com.scxd.beans.common.ListTotal;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.BizAlarmInfoMapper;
import com.scxd.service.Q06ReturnService;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:18 2018/6/14
 * @Modified By:
 */
@Service
public class Q06ReturnServiceImpl implements Q06ReturnService {

    @Resource
    private BizAlarmInfoMapper bizAlarmInfoMapper;
    @Override
    public Response getYJList(String querydoc) {
        Response response=null;
        List<Q12Return> lists=new ArrayList<>();
        if (StringUtil.isNotEmpty(querydoc)){
            Map<String,Object> map= (Map<String,Object >) JSON.parse(querydoc);
                int page= Integer.valueOf(String.valueOf( map.get("page")));
            int pageSize= Integer.valueOf(String.valueOf(map.get("pageSize")));
            map.put("pagefirst",(page-1)*pageSize+1);
            map.put("pageend",page*pageSize);
          int total=  bizAlarmInfoMapper.getYJlistTotal(map);
          if (total>0){
              lists= bizAlarmInfoMapper.getYJlist(map);

          }
          ListTotal listTotal=new ListTotal();
          listTotal.setList(lists);
          listTotal.setTotal(total);
         response=new Response().success(listTotal);

        }else{
            response=new Response().failure("查询数据不完善");
        }
        return response;
    }

}
