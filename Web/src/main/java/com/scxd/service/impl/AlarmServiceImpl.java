package com.scxd.service.impl;

import com.scxd.beans.common.AlarmBean;
import com.scxd.beans.common.ListTotal;
import com.scxd.beans.management.AlarmTableBean;
import com.scxd.common.ImgTools;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.AlarmDao;
import com.scxd.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 平台预警查询实现类
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private AlarmDao alarm;

    @Override
    public Response queryAlarm(Map map) throws Exception {

        //结束日期要设置+一天

        List<AlarmTableBean> result = new ArrayList<>();
        //根据时间和预警状态查询信息条数
        int total = alarm.selectAlarmNum(map);
        if (total == 0) {
        } else {
            //信息条数不为空，查询信息
            int page = (int) map.get("page");
            int start = (page - 1) * 10 + 1;
            int end = page * 10;
            map.put("start", start);
            map.put("end", end);
            result = alarm.selectAlarm(map);
        }
        ListTotal listTotal = new ListTotal();
        listTotal.setTotal(total);
        listTotal.setList(result);
        return new Response().success(listTotal);

    }

    @Override
    public Response queryDetail(String yjxh, HttpServletRequest request) {
        if (StringUtil.isEmpty(yjxh)) {
            return new Response().failure("预警序号不能为空");

        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort();
        AlarmBean alarmBean = alarm.getAlarmDetail(yjxh);
        if (alarmBean != null) {
            List<String> photos = alarmBean.getLjzp();
            if (photos != null && photos.size() > 0) {
                for (int i = 0; i < photos.size(); i++) {
                    String url = photos.get(i);
                    url = ImgTools.getLocalServiceUrl(url, basePath);
                    photos.set(i, url);
                }
            }
            alarmBean.setYjzp(ImgTools.getLocalServiceUrl(alarmBean.getYjzp(), basePath));
            return new Response().success(alarmBean);
        } else {
            return new Response().failure("未能查到信息");
        }

    }

    @Override
    public Response queryStatistics(String ssbmbh) {
        if (StringUtil.isEmpty(ssbmbh)) {
            return new Response().failure("所属部门不能为空");
        }
        List<Map> list = alarm.queryStatistics(ssbmbh);
        return new Response().success(list);
    }

    @Override
    public Response queryStatistics_Piechart(String ssbmbh) {
        if (StringUtil.isEmpty(ssbmbh)) {
            return new Response().failure("所属部门不能为空");
        }
        List<Map> list = alarm.queryStatistics_Piechart(ssbmbh);
        return new Response().success(list);
    }

    @Override
    public Response getStatistic_docment(String ssbmbh) {
        if (StringUtil.isEmpty(ssbmbh)) {
            return new Response().failure("所属部门不能为空");
        }
        List<Map> list = alarm.getStatistic_docment(ssbmbh);
        return new Response().success(list);
    }

    @Override
    public Response getOperationLastTime(String ssbmbh) {
        if (StringUtil.isEmpty(ssbmbh)) {
            return new Response().failure("所属部门不能为空");
        }
        List<Map> list = alarm.getOperationLastTime(ssbmbh);
        return new Response().success(list);
    }

    @Override
    public Response getTenDayData(String ssbmbh) {
        if (StringUtil.isEmpty(ssbmbh)) {
            return new Response().failure("所属部门不能为空");
        }
        List<Map> list = alarm.getTenDayData(ssbmbh);
        return new Response().success(list);
    }

}
