package com.scxd.dao.mapper;

import com.scxd.beans.biz.Q11Return;
import com.scxd.beans.common.KeyValueBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeDao {
    public List<Q11Return> selectCode(String bmbh);

    List<KeyValueBean> getKeyValueByDmlb(@Param("dmlb") String dmlb);
}
