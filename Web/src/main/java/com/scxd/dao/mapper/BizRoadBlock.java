package com.scxd.dao.mapper;

import com.scxd.beans.common.RoadblockExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  卡口备案
 */
public interface BizRoadBlock {
    //删除来源ly = 1的信息
    int deleteBizRoadBlockByLy();
    //批量插入
    int insertRoadBlocks(@Param("listmap") List<Map<String,Object>> listmap);

    //查询信息总数
    int selectTotal(Map map);

    //分页查询信息
    List<RoadblockExtend> selectRoadblock(Map map);
}
