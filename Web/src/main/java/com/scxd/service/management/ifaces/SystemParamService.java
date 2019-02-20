package com.scxd.service.management.ifaces;

import com.scxd.beans.management.ParamsBean;
import com.scxd.beans.pojo.SysPdaInfo;
import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;

import java.util.Map;

/**
 * @Auther:张翔
 * @Description:
 * @Date:Created in 10:45 2018/6/20
 * @Modified By:
 */
public interface SystemParamService {

    Response queryParams(String bmbh, String csmc, String page, String pagesize);

    Response getDepartMentParamsMessage(String bmbh, String csmc, String page, String pagesize);

    Response updateParams(String bmbh, String id, String csz);

    Response addParams(ParamsBean paramsBean);

    Response deleteParams(String id);

    Response updateDepartmentParams(Map map);
}
