package com.scxd.service.management.impl;

import com.scxd.beans.common.ListTotal;
import com.scxd.beans.management.ParamsBean;
import com.scxd.common.Response;
import com.scxd.dao.mapper.SysDepartmentParamMapper;
import com.scxd.dao.mapper.SysParamMapper;
import com.scxd.service.common.impl.LibServiceImpl;
import com.scxd.service.management.ifaces.SystemParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 作者: 张翔
 * 公司：四川星盾科技股份有限公司
 * 部门：技术部 PDA
 * 创建时间: 2018/6/20 下午 06:01
 * 描述：
 * 修改人：
 * 修改时间：
 */
@Service
public class SystemParamServiceImpl implements SystemParamService {

    @Resource
    private SysParamMapper paramsDao;
    @Resource
    private LibServiceImpl libService;
    @Resource
    private SysDepartmentParamMapper sysDepartmentParamMapper;


    @Override
    public Response queryParams(String bmbh, String csmc, String page, String pagesize) {

        Response response = null;
        List<ParamsBean> list = null;

        try {
            int count = paramsDao.getParamCount(bmbh, csmc);
            if (count > 0) {
                list = paramsDao.getParamMessage(bmbh, csmc, page, pagesize);
                if (list != null && list.size() > 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("total", count);
                    map.put("datas", list);
                    response = new Response().success(map);
                } else {
                    response = new Response().failure("未查询到数据");
                }
            } else {
                response = new Response().failure("未查询到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure("查询数据异常");
        }

        return response;
    }

    @Override
    public Response getDepartMentParamsMessage(String bmbh, String csmc, String page, String pagesize) {
        Response response = null;
        List<ParamsBean> list = new ArrayList<>();

        List<String> params = sysDepartmentParamMapper.getAllParamDM(csmc, page, pagesize);
        int count = params.size();
        if (count > 0) {
            for (int i = 0; i < params.size(); i++) {
                ParamsBean paramsBean = sysDepartmentParamMapper.getDepartParamMessage(bmbh, params.get(i));
                list.add(paramsBean);
            }
        }

        ListTotal listTotal = new ListTotal(list, count);
        response = new Response().success(listTotal);
        return response;
    }

    public Response updateParams(String bmbh, String id, String csz) {
        Response response = null;
        int i = paramsDao.updateParams(id, csz);
        if (i > 0) {
            //跟新缓存
            libService.libCache();
            response = new Response().success();
        } else {
            response = new Response().failure("更新失败");
        }
        return response;
    }

    @Override
    public Response addParams(ParamsBean paramsBean) {
        if (paramsBean!=null){
            paramsBean.setId(UUID.randomUUID().toString());
        }
       int i= sysDepartmentParamMapper.insertBean(paramsBean);
        if (i>0){
            return   new Response().success();
        }
        return new Response().failure("操作失败");
    }

    @Override
    public Response deleteParams(String id) {
        int i= sysDepartmentParamMapper.deleteByID(id);
        if (i>0){
            return   new Response().success();
        }
        return new Response().failure("操作失败");
    }

    @Override
    public Response updateDepartmentParams(Map map) {
        int i= sysDepartmentParamMapper.updateDepartmentParams(map);
        if (i>0){
            return   new Response().success();
        }
        return new Response().failure("操作失败");
    }
}
