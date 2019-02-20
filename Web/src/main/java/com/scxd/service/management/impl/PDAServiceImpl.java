package com.scxd.service.management.impl;

import com.scxd.beans.biz.Q02Return;
import com.scxd.beans.management.PdaBean;
import com.scxd.beans.pojo.SysPdaInfo;
import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;
import com.scxd.dao.management.ifaces.PDADao;
import com.scxd.dao.mapper.SysPdaInfoMapper;
import com.scxd.dao.mapper.SysPdaVersionMapper;
import com.scxd.service.management.ifaces.PDAService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 10:45 2018/6/6
 * @Modified By:
 */
@Service
public class PDAServiceImpl implements PDAService {
    @Resource
    private SysPdaInfoMapper sysPdaInfoMapper;
    @Resource
    private SysPdaVersionMapper sysPdaVersionMapper;
    @Resource
    private PDADao pdaDao;

    @Override
    public Response getPdaMessage(String pdamc, String pdawym, String bmbh, String bhxj, String pageindex, String pagesize) {
        Response response = null;
        List<PdaBean> list = null;
//        HashMap<String ,String> map=new HashMap<>();
//        map.put("mc",pdamc);
//        map.put("wym",pdawym);
//        map.put("bmbh",bmbh);
//        map.put("bhxj",bhxj);
//        map.put("pageindex",pageindex);
//        map.put("pagesize",pagesize);
//        List<PdaBean>  listc = sysPdaInfoMapper.getPdaMessageCount(map);
//        int count=listc==null?0:listc.size();
//       List<PdaBean> list= sysPdaInfoMapper.getPdaMessage(map);
//        Map<String ,Object> map1=new HashMap<>();
//        map1.put("count",count);
//        map1.put("list",list);
        try {
            list = pdaDao.getPdaMessage(pdamc, pdawym, bmbh, bhxj, pageindex, pagesize);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure("查询数据异常");
        }
        if (list.size() > 1) {
            response = new Response().success(list);
        } else {
            response = new Response().failure("未查询到数据");
        }
        return response;

    }

    @Override
    public Response addPdaInfo(SysPdaInfo pdaInfo, String name) {
        Response response = null;
        pdaInfo.setCjr(name);
        pdaInfo.setCjsj(new Date());
        int i = sysPdaInfoMapper.insert(pdaInfo);
        if (i > 0) {
            response = new Response().success("新增成功");
        } else {
            response = new Response().failure("新增PDA失败");
        }
        return response;
    }

    @Override
    public Response deletePda(String pdaid) {
        Response response = null;

        int i = sysPdaInfoMapper.deleteByPrimaryKey(pdaid);
        if (i > 0) {
            response = new Response().success("删除成功");
        } else {
            response = new Response().failure("删除失败");
        }
        return response;

    }

    @Override
    public Response updatePda(SysPdaInfo pdaInfo, String name) {

        Response response = null;
        pdaInfo.setGxr(name);
        pdaInfo.setGxsj(new Date());
        int i = sysPdaInfoMapper.updateByPrimaryKeySelective(pdaInfo);
        if (i > 0) {
            response = new Response().success("更新成功");
        } else {
            response = new Response().failure("更新PDA失败");
        }
        return response;
    }

    @Override
    public Response addPdaVersion(SysPdaVersion pdaInfo, String name) {
        Response response = null;
        pdaInfo.setCzryzh(name);
        pdaInfo.setCzsj(new Date());
        int i = sysPdaVersionMapper.insert(pdaInfo);
        if (i > 0) {
            response = new Response().success("新增成功");
        } else {
            response = new Response().failure("新增PDA版本失败");
        }
        return response;
    }

    @Override
    public Response getPdaVersion(String bmbh, String version, String desc, int page, int pageSize) {
        Response response = null;
        Map<String ,Object> map2=new HashMap<>();
        map2.put("bmbh",bmbh);
        map2.put("version",version);
        map2.put("desc",desc);
        map2.put("page",page);
        map2.put("pageSize",pageSize);
        List<SysPdaVersion> list=sysPdaVersionMapper.selectPdaVersion(map2);
        int count= sysPdaVersionMapper.selectPdaVersionTotal();
        String dicVersion="1.0.0";
        Q02Return result = sysPdaVersionMapper.selectPdaVersionBybmbh(bmbh);
        if (result!=null){
            dicVersion=result.getDictionary();
        }
        if (count > 0) {
            Map<String ,Object> map=new HashMap<>();
            map.put("total",count);
            map.put("datas",list);
            map.put("dictionary",dicVersion);
            response = new Response().success(map);
        } else {
            response = new Response().failure("未查询到pda版本信息");
        }
        return response;
    }

    @Override
    public Response deletePdaVersion(String pdaversion) {
        Response response = null;
        String fileUrl=sysPdaVersionMapper.selectByPrimaryKey(pdaversion).getUrl();
        int i = sysPdaVersionMapper.deleteByPrimaryKey(pdaversion);
        if (i > 0) {
        //这里删除文件
            File file=new File(fileUrl);
            if (file.exists()){
                file.delete();
            }
            response = new Response().success("删除成功");
        } else {
            response = new Response().failure("删除失败");
        }
        return response;
    }
}
