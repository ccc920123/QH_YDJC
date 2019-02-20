package com.scxd.service.impl;

import com.scxd.beans.common.User;
import com.scxd.dao.mapper.MonitoringMapper;
import com.scxd.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    private MonitoringMapper mm;

    @Autowired
    private HttpSession session;

    //移动集成指挥接入支队
    @Override
    public List<Map> getIntegratedCommand(int page)throws Exception {

        //获取当前登录用户的部门信息编号，关联查询到所在部门和子部门的
        User user = (User) session.getAttribute("userInfo");
        String bmbh = user.getSsbmbh();
        int start = (page - 1) * 5;
        int end = page * 5;
        //获取当天时间
        Date time = getNowTime();
        List<Map> map = mm.selectIntegratedCommand(bmbh,start,end,time);
        //通过部门编号获取该部门和下级部门总数
        int total = mm.selectTotalByBmbh(bmbh);
        //将统计的总数放入索引为0的list集合中
        map.get(0).put("bmzs",total);
        return map;
    }

    //查询表空间
    @Override
    public Map getTableSpace()throws Exception {
        return mm.selectTableSpace();
    }

    //警务通在线监控 @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Override
    public List<Map> getPoliceService()throws Exception {
        List<Map> map =  mm.selectPoliceService(getNowTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < map.size();i++){
            map.get(i).put("OPTIME",format.format(map.get(i).get("OPTIME")));
        }
        return map;
    }

    //获取当前时间，不需要时分秒 也就是获取的时间为当天的00:00:00
    private Date getNowTime()throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(new Date());
        Date time = sdf.parse(s);
        return time;
    }
}
