package com.scxd.service.impl;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.request.PdaBKRequest;
import com.scxd.beans.pojo.BizRmConfig;
import com.scxd.beans.pojo.BizRmConfigDetail;
import com.scxd.common.JSONUtil;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.BizRmConfigDetailMapper;
import com.scxd.dao.mapper.BizRmConfigMapper;
import com.scxd.service.W02ReturnService;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:19 2018/6/14
 * @Modified By:
 */
@Service
public class W02ReturnServiceImpl implements W02ReturnService {

    @Resource

    BizRmConfigMapper bizRmConfigMapper;
    @Resource
    BizRmConfigDetailMapper bizRmConfigDetailMapper;

    @Resource
    LibSysParam libSysParam;

    @Resource

    LoggerService loggerService;


    //回滚
    @Transactional
    @Override
    public Response writeBKMessage(BaseRequest baseRequest) throws IOException {
        String writedoc = baseRequest.getWritedoc();
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            PdaBKRequest pdaBKRequest = JSONUtil.parseObject(writedoc, PdaBKRequest.class);
            if (null != pdaBKRequest) {
                //删除这个用户以前的布控设置
                if ("0".equals(pdaBKRequest.getJszt())) {
//停止接收
                    loggerService.writeOperaLogger(baseRequest.getUser(), 7, baseRequest.getUser(), baseRequest.getWym());
                    int i = bizRmConfigMapper.updateTSZTByUser(pdaBKRequest.getUser(), pdaBKRequest.getJszt());
                    if (i > 0) {
                        response = new Response().success();
                    } else {
                        response = new Response().failure("关闭推送失败");
                    }
                } else {
                    //x
                    loggerService.writeOperaLogger(baseRequest.getUser(), 6, baseRequest.getUser(), baseRequest.getWym());
                    String pID = bizRmConfigMapper.selectIDByUser(pdaBKRequest.getUser());
                    if (StringUtil.isNotEmpty(pID)) {
                        bizRmConfigMapper.deleteByPrimaryKey(pID);
                        bizRmConfigDetailMapper.deleteByRMKey(pID);
                    }

                    //生成uuid，写入主表，在遍历sbkk，yjlx，cjry ，分别写入从表
                    String uuid = UUID.randomUUID().toString();
                    Date date = new Date();
                    BizRmConfig rmConfig = new BizRmConfig();
                    rmConfig.setBmbh(pdaBKRequest.getBmbh());
                    rmConfig.setCjsj(date);
                    rmConfig.setId(uuid);
                    rmConfig.setTszt(Short.valueOf(pdaBKRequest.getJszt()));
                    rmConfig.setCjr(pdaBKRequest.getUser());
                    String sbkk = pdaBKRequest.getSbkk();
                    String yjlx = pdaBKRequest.getYjlx();
                    String cjry = pdaBKRequest.getCjry();
                    try {
                        bizRmConfigMapper.insert(rmConfig);
                        List<BizRmConfigDetail> sbkks = insertRmConfigDetailKK(sbkk, "0", uuid);
                        List<BizRmConfigDetail> yjlxs = insertRmConfigDetail(yjlx, "1", uuid);
                        List<BizRmConfigDetail> cjrys = insertRmConfigDetail(cjry, "2", uuid);
                        if (yjlxs != null) {
                            sbkks.addAll(yjlxs);
                        }
                        if (cjrys != null) {
                            sbkks.addAll(cjrys);
                        }
                        int num = bizRmConfigDetailMapper.insertSelectiveList(sbkks);
                        if (num > 0) {
                            response = new Response().success("提交成功");
                        } else {
                            response = new Response().success("写入配置详情失败");
                        }

                    } catch (Exception e) {
                        response = new Response().failure("写入数据异常");
                    } finally {
                    }
                }

            } else {
                response = new Response().failure("写入数据不匹配");
            }
        } else {
            response = new Response().failure("写入数据为空");
        }

        return response;
    }

    /**
     * 插入详情表
     *
     * @param
     * @param sbkk
     * @param type 类型
     */
    private List<BizRmConfigDetail> insertRmConfigDetail(String sbkk, String type, String uuid) {
        List<BizRmConfigDetail> bizRmConfigDetails = null;
        if (StringUtil.isNotEmpty(sbkk)) {
            String[] var = sbkk.split(",");
            if (var != null) {
                bizRmConfigDetails = new ArrayList<>();
                for (String v : var
                        ) {
                    BizRmConfigDetail bizRmConfigDetail = new BizRmConfigDetail();
                    bizRmConfigDetail.setId(UUID.randomUUID().toString());
                    bizRmConfigDetail.setRmid(uuid);
                    bizRmConfigDetail.setPzdm(v);
                    bizRmConfigDetail.setType(Short.valueOf(type));
                    bizRmConfigDetails.add(bizRmConfigDetail);
                    // bizRmConfigDetailMapper.insert(bizRmConfigDetail);
                }
            }
        }
        return bizRmConfigDetails;
    }

    /**
     * 设备卡口单独处理
     *
     * @param sbkk
     * @param type
     * @param uuid
     */
    private List<BizRmConfigDetail> insertRmConfigDetailKK(String sbkk, String type, String uuid) {
        List<BizRmConfigDetail> bizRmConfigDetails = new ArrayList<>();
        if (StringUtil.isNotEmpty(sbkk)) {
            String[] var = sbkk.split(",");
            if (var != null) {
                for (String v : var
                        ) {
                    String kk = v.substring(0, v.length() - 1);
                    String fxlx = v.substring(v.length() - 1);
                    BizRmConfigDetail bizRmConfigDetail = new BizRmConfigDetail();
                    bizRmConfigDetail.setId(UUID.randomUUID().toString());
                    bizRmConfigDetail.setRmid(uuid);
                    bizRmConfigDetail.setPzdm(kk);
                    bizRmConfigDetail.setPzzdm(fxlx);
                    bizRmConfigDetail.setType(Short.valueOf(type));
                    bizRmConfigDetails.add(bizRmConfigDetail);
                }
            }
        }
        return bizRmConfigDetails;
    }
}
