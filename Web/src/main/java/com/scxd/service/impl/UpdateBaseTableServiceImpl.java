package com.scxd.service.impl;

import com.scxd.beans.pojo.BizPoliceInfo;
import com.scxd.beans.pojo.BizQstationBean;
import com.scxd.beans.pojo.BizRoadBlocks;
import com.scxd.beans.webBeans.WebRoot;
import com.scxd.common.StringUtil;
import com.scxd.common.XmlUtil;
import com.scxd.dao.mapper.*;
import com.scxd.service.UpdateBaseTableService;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webjczhpt.RmJaxRpcOutAccess;
import com.scxd.webjczhpt.RmJaxRpcOutAccessService;
import com.scxd.webjczhpt.RmJaxRpcOutAccessServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateBaseTableServiceImpl implements UpdateBaseTableService {
    @Autowired
    LibSysParam libSysParam;
    @Autowired
    BizPoliceInfoMapper police;
    @Autowired
    BizQstation qstation;
    @Autowired
    BizRoadBlocksMapper roadblock;
    @Resource
    SysDepartmentMapper sysDepartmentMapper;
    @Resource
    LoggerService loggerService;
    @Resource
    SysDepartmentParamMapper sysDepartmentParamMapper;

    @Override
    public void updateBaseTable() throws ServiceException, RemoteException, UnsupportedEncodingException {
        RmJaxRpcOutAccessService rmJaxRpcOutAccessService = new RmJaxRpcOutAccessServiceLocator();
        RmJaxRpcOutAccess serviceSoap = rmJaxRpcOutAccessService.getRmOutAccess();
//        //获取接口序列号
//        String jkxlh = libSysParam.getJCZHPTJKXLH();
        updateStationTable(serviceSoap);


    }

    private void updateStationTable(RmJaxRpcOutAccess serviceSoap) throws RemoteException, UnsupportedEncodingException {

        List<String> bmbhs = sysDepartmentParamMapper.getZDBMBHs();
        if (bmbhs != null && bmbhs.size() > 0) {

            for (String bmbh : bmbhs
                    ) {
                String jkxlh = sysDepartmentParamMapper.getCSZByBmbhAndCsdm(bmbh, "JCZHJKXLH");
                bmbh = bmbh.substring(0, bmbh.length() > 4 ? 4 : bmbh.length());
                //xml查询交警执法站信息文档
                //bmbh = "6325";
                String xmlDoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "\n" +
                        "<root> \n" +
                        "  <qstation> \n" +
                        "    <glbmt>" + bmbh + "</glbmt> \n" +
                        "  </qstation> \n" +
                        "</root>";
                //返回结果
                String xmlResult = serviceSoap.queryObjectOut("64", jkxlh, "64Q01", xmlDoc);
                xmlResult = URLDecoder.decode(xmlResult, "utf-8");
                loggerService.writeWebLogger(xmlDoc, xmlResult, "64Q01");
                WebRoot root = null;
                List<BizQstationBean> qstationBeanList = null;
                if (StringUtil.isEmpty(xmlResult)) {
                    continue;
                }
                root = XmlUtil.toObj(xmlResult, WebRoot.class);
                if (root != null && "1".equals(root.getHead().getCode())) {
                    qstationBeanList = root.getBody().getQstationBeanList();
                } else {
                    continue;
                }
                if (qstationBeanList != null && qstationBeanList.size() > 0) {
                    //先删除数据库中的交警执法站信息
                    qstation.deleteQstationByLy(bmbh);
                    int num = qstation.insertQstations(qstationBeanList);
                    if (num > 0) {

                            updatePoliceTable(serviceSoap, qstationBeanList, jkxlh);
                            updateRoadBlockTable(serviceSoap, qstationBeanList, jkxlh);

                    }

                }

            }
        }

    }

    private void updatePoliceTable(RmJaxRpcOutAccess serviceSoap, List<BizQstationBean> list, String jkxlh) throws UnsupportedEncodingException, RemoteException {
        for (BizQstationBean  bizQstationBean : list
                ) {
            String str=bizQstationBean.getFwzbh();
            String xmlDoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "\n" +
                    "<root> \n" +
                    "  <qstationperson> \n" +
                    "    <fwzbh>" + str + "</fwzbh> \n" +
                    "  </qstationperson> \n" +
                    "</root>\n";
            //返回结果
            String xmlResult = serviceSoap.queryObjectOut("64", jkxlh, "64Q03", xmlDoc);
            xmlResult = URLDecoder.decode(xmlResult, "utf-8");
            loggerService.writeWebLogger(xmlDoc, xmlResult, "64Q03");
            WebRoot root = null;
            List<BizPoliceInfo> bizPoliceInfoList = null;
            if (StringUtil.isEmpty(xmlResult)) {
                continue;
            }
            root = XmlUtil.toObj(xmlResult, WebRoot.class);
            if (root != null && "1".equals(root.getHead().getCode())) {
                bizPoliceInfoList = root.getBody().getBizPoliceInfoList();
            } else {
                continue;
            }
            if (bizPoliceInfoList != null && bizPoliceInfoList.size() > 0) {
                for (BizPoliceInfo bean : bizPoliceInfoList
                        ) {
                    bean.setId(UUID.randomUUID().toString());
                    bean.setLy(1);
                    bean.setFwzbh(str);
                }
                police.deletePoliceInfoByLy(str);
                police.insertPoliceInfo(bizPoliceInfoList);
            }
        }
    }

    private void updateRoadBlockTable(RmJaxRpcOutAccess serviceSoap,  List<BizQstationBean> list, String jkxlh) throws RemoteException, UnsupportedEncodingException {
        for (BizQstationBean  bizQstationBean : list
                ) {
            String str=bizQstationBean.getFwzbh();
            String xmlDoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "\n" +
                    "<root> \n" +
                    "  <qstationrelation> \n" +
                    "    <fwzbh>" + str + "</fwzbh> \n" +
                    "  </qstationrelation> \n" +
                    "</root>\n";
            //返回结果
            String xmlResult = serviceSoap.queryObjectOut("64", jkxlh, "64Q02", xmlDoc);
            xmlResult = URLDecoder.decode(xmlResult, "utf-8");
            loggerService.writeWebLogger(xmlDoc, xmlResult, "64Q02");
            WebRoot root = null;
            List<BizRoadBlocks> bizRoadBlocksList = null;
            if (StringUtil.isEmpty(xmlResult)) {
                continue;
            }
            root = XmlUtil.toObj(xmlResult, WebRoot.class);
            if (root != null && "1".equals(root.getHead().getCode())) {
                bizRoadBlocksList = root.getBody().getBizRoadBlocksList();
            } else {
                continue;
            }
            if (bizRoadBlocksList != null && bizRoadBlocksList.size() > 0) {
                for (BizRoadBlocks bean : bizRoadBlocksList
                        ) {
                    bean.setId(UUID.randomUUID().toString());
                    bean.setLy(1);
                    bean.setZfzbh(str);
                }
                roadblock.deleteBizRoadBlockByLy(str);
                roadblock.insertRoadBlocks(bizRoadBlocksList);
            }
        }
    }
}
