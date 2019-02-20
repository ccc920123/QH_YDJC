package com.scxd.service.impl;

import com.scxd.beans.common.KeyValueBean;
import com.scxd.beans.common.MenuBean;
import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysMenu;
import com.scxd.beans.pojo.SysMenuDetail;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.MenuDao;
import com.scxd.dao.mapper.CodeDao;
import com.scxd.dao.mapper.SysMenuDetailMapper;
import com.scxd.dao.mapper.SysMenuMapper;
import com.scxd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hdfs on 2017/9/9.
 */
@Component
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;
    @Resource
    private CodeDao codeDao;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysMenuDetailMapper sysMenuDetailMapper;
    @Autowired
    private HttpSession session;


    @Override
    public List<SysMenu> getMenusList(String type, String menuId) throws Exception {
        List<SysMenu> sysMenuList = sysMenuMapper.selectAllMenu();
        return sysMenuList ;
    }

    @Override
    public boolean saveMenus(List<MenuBean> menuBeanList) throws Exception {
        return menuDao.saveMenus(menuBeanList);
    }

    @Override
    public Response getMenuId(int[] id) throws Exception {
        List<Integer> list = null;
        list = menuDao.getMenuId(id);
        return new Response().success(list);
    }

    @Override
    public List<SysMenu> getMenuBeanListByLoginName(String loginname) {
        List<SysMenu> sysMenuList = sysMenuMapper.selectByUserName(loginname);

        return sysMenuList ;
    }

    @Override
    public Response selectAllMenu() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectAllMenu();

        return new Response().success(sysMenuList);
    }

    @Override
    public Response selectMenuDetail(int menuId) {
        List<SysMenuDetail> sysMenuDetailList = sysMenuDetailMapper.selectMenuDetail(menuId);
        return new Response().success(sysMenuDetailList);
    }

    @Override
    public Response selectMenuPression() {
        User user = (User) session.getAttribute("userInfo");
        List<Map> sysMenuDetailList = sysMenuDetailMapper.selectMenuPression(user.getUser_id());
        return new Response().success(sysMenuDetailList);
    }

    @Override
    public Response selectqx() {
        List<KeyValueBean> list = codeDao.getKeyValueByDmlb("1038");
        return new Response().success(list);
    }

    @Override
    public Response saveChange(SysMenuDetail sysMenuDetail) {
        String id = sysMenuDetail.getId();
        int parentid = sysMenuDetail.getParentid();
        int qxid = sysMenuDetail.getQxid();
        SysMenu sysMenu= sysMenuDetailMapper.selectMenuDetailByMenuIDAndQXID(parentid,qxid);
        if (sysMenu == null){
            return new Response().failure("修改失败");
        }
        if (StringUtil.isNotEmpty(id)){//修改
            if (sysMenu!=null){
                sysMenu.setSfxzjy(sysMenuDetail.getSfxzjy());
            }
            int i=sysMenuDetailMapper.updateSfxzjy(sysMenu);
            if (i>0){
                return new Response().success();
            }else{
                return new Response().failure("修改失败");
            }

        }else {//新增
            if (sysMenu!=null){
                return new Response().failure("已存在当前功能权限");
            }else{
                SysMenu sysMenu1=new SysMenu();
                sysMenu1.setLevelnum(4);
                sysMenu1.setSfxzjy(sysMenuDetail.getSfxzjy());
                sysMenu1.setQxid(sysMenuDetail.getQxid());
                sysMenu1.setName(sysMenuDetail.getQxName());
                sysMenu1.setType(sysMenuDetail.getType());
                sysMenu1.setParentid(sysMenuDetail.getMenuId());
             int i=   sysMenuMapper.insertSelective(sysMenu1);
             if (i>0){
                 return new Response().success();
             }else{
                 return new Response().failure("新增失败");
             }

            }

        }


    }

    @Override
    public Response deleteMenuQx(String id) {
       int i= sysMenuDetailMapper.deleteBYQXID(id);
       if (i>0){
           return new Response().success();
       }else{
           return new Response().failure("删除失败");
       }

    }



}
