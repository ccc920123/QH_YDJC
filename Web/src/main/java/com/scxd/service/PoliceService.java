package com.scxd.service;

import com.scxd.beans.management.FwzbhAndFwzmc;
import com.scxd.common.Response;

import java.util.List;
import java.util.Map;

public interface PoliceService {

   Response getPoliceInfo(Map map)throws Exception;

    List<FwzbhAndFwzmc> getFwz(String glbm)throws Exception;
}
