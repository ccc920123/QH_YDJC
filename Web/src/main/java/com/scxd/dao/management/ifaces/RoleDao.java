package com.scxd.dao.management.ifaces;


import com.scxd.beans.common.Role;
import com.scxd.beans.management.MenuRoleBean;

import java.util.List;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
public interface RoleDao {
    /*
     * 数据访问层
     */


    public List<Role> getRole(String pageNo, String pageSize, String NAME, String TYPE, String LEVELNUM) throws Exception;

    public List<List> getRoleList(int pageNo, int pageSize) throws Exception;

    public boolean addRole(Role role) throws Exception ;

    public Role getRoleById(int Role_id)throws Exception ;

    public boolean updateRoleById(Role role) throws  Exception;

    public boolean deleteRole(int Role_id)throws Exception ;

    public void deleteRoleMenu(int Role_id)throws Exception ;


    public boolean addMenuRole(int[] id, Role role) throws Exception;

    public List<List> modifyRole(int roleId) throws Exception;

    public List<List> menuRoleList(int roleId) throws Exception;

    public int modifyMenuRole(MenuRoleBean menuRoleBean) throws Exception;

    public List<Integer> getUser(int roleId) throws Exception;
}
