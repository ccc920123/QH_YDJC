package com.scxd.dao;

import com.scxd.beans.common.MenuBean;
import com.scxd.common.Response;
import com.scxd.beans.common.Role;
import com.scxd.beans.common.User;

import java.util.List;
import java.util.Map;

/**
 * Created by hdfs on 2018/5/15.
 */
public interface UserDao {

    public User getUserInfoByUsername(String username) throws Exception;

    /**
     * 插入、更新
     *
     * @param user
     * @throws Exception
     */
    public void merge(User user) throws Exception;

    public User getUseById(int id) throws Exception;


    /**
     * 删除
     *
     * @param user_id
     * @throws Exception
     */
    public void delete(String user_id) throws Exception;

    /**
     *重置密码
     * @param user_id
     * @throws Exception
     */
    public void ResetPasswd(int user_id)  throws Exception;


    /**
     * 获取用户列表
     *
     * @yhxm 用户姓名
     * @dlzh 登录账号
     * @bhxj 包含下级（1 是；2 否；）
     * @jgbh 机构编号
     */
    public List<User> selectNextLev(String yhxm, String dlzh, String bhxj, String jgbh, int pageindex, int pagesize) throws Exception;

    public List<Role> getRolesByBmbh(String bmbh)throws Exception;

    public List<Map> getSysUserRoles(String type)throws Exception;

    public Map getSysUserRolesByID(String roleId)throws Exception;

    /**
     * 功能描述：根据用户名获取用户权限列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     * @param loginname
     * @return
     */
    public List<Role> getUserRoles(String loginname) throws Exception;

    /**
     * 功能描述：根据用户名获取用户菜单列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     * @param loginname
     * @return
     */
    public List<MenuBean> getUserMenus(String loginname) throws Exception;

    /**
     * 功能描述：根据用户名获取用户信息
     * 作者：齐遥遥
     * 时间：2017-10-18
     * @param loginname
     * @return
     */
    public User getUserByLoginname(String loginname) throws Exception;

    /**
     * 功能描述：修改用户密码
     * 作者：杨文星
     * 时间：2017-10-21
     * @param loginname
     * @return
     */
    public Response updatePassword(String loginname, String password, String newPassword) throws Exception;


    /**
     *获取登录的用户信息
     * @return
     * @throws Exception
     */
    public String getUserInfo(String user_id) throws Exception;


    public List<Map<String, Object>> getUserSj() throws Exception;

    public Map<String, String> getUserSj2(String username) throws Exception;



}
