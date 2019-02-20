package com.scxd.dao.mapper;

import com.scxd.beans.common.KeyValueBean;
import com.scxd.beans.pojo.BizQstationBean;
import com.scxd.beans.pojo.BizQstationExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 交警执法站信息操作
 */
public interface BizQstation {
    //删除所有来源为自动增加的信息
    public int deleteQstationByLy(@Param("bmbh")String bmbh);
//    //批量插入交警执法站信息
//    public int insertQstations(@Param("listmap") List<Map<String,Object>> listmap);

    int insertQstations(@Param("qstationBeanList")List<BizQstationBean> qstationBeanList);

    List<String> getAllQsatianBhs();

    //查询执法站信息总数
    int selectTotal(Map map);
    //分页查询执法站信息
    List<BizQstationExtend> selectQstation(Map map);

    List<KeyValueBean> queryByBmbh(String bmbh);
}
