package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 16:26 2018/9/3
 * @Modified By:
 */
public interface LawService {
    Response getLawList(String querydoc)throws  Exception;

    Response getLawDetail(BaseRequest baseRequest) throws  Exception;
}
