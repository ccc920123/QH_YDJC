package com.scxd.dao;


import com.scxd.beans.common.MenuBean;

import java.util.List;

/**
 * Created by hdfs on 2017/9/9.
 */
public interface MenuDao {

    public List<MenuBean> getMenusList(String type, String menuId) throws Exception;

    /**
     * 功能描述：保存菜单
     * 作者：齐遥遥
     * 时间：2017-09-11
     * @param menuBeanList
     * @return
     * @throws Exception
     */
    public boolean saveMenus(List<MenuBean> menuBeanList) throws Exception;


    public List<Integer> getMenuId(int[] id) throws Exception;

}
