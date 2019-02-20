package com.scxd.dao.mapper;

import com.scxd.beans.biz.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 违法信息dao
 */
public interface BizVioInfoMapper {
   //查询机动车违法列表清单
    List<Q13Return> selectVioInfo(@Param("hpzl")String hpzl,@Param("hphm")String hphm,@Param("start")int start,@Param("end")int end);
    //查询最近一天有无该信息
    List<Q13Return> selectVioByDate(@Param("hpzl")String hpzl,@Param("hphm") String hphm, @Param("date")Date date,@Param("start")int start,@Param("end")int end);
    //查询最近一天有无存储该驾驶人违法信息
    List<Q16Return> selectVioDriByDate(@Param("sfzmhm") String sfzmhm,@Param("date")Date date,@Param("start")int start,@Param("end")int end);
    //删除时间超过一天的陈旧信息
    int deleteVioInfo(@Param("date") Date date,@Param("sfzmhm") String sfzmhm);
    //将综合平台的信息写入数据库
    int insertVioInfoList(@Param("listvio")List<BizVioInfo> listvio);
    //通过序号查询违法机动车详细信息
    Q14Return selectVioByXH(String xh);
    //通过ID查询驾驶人违法详情
    Q14Return selectVioById(String id);
    //将机动车违法照片插入本地库
    int insertVioZps(@Param("zps")List<BizPhotoInfo> zps);
    //通过关联id查询照片路劲
    List<String> selectZpUrl(String glid);
    //删除照片
    int deleteZP(@Param("date")Date date, @Param("hphm")String hphm,@Param("hpzl") String hpzl);
    //分页查询违法代码列表
    List<Q23Return> selectVioCodeList(@Param("map") Map<String,Object> map, int start, int end);
    //违法代码总数查询
    int selectXXTS(Map<String,Object> map);
    //违法代码详情
    Q24Return selectVioCodeInfo(String wfdm);
    //删除违法代码信息
    int deleteVioCode();
    //将综合平台违法代码信息写入本地库
     int insertVioCodeList(@Param("listvio")List<BizCodeWfdm> listvio);
//通过号牌号码号牌种类删除
    void deleteVioInfoByCar(@Param("date") Date date,@Param("hphm") String hphm,@Param("hpzl") String hpzl);

 void deleteVioInfoByJSZH(@Param("date") Date date,@Param("jszh")String jszh);
}
