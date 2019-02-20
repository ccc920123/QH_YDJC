package com.scxd.service;

import com.scxd.beans.pojo.SysUser;
import com.scxd.common.Response;
import com.scxd.beans.common.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hdfs on 2018/5/15.
 */
public interface UserService {

    public User getUserInfoByUsername(String username) throws Exception;

    public User getUserInfoFromSession(HttpSession session) throws Exception;

    /**
     * 用户注册逻辑
     *
     * @param user
     */
//    public Response merge(User user);

    /**
     * 用户获取逻辑
     *
     * @param id
     * @return
     */
    public Response getUserById(String id);


    /**
     * 获取用户列表
     *
     * @yhxm 用户姓名
     * @dlzh 登录账号
     * @bhxj 包含下级（1 是；2 否；）
     * @jgbh 机构编号
     */
    public Response selectNextLev(String yhxm, String dlzh, String bhxj, String jgbh, int pageindex, int pagesize);

    /**
     * 删除
     *
     * @param user_id
     * @throws Exception
     */
    public Response delete(String user_id, String tempId);

    public Response getRolesByBmbh(String bmbh);

    public Response getSysUserRoles(String type);

    public Response getSysUserRolesByID(String roleId);

    /**
     * 重置密码
     *
     * @param user_id
     */
    public Response ResetPasswd(int user_id);

    /**
     * 功能描述：根据用户名获取用户权限列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @param loginname
     * @return
     */
    public Response getUserRoles(String loginname);

    /**
     * 功能描述：根据用户名获取用户菜单列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @param loginname
     * @return
     */
    public Response getUserMenus(String loginname);

    /**
     * 功能描述：根据用户名获取用户信息
     * 作者：齐遥遥
     * 时间：2017-10-18
     *
     * @param loginname
     * @return
     */
    public Response getUserByLoginname(String loginname);

    public Response updatePassword(String loginname, String password, String newPassword);


    public String getUserInfo(String user_id);

    //修改用户密码
    boolean updatePwd(Map map)throws Exception;

    //用户个人中心信息显示补充查询
    Map getUserInfor(String loginname,String bmbh)throws Exception;

    Response addUser(SysUser user);

    Response update(SysUser user);
}
