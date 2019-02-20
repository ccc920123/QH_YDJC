package com.scxd.service.impl;

import com.scxd.beans.pojo.SysMenuRole;
import com.scxd.beans.pojo.SysRole;
import com.scxd.common.Response;
import com.scxd.dao.mapper.SysMenuRoleMapper;
import com.scxd.dao.mapper.SysRoleMapper;
import com.scxd.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 16:31 2018/10/12
 * @Modified By:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysMenuRoleMapper sysMenuRoleMapper;

    @Override
    public Response addRole(SysRole role) {
        String roleName = role.getName();
        SysRole sysRole = sysRoleMapper.getSysRole(roleName);
        if (sysRole != null) {
            return new Response().failure("已存在相同的角色名");
        }
        int i = sysRoleMapper.insertSelective(role);
        if (i > 0) {
            return new Response().success();
        } else {
            return new Response().failure("增加失败");
        }

    }

    /**
     * 加载角色菜单树
     *
     * @param roleId
     * @param type
     * @return
     */
    @Override
    public Response menuRoleList(int roleId, int type) {
        List<Map<String, String>> list = sysRoleMapper.menuRoleList(roleId, type);
        return new Response().success(list);
    }

    @Override
    public Response modifyMenuRole(int roleId, int[] menuIds, String loginame) {
        if (menuIds == null && menuIds.length == 0) {
            return new Response().failure("未选择菜单");
        }
        sysMenuRoleMapper.deleteByRoleId(roleId);
        List<SysMenuRole> sysMenuRoles = new ArrayList<>();
        for (int i = 0; i < menuIds.length; i++) {
            SysMenuRole sysMenuRole = new SysMenuRole();
            sysMenuRole.setRoleId(roleId);
            sysMenuRole.setMenuId(menuIds[i]);
            sysMenuRole.setCreater(loginame);
            sysMenuRoles.add(sysMenuRole);
        }
        int i = sysMenuRoleMapper.insertArray(sysMenuRoles);
        if (i > 0) {
            return new Response().success();
        } else {
            return new Response().failure("授权失败");
        }
    }

    @Override
    public Response modifyRole(SysRole role) {
        String roleName = role.getName();
        SysRole sysRole = sysRoleMapper.getSysRole(roleName);
        if (sysRole != null) {
            return new Response().failure("已存在相同的角色名");
        }
        int i = sysRoleMapper.updateByBean(role);
        if (i > 0) {
            return new Response().success();
        } else {
            return new Response().failure("修改失败");
        }
    }

    @Override
    public Response deleteMenuRole(int roleId) {
        sysMenuRoleMapper.deleteByRoleId(roleId);
        int i = sysRoleMapper.deleteByPrimaryKey(roleId);
        if (i > 0) {
            return new Response().success();
        } else {
            return new Response().failure("删除失败");
        }

    }
}
