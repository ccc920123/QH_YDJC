package com.scxd.dao.mapper;

import com.scxd.beans.biz.Q19Return;
import com.scxd.beans.biz.Q22Return;
import com.scxd.beans.pojo.BizVioSurveBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 违法监控mapper接口
 */
public interface BizVioSurveMapper {
    //获取违法监控列表
    List<Q19Return> selectVioSurveList(Date ksrq,Date jsrq,int start,int end,String zqmj);
    //通过jdsbh获取违法监控详细信息
    Q22Return selectVioSurveByJdsbh(String jdsbh);
    //电子监控信息录入
    int insertVioSurve(@Param("map")Map<String,Object> map);
    //获取电子监控详情图片
    List<String> selectZPS(String tzsh);

    void deleteVioSure(String id);

    int updatePrint(String xh);

    String getMaxTzsh();
    String  getSurveDocumentBH();
    int insertSelective(BizVioSurveBean record);

    int getSurveTotal(Date ksrq, Date jsrq, int start, int end, String zqmj);
}
