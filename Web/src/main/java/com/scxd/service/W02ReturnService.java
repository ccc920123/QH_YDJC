package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import java.io.IOException;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:18 2018/6/14
 * @Modified By:
 */
public interface W02ReturnService {
    Response writeBKMessage(BaseRequest baseRequest) throws IOException;
}
