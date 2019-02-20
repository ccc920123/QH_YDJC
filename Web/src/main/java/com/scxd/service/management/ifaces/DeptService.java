package com.scxd.service.management.ifaces;


import com.scxd.beans.management.Dept;
import com.scxd.common.Response;

/**
 * 标题：部门管理
 * 增删改查
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
public interface DeptService {

    /**
     * 插入、更新
     *
     * @param dept
     * @throws Exception
     */
    public Response merge(Dept dept);

    /**
     * 查询一组下级dept
     * 包含下一级
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    public Response selectNextLev(String bmbh, String searchBh, String searchName, String pageNo, String pageSize);

    /**
     * 不包含下一级
     * @param bmbh
     * @return
     */
    public Response selectOne(String bmbh, String searchBh, String searchName);

    /**
     * 查询全部下级dept
     * @param bmbh
     * @return
     * @throws Exception
     */
    public Response selectAll(String bmbh, String zdid);

    /**
     * 查询全部下级支队dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    public Response getZdList(String bmbh);


    /**
     * 删除一个部门
     *
     * @param bmbh
     * @throws Exception
     */
    public Response delete(String bmbh);


    /**
     * type代码、层级代码
     * @return
     */
    public Response typeList();

}
