package com.scxd.dao.mapper;

import com.scxd.beans.biz.BizPhotoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *功能描述：图片操作接口
 */
public interface PhotoDao {

   int  insertPhotoByObj(BizPhotoInfo photoInfo);

   List<BizPhotoInfo> getFeedBackPhotosByYjxh(String yjxh);

    BizPhotoInfo selectByPrimaryKey(String id);

    List<BizPhotoInfo> selectZPByglidAndZpzl(@Param("glid") String glid,@Param("zpzl")String zpzl);

    void deleteByUrl(String url);

    void deleteByGlid(@Param("glid") String glid,@Param("zpzl")String zpzl);
}
