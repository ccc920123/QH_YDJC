package com.scxd.service;

import com.scxd.beans.biz.ForceExtend;
import com.scxd.beans.biz.SurveExtend;
import com.scxd.beans.biz.ViolationExtend;
import com.scxd.beans.common.WritBean;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文书管理业务层接口
 */
public interface WritService {

    //条件查询文书详情
    Map getWritByWslbWsbhScsj(Map map)throws Exception;
    //删除文书
    boolean removeWrit(Map map)throws Exception;

    //查询电子监控详情
    SurveExtend getSurveInfo(String wsbh, HttpServletRequest request)throws Exception;

    //查询强制措施详情
    ForceExtend getForceInfo(String wsbh)throws Exception;

    //查询简易处罚详情
    ViolationExtend getViolationInfo(String wsbh)throws Exception;
}
