package com.scxd.service.management.ifaces;

import com.scxd.beans.pojo.SysPdaInfo;
import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 10:45 2018/6/6
 * @Modified By:
 */
public interface PDAService {

    Response getPdaMessage(String pdamc, String pdawym, String bmbh, String bhxj, String pageindex, String pagesize);

    Response addPdaInfo(SysPdaInfo pdaInfo, String name);

    Response deletePda(String pdaid);

    Response updatePda(SysPdaInfo pdaInfo, String name);

    Response addPdaVersion(SysPdaVersion pdaInfo, String name);

    Response getPdaVersion(String bmbh, String version, String desc, int page, int pageSize);

    Response deletePdaVersion(String pdaversion);
}
