package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.common.Role;
import com.scxd.beans.common.User;
import com.scxd.beans.management.MenuRoleBean;
import com.scxd.beans.pojo.SysRole;
import com.scxd.common.Response;
import com.scxd.common.StringEx;
import com.scxd.service.CommonService;
import com.scxd.service.SysRoleService;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.service.management.ifaces.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作者：chenli
 * 日期：2017/10/12
 */
@RestController
public class RoleColtroller {
    @Resource
    private RoleService roleService;
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private CommonService commonService;
    @Resource
    LoggerService loggerService;
    int OP_MODULE=11;
    /**
     * 功能描述：获取角色信息
     *
     * @param NAME     角色名称
     * @param TYPE     角色类型
     * @param LEVELNUM 角色层级
     * @return
     */
    @RequestMapping(value = "/management/Role/getRole", method = RequestMethod.GET)
    public Response getRole(String pageNo, String pageSize, String NAME, String TYPE, String LEVELNUM,HttpServletRequest request) {
        Response response= roleService.getRole(pageNo, pageSize, NAME, TYPE, LEVELNUM);
        String opcontent="NAME="+NAME+"&TYPE="+TYPE+"&LEVELNUM="+LEVELNUM+"&pageNo="+pageNo+"&pageSize="+pageSize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return  response;
    }

    /**
     * 功能描述：新增角色信息
     * 作者：chenli
     * 时间：2017-10-12
     *
     * @param role 角色实体
     * @return
     */
    @RequestMapping(value = "/management/Role/addRole", method = RequestMethod.POST, produces = "application/json")
    public Response addRole(@RequestBody @Valid SysRole role, HttpSession session,HttpServletRequest request) {
        User user = null;
        try {
            user = commonService.getUserInfoFromSession(session);
        } catch (Exception e) {
            e.printStackTrace();

        }
        String ssbmbh = user.getSsbmbh();
        String loginame = user.getLoginname();
        role.setCzryzh(loginame);
        role.setCzsj(new Date());
        role.setCjbmbh(ssbmbh);
        Response response= sysRoleService.addRole(role);
        String opcontent=((JSONObject) JSONObject.toJSON(role)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,3,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;

    }
    @RequestMapping(value = "/management/Role/modifyRole", method = RequestMethod.POST, produces = "application/json")
    public Response modifyRole(@RequestBody @Valid SysRole role, HttpSession session,HttpServletRequest request) {
        User user = null;
        try {
            user = commonService.getUserInfoFromSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ssbmbh = user.getSsbmbh();
        String loginame = user.getLoginname();
        role.setCzryzh(loginame);
        role.setCzsj(new Date());
        role.setCjbmbh(ssbmbh);
        Response response= sysRoleService.modifyRole(role);
        String opcontent=((JSONObject) JSONObject.toJSON(role)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,5,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;

    }
//
//    /**
//     * @param ROLE_ID 角色ID
//     * @return
//     * @description 获取指定Id的角色信息
//     */
//    @RequestMapping(value = "/management/Role/getRoleById", method = RequestMethod.GET, produces = "application/json")
//    public Response getRoleById(@PathVariable("ROLE_ID") int ROLE_ID) throws Exception {
//
//        Role role = (Role) roleService.getRoleById(ROLE_ID);
//        return new Response().success(role);
//    }

//    /**
//     * @param role 角色实体
//     * @return
//     * @description 修改指定Id的角色信息
//     */
//    @RequestMapping(value = "/management/Role/updateRoleById", method = RequestMethod.GET, produces = "application/json")
//    public Response updateRoleById(@RequestBody @Valid Role role) {
//        String result = "";
//        try {
//            boolean flag = roleService.updateRoleById(role);
//
//            if (flag) {
//                result = "update Role ok";
//            } else {
//                result = "update Role fail";
//            }
//        } catch (Exception e) {
//            result = "update Role fail";
//            e.printStackTrace();
//        }
//        return new Response().success(result);
//    }

    /**
     * @return
     * @description 删除指定Id的角色信息
     */
    @RequestMapping(value = "/management/Role/deleteMenuRole", method = RequestMethod.GET)
    public Response deleteMenuRole(int roleId,HttpServletRequest request) {
        Response response= sysRoleService.deleteMenuRole(roleId);
        String opcontent="roleId="+roleId;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,4,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
            return response;

    }


    /**
     * 查询角色信息列表
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/management/Role/menuRoleList", method = RequestMethod.GET)
    public Response menuRoleList(int roleId, int type) {

            return sysRoleService.menuRoleList(roleId, type);

    }

    /**
     * 插入角色信息到Sys_Menu_Role
     *
     * @return
     */
    @RequestMapping(value = "/management/Role/addMenuRole", method = RequestMethod.POST, produces = "application/json")
    public Response addMenuRole(@RequestBody @Valid MenuRoleBean menuRoleBean, HttpServletRequest request, HttpSession session) {

        try {
            User user = commonService.getUserInfoFromSession(session);
            String ssbmbh = user.getSsbmbh();
            String loginame = user.getLoginname();
            menuRoleBean.getRole().setCjbmbh(ssbmbh);
            menuRoleBean.getRole().setCzryzh(loginame);
            return roleService.addMenuRole(menuRoleBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().success(e.getMessage() != null ? e.getMessage() : "0");
        }
    }

    /**
     * 修改角色数据
     */
    @RequestMapping(value = "/management/Role/modifyRole", method = RequestMethod.GET)
    public Response modifyRole(int roleId) {
        try {
            return roleService.modifyRole(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "0");
        }
    }

    /**
     * 获取角色用户列表
     */
    @RequestMapping(value = "/management/Role/getRoleList", method = RequestMethod.GET)
    public Response getRoleList(int pageNo, int pageSize) {
        try {
            return roleService.getRoleList(pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "0");
        }
    }

    /**
     * 修改角色菜单数据
     *
     * @param
     * @param request
     * @return
     */

    @RequestMapping(value = "/management/Role/modifyMenuRole", method = RequestMethod.POST, produces = "application/json")
    public Response modifyMenuRole(@RequestBody Map map, HttpServletRequest request, HttpSession session) {
        try {
            User user = commonService.getUserInfoFromSession(session);
            String loginame = user.getLoginname();
            int roleId =  Integer.valueOf(map.get("roleId").toString());
            List<Integer> menuIds = (List<Integer>) map.get("menuIds");
            int[] ids = new int[menuIds.size()];
            for (int i = 0; i < menuIds.size(); i++) {
                ids[i] = menuIds.get(i);
            }
            return sysRoleService.modifyMenuRole(roleId, ids,loginame);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "0");
        }
    }

    @RequestMapping(value = "/management/Role/getUser", method = RequestMethod.GET)
    public Response getUser(int roleId) {
        try {
            return roleService.getUser(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }
}
