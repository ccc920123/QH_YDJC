package com.scxd.service;

import com.scxd.common.Response;

public interface PushService {

    public Response getWarningPush(String querydoc);

    Response pushBack(String writedoc);
}
