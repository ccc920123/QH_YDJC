package com.scxd.service;


import com.scxd.beans.common.MenuBean;
import com.scxd.beans.pojo.SysMenu;
import com.scxd.beans.pojo.SysMenuDetail;
import com.scxd.common.Response;

import java.util.List;

/**
 * Created by hdfs on 2017/9/9.
 */
public interface MenuService {

    public List<SysMenu> getMenusList(String type, String menuId) throws Exception;

    /**
     * 功能描述：保存菜单
     * 作者：齐遥遥
     * 时间：2017-09-11
     * @param menuBeanList
     * @return
     * @throws Exception
     */
    public boolean saveMenus(List<MenuBean> menuBeanList) throws Exception;

    public Response getMenuId(int[] id) throws Exception;

    List<SysMenu> getMenuBeanListByLoginName(String loginname);

    Response selectAllMenu();

    Response selectMenuDetail(int menuId);

    Response selectqx();

    Response selectMenuPression();

    Response saveChange(SysMenuDetail sysMenuDetail);

    Response deleteMenuQx(String id);

}
