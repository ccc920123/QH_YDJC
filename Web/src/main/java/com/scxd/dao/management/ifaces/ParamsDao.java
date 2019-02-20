package com.scxd.dao.management.ifaces;

import com.scxd.beans.management.ParamsBean;
import com.scxd.beans.management.PdaBean;

import java.util.List;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:45 2018/6/6
 * @Modified By:
 */
public interface ParamsDao {

 List<ParamsBean> getPdaMessage(String bmbh, String csmc, String page, String pagesize) throws  Exception;
}
