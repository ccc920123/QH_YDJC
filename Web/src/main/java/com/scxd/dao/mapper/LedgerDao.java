package com.scxd.dao.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.Map;

public interface LedgerDao {

    public int insertLedger(@Param("map") Map<?,?> map);
}
