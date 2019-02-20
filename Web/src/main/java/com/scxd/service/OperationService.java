package com.scxd.service;

import com.scxd.common.Response;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:34 2018/9/3
 * @Modified By:
 */
public interface OperationService {

//    public Response queryOperationType() ;

    Response queryOperationList(String ksrq, String jsrq, String bmbh, String czlx, String czr, int pageNo, int pageSize, int logType);

    Response queryOperationDetail(String id);

    Response queryLoginStatisticsList(String kssj, String jssj);

    Response queryOpStatisticsList(String kssj, String jssj);

    Response querySalfStatisticsList(String kssj, String jssj);

    Response queryAuditStatisticsList(String kssj, String jssj);

    Response queryScyLogDetail(String id);
}
