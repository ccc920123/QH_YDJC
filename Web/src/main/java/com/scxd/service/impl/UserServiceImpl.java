package com.scxd.service.impl;

import com.scxd.beans.common.MenuBean;
import com.scxd.beans.management.RoleBean;
import com.scxd.beans.pojo.SysUser;
import com.scxd.common.Response;
import com.scxd.beans.common.Role;
import com.scxd.beans.common.User;
import com.scxd.dao.UserDao;
import com.scxd.dao.mapper.SysUserMapper;
import com.scxd.dao.util.Md5Util;
import com.scxd.service.UserService;
import com.szdt.security.des.DESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by hdfs on 2018/5/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private SysUserMapper sysUser;

    @Autowired
    private HttpSession session;

    private static final String SECERT_KEY = "ics";

    @Override
    public User getUserInfoByUsername(String username) throws Exception {
        return userDao.getUserInfoByUsername(username);
    }

    @Override
    public User getUserInfoFromSession(HttpSession session) throws Exception {

        User user = (User) session.getAttribute("userInfo");
        return user;
    }

    /**
     * 插入、更新
     *
     * @param user
     * @throws Exception
     */
    @Override
    public Response update(SysUser user) {
        try {
            int n = sysUser.getUserBySfzhmUpdate(user);
            //判断身份证是否注册过
            if (n > 0) {
                return new Response().failure("该身份证号码已注册");
            }
            int k = sysUser.getUserByYgbhUpdate(user);
            //判断身份证是否注册过
            if (k > 0) {
                return new Response().failure("该警员/人员编号已注册");
            }
            int n1 = sysUser.updateBySysUser(user);
            if (n1  < 1) {
                return new Response().failure("修改失败");
            }

            RoleBean roleBean = new RoleBean();
            roleBean.setUser_id(user.getUserId());
            roleBean.setRole_id(user.getRoleId());
            roleBean.setCreater(user.getCzryzh());

            int m = sysUser.updateUserByRole(roleBean);
            if (m < 1) {
                return new Response().failure("修改失败");
            }
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success();
    }

    /**
     * @param id
     * @return
     * @description 用户获取逻辑的具体实现
     */
    //  @Transactional(readOnly = false)   //必须设置为false,因为此处切入了日志的保存逻辑
    public Response getUserById(String id) {
        User user = null;
        try {
            user = sysUser.getUseById(id);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (user == null) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(user);
    }

    @Override
    public Response getRolesByBmbh(String bmbh) {
        List<Role> list = null;
        try {
            list = userDao.getRolesByBmbh(bmbh);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到相关角色信息");
        }
        return new Response().success(list);
    }

    @Override
    public Response getSysUserRoles(String type) {
        List<Map> list = null;
        try {
            list = userDao.getSysUserRoles(type);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到相关角色信息");
        }
        return new Response().success(list);
    }

    @Override
    public Response getSysUserRolesByID(String roleId) {
        Map list = new HashMap();
        try {
            list = userDao.getSysUserRolesByID(roleId);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list == null || list.size() == 0) {
            return new Response().failure("没有查到所属角色类型信息");
        }
        return new Response().success(list);
    }

    @Override
    public Response ResetPasswd(int user_id) {
        try {
            User user = sysUser.getUseById(String.valueOf(user_id));
            String key = user.getUser_id() + "abc_1234" + user.getRealname();
            String password = Md5Util.md5(key);
            int n = sysUser.ResetPasswd(user_id, password);
            if (n == 0) {
                return new Response().failure("重置密码失败");
            }
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success();
    }

    /**
     * 功能描述：根据用户名获取用户权限列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @param loginname
     * @return
     */
    @Override
    public Response getUserRoles(String loginname) {

        List<Role> roleList = null;

        try {
            roleList = userDao.getUserRoles(loginname);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }

        return new Response().success(roleList);
    }

    /**
     * 功能描述：根据用户名获取用户菜单列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @param loginname
     * @return
     */
    @Override
    public Response getUserMenus(String loginname) {

        List<MenuBean> menuBeanList = null;

        try {
            menuBeanList = userDao.getUserMenus(loginname);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }

        return new Response().success(menuBeanList);
    }

    @Override
    public Response getUserByLoginname(String loginname) {

        User user = null;
        try {
            user = userDao.getUserByLoginname(loginname);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success(user);
    }

    /**
     * 更改密码
     * 杨文星
     * 2017-10-21
     */
    //@Transactional(readOnly = false)   //必须设置为false,因为此处切入了日志的保存逻辑
    @Override
    public Response updatePassword(String loginname, String password, String newPassword) {
        try {
            return userDao.updatePassword(loginname, password, newPassword);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }

    @Override
    public Response selectNextLev(String yhxm, String dlzh, String bhxj, String jgbh, int pageindex, int pagesize) {
        List<User> list = null;
        try {
            list = userDao.selectNextLev(yhxm, dlzh, bhxj, jgbh, pageindex, pagesize);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        if (list.size() == 1) {
            return new Response().failure("没有查到相关信息");
        }
        return new Response().success(list);
    }

    /**
     * 删除
     *
     * @param user_id
     * @throws Exception
     */
    @Override
    public Response delete(String user_id, String tempId) {

        if (user_id.equals(tempId)) {
            return new Response().failure("不允许删除自己的账号");
        } else {

            try {
                // 判断当前要删除的用户是否活跃状态，如果是，则不允许删除
//                boolean flag = commonDao.checkIfUserIsActive(null, user_id);

//                if(flag) {
//                    throw new Exception("当前要删除的用户正在活跃状态，不能删除！");
//                }

            } catch (Exception e) {
                return new Response().failure(e.getMessage() != null ? e.getMessage() : "删除用户异常");
            }
        }

        try {
            userDao.delete(user_id);
        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success();
    }

    @Override
    public String getUserInfo(String user_id) {
        String sfzhm = null;
        try {
            sfzhm = userDao.getUserInfo(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sfzhm;
    }

    //修改用户密码
    @Override
    public boolean updatePwd(Map map) throws Exception {

        User user = (User) session.getAttribute("userInfo");
        //判断输入的旧密码与当前登录用户的密码是否一致
       String oldp=(String) map.get("oldpwd");
        String pwd = sysUser.selectPwd(user.getLoginname(), user.getSsbmbh());
        DESUtil desUtil = new DESUtil(SECERT_KEY);
        String oldpwd=Md5Util.md5(user.getUser_id()+desUtil.decrypt(oldp)+user.getRealname());
        if (!oldpwd.equals(pwd)) {
            return false;
        } else {
            String newpwd=Md5Util.md5(user.getUser_id()+desUtil.decrypt((String) map.get("newpwd"))+user.getRealname());
            map.remove("oldpwd");
            map.put("newpwd",newpwd);
            map.put("bmbh", user.getSsbmbh());
            map.put("user", user.getLoginname());
        }

        int num = sysUser.updatePassword(map);
        return (num != 0) ? true : false;
    }

    //用户中心信息显示补充查询
    @Override
    public Map getUserInfor(String loginname, String bmbh) throws Exception {
        Map map = sysUser.selectUserInfo(loginname, bmbh);
        return map;
    }

    @Override
    public Response addUser(SysUser user) {
        try {
            String sfzmhm = user.getSfzhm();
            int n = sysUser.getUserBySfzhm(sfzmhm);
            //判断身份证是否注册过
            if (n > 0) {
                return new Response().failure("该身份证号码已注册");
            }
            String loginname = user.getLoginname();
            int m = sysUser.getUserByLoginname(loginname);
            //判断身份证是否注册过
            if (m > 0) {
                return new Response().failure("该登录账号已注册");
            }
            String ygbh = user.getYgbh();
            int k = sysUser.getUserByYgbh(ygbh);
            //判断身份证是否注册过
            if (k > 0) {
                return new Response().failure("该警员/人员编号已注册");
            }
            //添加新增项目主键
            String userid = UUID.randomUUID().toString();
//            int userid = sysUser.getSysUserNextID();
            user.setUserId(userid);
            String key = userid + "abc_1234" + user.getRealname();
            String password = Md5Util.md5(key);
            user.setPassword(password);

            RoleBean roleBean = new RoleBean();
            roleBean.setUser_id(user.getUserId());
            roleBean.setRole_id(user.getRoleId());
            roleBean.setCreatetime(new Date());
            roleBean.setCreater(user.getCzryzh());
            int role = sysUser.addUserByRole(roleBean);
            if (role > 0) {
                int num = sysUser.addUserByUser(user);
                if (num > 0) {

                } else {
                    return new Response().failure("添加用户失败");
                }
            } else {
                return new Response().failure("添加用户失败");
            }

        } catch (Exception e) {
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
        return new Response().success();
    }
}
