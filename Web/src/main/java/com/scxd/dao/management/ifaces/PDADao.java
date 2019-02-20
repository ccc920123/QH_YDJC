package com.scxd.dao.management.ifaces;

import com.scxd.beans.management.PdaBean;

import java.util.List;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:45 2018/6/6
 * @Modified By:
 */
public interface PDADao {

 List<PdaBean> getPdaMessage(String pdamc, String pdawym, String bmbh, String bhxj, String pageindex, String pagesize) throws  Exception;
}
