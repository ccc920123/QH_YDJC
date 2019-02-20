package com.scxd.service.impl;

import com.scxd.beans.biz.BizPhotoInfo;
import com.scxd.beans.biz.Q09Return;
import com.scxd.beans.pojo.BizAlarmInfo;
import com.scxd.common.ImgTools;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.BizAlarmInfoMapper;
import com.scxd.dao.mapper.PhotoDao;
import com.scxd.dao.mapper.SysDepartmentParamMapper;
import com.scxd.service.VehiclesService;
import com.scxd.service.WebJCBKService;
import com.scxd.service.common.LibSysParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:23 2018/6/15
 * @Modified By:
 */
@Service
public class WebJCBKServiceImpl implements WebJCBKService {
    @Resource
    private BizAlarmInfoMapper bizAlarmInfoMapper;
    @Resource
    private PhotoDao photoDao;
    @Resource
    private LibSysParam libSysParam;

    @Resource
    private VehiclesService vehiclesService;

    @Resource
    private SysDepartmentParamMapper sysDepartmentParamMapper;

    /**
     * 写入预警信息
     *
     * @param bizAlarmInfo
     * @param request
     * @return
     */
    @Override
    public Response writeYJMessage(BizAlarmInfo bizAlarmInfo, HttpServletRequest request) {

        Response response = null;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour <= 7) {
            return new Response().success();
        }
        //插入预警信息表
        if (bizAlarmInfo != null) {
            //查询车辆信息，根据号牌号码号牌种类将车辆类型和使用性质查询出来
            String bmbh = bizAlarmInfo.getQsbm();
            if (StringUtil.isNotEmpty(bmbh)) {
                String sfjsyj = sysDepartmentParamMapper.getCSZByBmbhAndCsdm(bmbh, "SFJSYJ");
                if (!"1".equals(sfjsyj)) {
                    return new Response().success();
                }
                Q09Return q09Return = null;
                try {
                    q09Return = vehiclesService.getVehMessage(bizAlarmInfo.getHphm(), bizAlarmInfo.getHpzl(), request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (q09Return != null) {
                    bizAlarmInfo.setCllx(q09Return.getCllx());
                    bizAlarmInfo.setSyxz(q09Return.getSyxz());
                }
            }
        }
        int i = bizAlarmInfoMapper.insert(bizAlarmInfo);
        String gcxh = bizAlarmInfo.getGcxh();
        String yjxh = bizAlarmInfo.getYjxh();
        try {
            //通过过车序号和预警序号查出预警图片
            downloadNet(yjxh, gcxh, request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (i > 0) {
            response = new Response().success();
        } else {
            response = new Response().failure("写入失败");
        }

        return response;
    }

    /**
     * 下载预警图片
     *
     * @param yjxh
     * @param gcxh
     * @param request
     * @throws MalformedURLException
     */
    public void downloadNet(String yjxh, String gcxh, HttpServletRequest request) throws MalformedURLException {


        String path = libSysParam.getPHOTO_PATH();
        //获取拼装url链接
        String contextPath = "/Web";
        String basePath = request.getScheme() + "://" +
                path + contextPath + "/getPhotos?" + "id=";

        String sUrl = libSysParam.getJCZHPTZPUrl() + gcxh;
        // sUrl="http://10.192.12.142/rmweb/readPassPic.tfc?method=getPassPic&fhk=2&gcxh=630000100461703044";
        URL url = new URL(sUrl);

        try {
//            URLConnection conn = url.openConnection();
//            InputStream inStream = conn.getInputStream();
//            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
//            byte[] buff = new byte[100];
//            int rc = 0;
//            while ((rc = inStream.read(buff, 0, 100)) > 0) {
//                swapStream.write(buff, 0, rc);
//            }
            byte[] buffer = null;
            long date1 = new java.util.Date().getTime();
            buffer = ImgTools.reduceImg(url, 1024, 840, 0.5f);
            long date2 = new java.util.Date().getTime();
            System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK :;" + (date2 - date1));
            BizPhotoInfo photoInfo = new BizPhotoInfo();
            String uuid = UUID.randomUUID().toString();
            photoInfo.setId(uuid);
            photoInfo.setGlid(yjxh);
            photoInfo.setPsry("jcbkpt");
            photoInfo.setZp(buffer);
            photoInfo.setZpzl("7001");
            photoInfo.setPssj(new Date(System.currentTimeMillis()));
            photoInfo.setSx1(basePath + uuid);
            //插入图片表
            photoDao.insertPhotoByObj(photoInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
