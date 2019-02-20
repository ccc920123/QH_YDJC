package com.scxd.service.management.ifaces;


import com.scxd.beans.common.Role;
import com.scxd.beans.management.MenuRoleBean;
import com.scxd.common.Response;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
public interface RoleService {
    /**
     * 获取角色
     * @param NAME 角色名称
     * @param TYPE 角色类型
     * @param LEVELNUM 角色层级
     * @return
     */
    public Response getRole(String pageNo, String pageSize, String NAME, String TYPE, String LEVELNUM);

    /**
     * 新增角色
     * @param role 角色实体
     * @return
     */
    public boolean addRole(Role role) throws Exception;

    public Role getRoleById(int Role_id) throws Exception;

    public boolean updateRoleById(Role role) throws Exception;

    public Response deleteMenuRole(int Role_id);

    //增加Menu_Role
    public Response addMenuRole(MenuRoleBean menuRoleBean);

    public Response menuRoleList(int roleId);

    public Response modifyRole(int id);

    public Response getRoleList(int pageNo, int pageSize);

    public Response modifyMenuRole(MenuRoleBean menuRoleBean);

    public Response getUser(int roleId);


}
