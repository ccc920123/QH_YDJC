package com.scxd.service.impl;

import com.scxd.beans.biz.BizPhotoInfo;
import com.scxd.beans.common.EntityToMap;
import com.scxd.dao.mapper.PhotoDao;
import com.scxd.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;


@Service
public class PhotoServiceImpl implements PhotoService {

    @Resource
    private PhotoDao photoDao;

    @Override
    public int inputPhoto(BizPhotoInfo photo) {
        try{
            return photoDao.insertPhotoByObj(photo);
        }catch (Exception e){
            return 0;
        }
    }
}
