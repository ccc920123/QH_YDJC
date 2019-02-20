package com.scxd.service;

import com.scxd.beans.pojo.SysUserSafeauditsetBean;
import com.scxd.common.Response;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 13:59 2018/10/18
 * @Modified By:
 */
public interface SalfAuditService {
    Response salfAuditService(String salfType, int pageNo, int pageSize);

    Response getSalfAuditDetail(String id);

    Response saveSalfAudit(SysUserSafeauditsetBean sysUserSafeauditsetBean);

    Response salfAuditLogList(String safetype, int pageNo, int pageSize);
}
