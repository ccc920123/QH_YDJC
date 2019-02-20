package com.scxd.service.management.impl;

import com.scxd.beans.common.Role;
import com.scxd.beans.management.MenuRoleBean;
import com.scxd.common.Response;
import com.scxd.dao.management.ifaces.RoleDao;
import com.scxd.service.management.ifaces.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    /**
     * 获取类型
     *
     * @param NAME     角色名称
     * @param TYPE     角色类型
     * @param LEVELNUM 角色层级
     * @return
     */
    @Override
    public Response getRole(String pageNo, String pageSize, String NAME, String TYPE, String LEVELNUM) {
        List list = null;
        try {
            list = roleDao.getRole(pageNo, pageSize, NAME, TYPE, LEVELNUM);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure("数据访问异常");
        }

        return new Response().success(list);
    }

    public boolean addRole(Role role) throws Exception {
        return roleDao.addRole(role);
    }

    public Role getRoleById(int Role_id) throws Exception {

        return roleDao.getRoleById(Role_id);

    }

    public boolean updateRoleById(Role role) throws Exception {

        return roleDao.updateRoleById(role);
    }

    @Override
    public Response deleteMenuRole(int Role_id) {
        try {
            roleDao.deleteRoleMenu(Role_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success("1");

//        else {
//            return new Response().failure("删除角色异常");
//        }
    }

    @Override
    public Response addMenuRole(MenuRoleBean menuRoleBean) {
        boolean flag = false;
        int[] id = menuRoleBean.getId();
        Role role = menuRoleBean.getRole();
        try {
            flag = roleDao.addMenuRole(id, role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().success(e.getMessage() != null ? e.getMessage() : "0");
        }
        if (flag == true) {
            return new Response().success("1");
        }
        else {
            return new Response().success("0");

        }
    }

    @Override
    public Response menuRoleList(int roleId) {
        List<List> list = null;
        try {
            list = roleDao.menuRoleList(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "0");
        }
        return new Response().success(list);
    }

    @Override
    public Response modifyRole(int id) {

        List<List> list = null;
        try {
            list = roleDao.modifyRole(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "0");
        }
        return new Response().success(list);
    }

    @Override
    public Response getRoleList(int pageNo, int pageSize) {
        List<List> list = null;
        try {
            list = roleDao.getRoleList(pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "0");
        }
        return new Response().success(list);
    }

    @Override
    public Response modifyMenuRole(MenuRoleBean menuRoleBean) {
        int flag;
        try {
            flag = roleDao.modifyMenuRole(menuRoleBean);
            if (flag == -1) {
                return new Response().failure("与创建者不一致");
            }
            if (flag == 0) {
                return new Response().failure("数据修改错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据修改错误");
        }
        return new Response().success(flag);
    }

    @Override
    public Response getUser(int roleId) {
        List<Integer> list = null;
        try {
            list = roleDao.getUser(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问错误");
        }
        return new Response().success(list);
    }
}
