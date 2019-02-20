package com.scxd.beans.webBeans;

import com.scxd.beans.biz.*;
import com.scxd.beans.pojo.BizPoliceInfo;
import com.scxd.beans.pojo.BizQstationBean;
import com.scxd.beans.pojo.BizRoadBlocks;
import com.scxd.beans.pojo.test.BizVehInfoBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

public class WebBody {
    @XmlElement(name = "veh")
    private BizVehInfoBean veh;
    @XmlElement(name = "drv")
    private BizDrvInfo drvInfo;
    @XmlElement(name = "DrvPerson")
    private BizDrvInfo bizDrvInfo;
    @XmlElement(name = "DrvZp")
    private DriverPhotoBean drvZp;

    public BizDrvInfo getBizDrvInfo() {
        return bizDrvInfo;
    }

    public BizVehInfoBean getVeh() {
        return veh;
    }

    public BizDrvInfo getDrvInfo() {
        return drvInfo;
    }

    public DriverPhotoBean getDrvZp() {
        return drvZp;
    }

    @XmlElement(name = "viosurveil")
    private List<BizVioInfo> vioInfo;
    @XmlElement(name = "vio")
    private List<BizVioInfo> bizVioInfoList;
    @XmlElement(name = "qstation")
    private List<BizQstationBean> qstationBeanList;
    @XmlElement(name = "qstationperson")
    private List<BizPoliceInfo> bizPoliceInfoList;
    @XmlElement(name = "qstationrelation")
    private List<BizRoadBlocks> bizRoadBlocksList;
    @XmlElement(name = "vioforce")
    private List<WebForceResponseBean> webForceResponseBeans;

    public List<BizVioInfo> getVioInfo() {
        return vioInfo;
    }

    @XmlElement(name = "violation")
    private List<BizVioInfo> vioDrvInfo;

    public List<BizVioInfo> getVioDrvInfo() {
        return vioDrvInfo;
    }

    @XmlElement(name = "viojdczp")
    private VioZps vioZps;

    public VioZps getVioZps() {
        return vioZps;
    }

    public List<BizVioInfo> getBizVioInfoList() {
        return bizVioInfoList;
    }

    public List<BizQstationBean> getQstationBeanList() {
        return qstationBeanList;
    }

    public List<BizPoliceInfo> getBizPoliceInfoList() {
        return bizPoliceInfoList;
    }

    public List<BizRoadBlocks> getBizRoadBlocksList() {
        return bizRoadBlocksList;
    }

    public List<WebForceResponseBean> getWebForceResponseBeans() {
        return webForceResponseBeans;
    }
}
