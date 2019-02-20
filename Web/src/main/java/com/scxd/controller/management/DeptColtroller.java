package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.common.User;
import com.scxd.beans.management.Dept;
import com.scxd.common.Response;
import com.scxd.common.StringEx;
import com.scxd.common.StringUtil;
import com.scxd.service.CommonService;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.service.management.ifaces.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.Map;

/**
 * 标题：部门管理
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
@RestController
public class DeptColtroller {
    int OP_MODULE=10;
    @Resource
    private DeptService deptService;

    @Autowired
    LoggerService loggerService;

    @Resource
    private CommonService commonService;


    /**
     * 插入、更新
     *
     * @param dept
     * @throws Exception
     */
    @RequestMapping(value = "management/dept/update", method = RequestMethod.PUT)
    public Response merge(@RequestBody @Valid Dept dept, HttpServletRequest request, HttpSession session) {

        User cachedUser = null;
        try {
            cachedUser = commonService.getUserInfoFromSession(session);
            dept.setCzryzh(cachedUser.getLoginname());
        } catch (Exception e) {
            // e.printStackTrace();
            dept.setCzryzh("admin");
        }

        return deptService.merge(dept);
    }

    /**
     * 查询一组下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "management/dept/selectNextLev", method = RequestMethod.GET)
    public Response selectNextLev(String bmbh, String searchBh, String searchName, String pageNo, String pageSize,HttpServletRequest request) {
        Response response=deptService.selectNextLev(bmbh, searchBh, searchName, pageNo, pageSize);
        String opcontent="bmbh="+bmbh+"&searchBh="+searchBh+"&searchName="+searchName+"&pageNo="+pageNo+"&pageSize="+pageSize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    @RequestMapping(value = "management/dept/select", method = RequestMethod.GET)
    public Response selectOne(String bmbh, String searchBh, String searchName,HttpServletRequest request
                              ) {
        Response response=deptService.selectOne(bmbh, searchBh, searchName);
        String opcontent="bmbh="+bmbh+"&searchBh="+searchBh+"&searchName="+searchName;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    /**
     * 查询全部下级dept
     *
     * @param bmbh
     * @param zdid 部门id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "management/dept/selectAll", method = RequestMethod.GET)
    public Response selectAll(String bmbh, String zdid, HttpServletRequest request,HttpSession session) {

        if (StringUtil.isEmpty(bmbh)) {
            User cachedUser = null;
            try {
                cachedUser = commonService.getUserInfoFromSession(session);
                bmbh = cachedUser.getSsbmbh();
            } catch (Exception e) {
            }
        }
        Response response=deptService.selectAll(bmbh, zdid);
        String opcontent="bmbh="+bmbh+"&zdid="+zdid;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;

    }


    /**
     * 删除一个部门
     *
     * @param bmbh
     * @throws Exception
     */
    @RequestMapping(value = "management/dept/delete", method = RequestMethod.DELETE)
    public Response delete(@QueryParam("bmbh") String bmbh,HttpServletRequest request) {
        Response response=deptService.delete(bmbh);
        String opcontent="bmbh="+bmbh;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,4,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    /**
     * type代码、层级代码
     *
     * @return
     */
    @RequestMapping(value = "management/dept/typeList", method = RequestMethod.GET)
    public Response typeList() {
        return deptService.typeList();
    }

    /**
     * 查询全部下级支队dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "management/dept/getZdList", method = RequestMethod.GET)
    public Response getZdList(String bmbh,HttpServletRequest request) {
        Response response=deptService.getZdList(bmbh);
        String opcontent="bmbh="+bmbh;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }



}
