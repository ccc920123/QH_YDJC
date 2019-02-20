package com.scxd.dao.mapper;

import com.scxd.beans.pojo.BizLawsInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizLawsInfoBeanMapper {
    int insert(BizLawsInfoBean record);

    int insertSelective(BizLawsInfoBean record);

    int getLawListTotal(@Param("name") String name);

    List<BizLawsInfoBean> getLawList(@Param("name") String name,@Param("start") int start, @Param("end")int end);

    BizLawsInfoBean getLawDetail(@Param("id")String id);
}