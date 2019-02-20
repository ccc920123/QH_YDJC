package com.scxd.service;

import com.scxd.beans.pojo.SysRole;
import com.scxd.common.Response;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 16:30 2018/10/12
 * @Modified By:
 */
public interface SysRoleService {
    Response addRole(SysRole role);

    Response menuRoleList(int roleId, int type);

    Response modifyMenuRole(int roleId, int[] menuIds, String loginame);

    Response modifyRole(SysRole role);

    Response deleteMenuRole(int roleId);
}
