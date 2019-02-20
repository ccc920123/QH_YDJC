package com.scxd.controller.management;

import com.scxd.beans.common.User;
import com.scxd.beans.common.UserExtend;
import com.scxd.beans.pojo.SysUser;
import com.scxd.common.Response;
import com.scxd.common.StringEx;
import com.scxd.common.StringUtil;
import com.scxd.service.CommonService;
import com.scxd.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述：用户管理
 * 作者：齐遥遥
 * 时间：2017/9/1
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：zjc
 * 修改时间：2017-10-12
 */
@RestController
@RequestMapping("/users")
public class UserController {
    int OP_MODULE=12;
    @Resource
    private UserService userService;

    @Resource
    private CommonService commonService;
    /**
     * Log4j日志处理
     */
    private static final Logger log = Logger.getLogger(UserController.class);


    /**
     * 插入、更新
     *
     * @param user
     * @throws Exception
     */
    @RequestMapping(value = "/management/user/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public Response update(@RequestBody @Valid SysUser user, HttpServletRequest request, HttpSession session) {
        User cachedUser = null;
        try {
            cachedUser = commonService.getUserInfoFromSession(session);
            user.setCzryzh(cachedUser.getLoginname());
            System.out.println(user);
        } catch (Exception e) {
            user.setCzryzh("admin");
        }
        return userService.update(user);
    }

    /**
     * 插入、更新
     *
     * @param user
     * @throws Exception
     */
    @RequestMapping(value = "/management/user/add", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public Response addUser(@RequestBody @Valid SysUser user, HttpServletRequest request, HttpSession session) {
        User cachedUser = null;
        try {
            cachedUser = commonService.getUserInfoFromSession(session);
            user.setCzryzh(cachedUser.getLoginname());
            System.out.println(user);
        } catch (Exception e) {
            user.setCzryzh("admin");
        }
        return userService.addUser(user);
    }
    /**
     * 获取用户列表
     *
     * @yhxm 用户姓名
     * @dlzh 登录账号
     * @bhxj 包含下级（1 是；2 否；）
     * @jgbh 机构编号
     */
    @RequestMapping(value = "/management/user/getUsers", method = RequestMethod.GET, produces = "application/json")
    public Response selectNextLev(String yhxm, String dlzh, String bhxj, String jgbh, int pageindex, int pagesize
            , HttpSession session) {
        if (StringUtil.isEmpty(jgbh)) {
            User cachedUser = null;
            try {
                cachedUser = commonService.getUserInfoFromSession(session);
                jgbh = cachedUser.getSsbmbh();
            } catch (Exception e) {
            }
        }
        return userService.selectNextLev(yhxm, dlzh, bhxj, jgbh, pageindex, pagesize);
    }

    /**
     * 删除
     *
     * @param user_id
     * @throws Exception
     */
    @RequestMapping(value = "/management/user/delete", method = RequestMethod.DELETE)
    public Response delete(String user_id, HttpSession session) {
        User cachedUser = null;
        String tempId = "0";
        try {
            cachedUser = commonService.getUserInfoFromSession(session);
            tempId = cachedUser.getUser_id();
        } catch (Exception e) {
        }

        return userService.delete(user_id, tempId);
    }

    /**
     * @param user_id
     * @return
     * @description 获取指定Id的用户getUserById(@PathVariable("user_id") int user_id)
     */
    @RequestMapping(value = "/management/user/getUserById", method = RequestMethod.GET, produces = "application/json")
    public Response getUserById(String user_id) {
//		log.debug("查询用户 :" + user);
        return userService.getUserById(user_id);
    }

    /**
     * 根据部门编号获取角色信息
     */
    @RequestMapping(value = "/management/user/getRolesByBmbh", method = RequestMethod.GET, produces = "application/json")
    public Response getRolesByBmbh(String bmbh) {
//		log.debug("查询用户 :" + user);
        return userService.getRolesByBmbh(bmbh);
    }
    /**
     * 根据部门编号获取角色信息
     */
    @RequestMapping(value = "/management/user/getSysUserRoles", method = RequestMethod.GET, produces = "application/json")
    public Response getSysUserRoles(String type) {
//		log.debug("查询用户 :" + user);
        return userService.getSysUserRoles(type);
    }

    @RequestMapping(value = "/management/user/getSysUserRolesByID", method = RequestMethod.GET, produces = "application/json")
    public Response getSysUserRolesByID(String roleId) {
//		log.debug("查询用户 :" + user);
        return userService.getSysUserRolesByID(roleId);
    }

    /**
     * 重置密码
     *
     * @param user_id
     */
    @RequestMapping(value = "/management/user/ResetPassWord", method = RequestMethod.GET, produces = "application/json")
    public Response ResetPasswd(int user_id) {
        return userService.ResetPasswd(user_id);
    }

    /**
     * 获取当前登录用户真实姓名
     * 杨文星
     * 2017-10-21
     */
    @RequestMapping(value = "/getThisUser", method = RequestMethod.GET)
    public Response getThisUser(HttpSession session) {
        User thisUser = null;
        try {
            thisUser = commonService.getUserInfoFromSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sfzhm=userService.getUserInfo(thisUser.getUser_id());


        Map user = new HashMap();
        user.put("loginname", thisUser.getLoginname());  //登录账号
        user.put("realname", thisUser.getRealname());  //真实姓名
        user.put("ssbm", thisUser.getSsbmname());//所属部门名称
        user.put("sfzhm", sfzhm);//身份证号码
        user.put("lxdh", thisUser.getLxdh());//联系电话
        user.put("lxdz", thisUser.getLxdz());//联系地址

        return new Response().success(user);
    }

    /**
     * 更新当前登录用户密码
     * 杨文星
     * 2017-10-21
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public Response updatePassword(String oldpwd, String newpwd, HttpSession session) {
        if (oldpwd == null || newpwd == null) {
            return new Response().failure("原密码或新密码不能为空");
        }
        User thisUser = null;
        try {
            thisUser = commonService.getUserInfoFromSession(session);
            return userService.updatePassword(thisUser.getLoginname(), oldpwd, newpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response().failure("密码修改失败");
    }

    //修改用户密码
    @RequestMapping(value = "/updatepwd",method = RequestMethod.POST)
    public Boolean updatepwd(@RequestBody Map map){
        try{
            return userService.updatePwd(map);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //获取用户信息
    @RequestMapping(value="/information",method = RequestMethod.POST)
    public UserExtend getUserInformation(HttpSession session){
        try{
            User us = (User) session.getAttribute("userInfo");
            UserExtend user = new UserExtend();
            user.setUser(us);
            Map map = userService.getUserInfor(user.getUser().getLoginname(),user.getUser().getSsbmbh());
            user.getUser().setSfzhm((String)map.get("SFZHM"));

            //状态
            switch (user.getUser().getZt()){
                case 0:user.setZtname("停用");break;
                case 1:user.setZtname("正常");break;
                case 2:user.setZtname("锁定");break;
                default:break;
            }

            //用户有效期状态
            switch (user.getUser().getYxqzt()){
                case "0":user.getUser().setYxqzt("有效");break;
                case "1":user.getUser().setYxqzt("无效");break;
                default:break;
            }

            //开启IP限制
            switch (user.getUser().getKqipxz()){
                case 0:user.setKqipxzstr("否");break;
                case 1:user.setKqipxzstr("是");break;
                default:break;
            }

            //是否使用PDA
            switch (user.getUser().getSfsypda()){
                case 0:user.setSfsypdaStr("否");break;
                case 1:user.setSfsypdaStr("是");break;
                default:break;
            }

            //是否民警
            switch (user.getUser().getSfmj()){
                case "1":user.getUser().setSfmj("是");break;
                case "2":user.getUser().setSfmj("否");break;
                default:break;
            }

            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
