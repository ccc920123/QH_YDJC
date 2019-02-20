package com.scxd.service.management.impl;

import com.scxd.beans.management.Dept;
import com.scxd.common.Response;
import com.scxd.dao.management.ifaces.DeptDao;
import com.scxd.service.management.ifaces.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptDao deptDao;

    /**
     * 插入、更新
     *
     * @param dept
     * @throws Exception
     */
    @Override
    public Response merge(Dept dept) {
        try {
            deptDao.merge(dept);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success();
    }

    /**
     * 查询一组下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public Response selectNextLev(String bmbh, String searchBh, String searchName, String pageNo, String pageSize) {
        List<Dept> list = null;
        try {
            list = deptDao.selectNextLev(bmbh, searchBh, searchName, pageNo, pageSize);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(list);
    }

    /**
     * 不包含下一级
     *
     * @param bmbh
     * @return
     */
    @Override
    public Response selectOne(String bmbh, String searchBh, String searchName) {
        Dept dept = null;
        try {
            dept = deptDao.selectOne(bmbh, searchBh, searchName);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (dept == null) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(dept);
    }

    /**
     * 查询全部下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public Response selectAll(String bmbh,String zdid) {
        List<Dept> list = null;
        try {
            list = deptDao.selectAll(bmbh, zdid);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(list);
    }

    /**
     * 查询全部下级支队dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public Response getZdList(String bmbh) {
        List<Dept> list = null;
        try {
            list = deptDao.getZdList(bmbh);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(list);
    }


    /**
     * 删除一个部门
     *
     * @param bmbh
     * @throws Exception
     */
    @Override
    public Response delete(String bmbh) {
        try {
            deptDao.delete(bmbh);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success();
    }

    /**
     * type代码、层级代码
     *
     * @return
     */
    @Override
    public Response typeList() {
        List list = null;
        try {
            list = deptDao.typeList();
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(list);
    }


}
