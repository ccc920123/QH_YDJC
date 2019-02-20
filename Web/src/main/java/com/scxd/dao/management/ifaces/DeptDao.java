package com.scxd.dao.management.ifaces;


import com.scxd.beans.management.Dept;

import java.util.List;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
public interface DeptDao {

    /**
     * 插入、更新
     *
     * @param dept
     * @throws Exception
     */
    public void merge(Dept dept) throws Exception;

    /**
     * 查询一组下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    public List<Dept> selectNextLev(String bmbh, String searchBh, String searchName, String pageNo, String pageSize) throws Exception;

    /**
     * 查询一个
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    public Dept selectOne(String bmbh, String searchBh, String searchName) throws Exception;

    /**
     * 查询全部下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    public List<Dept> selectAll(String bmbh, String zdid) throws Exception;

    /**
     * 查询全部下级支队dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    public List<Dept> getZdList(String bmbh) throws Exception;

    /**
     * 删除一个部门
     *
     * @param bmbh
     * @throws Exception
     */
    public void delete(String bmbh) throws Exception;

    /**
     * type代码、层级代码
     * type、type_id、dept、levelnum
     *
     * @return
     * @throws Exception
     */
    public List typeList() throws Exception;

}
