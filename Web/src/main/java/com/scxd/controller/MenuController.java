package com.scxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.common.MenuBean;
import com.scxd.beans.common.MenuRoleBean;
import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysMenu;
import com.scxd.beans.pojo.SysMenuDetail;
import com.scxd.beans.pojo.SysUser;
import com.scxd.common.Response;
import com.scxd.service.CommonService;
import com.scxd.service.MenuService;
import com.scxd.service.common.impl.LoggerService;
import com.sun.javafx.logging.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 文件描述：获取菜单树
 * 作者：齐遥遥
 * 时间：2017/9/9
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：/VehicleManager/menus/saveMenus
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/menus")
public class MenuController {
    int OP_MODULE=13;
    @Resource
    private MenuService menuService;

    @Resource
    CommonService commonService;


    @Resource
    LoggerService loggerService;

    public MenuService getMenuService() {
        return menuService;
    }

    /**
     * 功能描述：获取所有菜单
     * 作者：齐遥遥
     * 时间：2017-09-09
     * @param type 菜单类型：all:获取所有菜单;
     * @return
     */
    @RequestMapping(value = "/menu/{type}", method = RequestMethod.GET, produces = "application/json")
    public Response getAllMenus(@PathVariable("type") String type, HttpServletRequest request, HttpSession session) throws Exception {

        List<SysMenu> menuBeanList = null;

        if("role".equals(type)) {
            User user = commonService.getUserInfoFromSession(session);
            menuBeanList = menuService.getMenuBeanListByLoginName(user.getLoginname());

        } else if("all".equals(type)) {
            menuBeanList = menuService.getMenusList(type,"");
        }
        Response response=new Response().success(menuBeanList);
        String opcontent="type="+type;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    /**
     * 功能描述：保存菜单
     * 作者：齐遥遥
     * 时间：2017-09-11
     * @return
     */
    @RequestMapping(value = "/saveMenus",method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public Response saveMenus(@RequestBody @Valid List<MenuBean> menuBeanList) {

        String result = "";

        try {
            boolean flag = menuService.saveMenus(menuBeanList);

            if(flag) {
                result = "save menus ok";
            } else {
                result = "save menus fail";
            }

        } catch (Exception e) {
            result = "save menus fail";
            e.printStackTrace();

            return new Response().failure(result);
        }

        return new Response().success(result);
    }


    @RequestMapping(value = "/menu/getMenuId", method = RequestMethod.POST,produces = "application/json")
    public Response getMenuId(@RequestBody @Valid MenuRoleBean menuRoleBean){
        try {

            return menuService.getMenuId(menuRoleBean.getId());
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }
    @RequestMapping(value = "/menu/saveChange", method = RequestMethod.POST,produces = "application/json")
    public Response saveChange(@RequestBody SysMenuDetail sysMenuDetail){
        try {
            return menuService.saveChange(sysMenuDetail);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }
    @RequestMapping(value = "/menu/selectAllMenu", method = RequestMethod.GET,produces = "application/json")
    public Response selectAllMenu(){
        try {
            return menuService.selectAllMenu();
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }
    @RequestMapping(value = "/menu/deleteMenuQx", method = RequestMethod.GET,produces = "application/json")
    public Response deleteMenuQx(@RequestParam String menuId){
        try {
            return menuService.deleteMenuQx(menuId);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }
    @RequestMapping(value = "/menu/selectqx", method = RequestMethod.GET,produces = "application/json")
    public Response selectqx(){
        try {
            return menuService.selectqx();
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }
    @RequestMapping(value = "/menu/selectMenuDetail", method = RequestMethod.GET,produces = "application/json")
    public Response selectMenuDetail(int  menuId){
        try {
            return menuService.selectMenuDetail(menuId);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }

    @RequestMapping(value = "/menu/menuPression", method = RequestMethod.GET,produces = "application/json")
    public Response selectMenuPression(){
        try {
            return menuService.selectMenuPression();
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage() != null ? e.getMessage() : "数据访问异常");
        }
    }

}
