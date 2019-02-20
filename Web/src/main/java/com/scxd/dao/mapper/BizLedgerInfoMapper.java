package com.scxd.dao.mapper;

import com.scxd.beans.management.LedgerListBean;
import com.scxd.beans.pdaBeans.response.Q30ReturnBean;
import com.scxd.beans.pojo.BizLedgerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BizLedgerInfoMapper {


    int insertSelective(BizLedgerInfo record);


    BizLedgerInfo getLedgerDocumentDetail(String tzlsh);

    List<Q30ReturnBean> getLedgerDocument(Map<String, Object> map);

    int getLedgerDocTotal(Map<String, Object> map);

    List<String> getLedgerPhoto(String tzlsh);

    int queryLeadgerListTotal(@Param("bmbh") String bmbh,@Param("ksrq") String ksrq,
                              @Param("jsrq")String jsrq, @Param("zfzbh")String zfzbh,@Param("hphm") String hphm,
                              @Param("jszh")String jszh);

    List<LedgerListBean> queryLeadgerList(@Param("bmbh") String bmbh,@Param("ksrq") String ksrq,
                                          @Param("jsrq")String jsrq, @Param("zfzbh")String zfzbh,@Param("hphm") String hphm,
                                          @Param("jszh")String jszh, @Param("pageNo")String pageNo, @Param("pageSize")String pageSize);

    Map<String,Object> getLedgerPagesDetail(String id);
}