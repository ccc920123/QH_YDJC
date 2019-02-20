package com.scxd.service.impl;

import com.scxd.beans.biz.*;
import com.scxd.beans.common.User;
import com.scxd.beans.common.WritBean;
import com.scxd.beans.pojo.BizVioForce;
import com.scxd.beans.pojo.BizVioViolation;
import com.scxd.common.ImgTools;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.WritDaoMapper;
import com.scxd.service.WritService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WritServiceImpl implements WritService {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private WritDaoMapper wdm;

    @Autowired
    private HttpSession session;

    //条件查询文书详情
    @Override
    public Map getWritByWslbWsbhScsj(Map map) throws Exception {
        User user = (User) session.getAttribute("userInfo");
        //获取部门编号
        String bmbh = user.getSsbmbh();
        map.put("bmbh", bmbh);
        //结束的时间要加一天
        if (!(map.get("jsrq") == null || map.get("jsrq").equals(""))){
            Date jsrq = format.parse((String)map.get("jsrq"));
            jsrq = new Date(jsrq.getTime() + 24*60*60*1000);
            map.put("jsrq",jsrq);
        }else{
            map.remove("jsrq");
        }

        if (!(map.get("ksrq") == null || map.get("ksrq").equals(""))){
            map.put("ksrq",format.parse((String)map.get("ksrq")));
        }else{
            map.remove("ksrq");
        }

        //分页查询，计算开始和结束的信息行数
        int page = (int) map.get("page");
        int start = (page - 1) * 10;
        int end = page * 10;
        map.put("start", start);
        map.put("end", end);

        int total = wdm.selectTotal(map);
        if (total == 0){//查询信息条数为0，直接返回查询条数0
            Map res = new HashMap();
            res.put("total",total);
            return res;
        }

        List<WritBean> result = wdm.selectWrit(map);
        for (int i = 0; i < result.size();i++){
            result.get(i).setXh(start + i + 1 + "");
        }

        Map res = new HashMap();
        res.put("result",result);
        res.put("total",total);
        return res;
    }

    //删除文书
    @Override
    public boolean removeWrit(Map map) throws Exception {
        int num = wdm.deleteWrit(map);
        return (num != 0)?true:false;
    }


    /**
     * 查询电子监控详情
     */
    @Override
    public SurveExtend getSurveInfo(String wsbh, HttpServletRequest request) throws Exception {
        SurveExtend result = wdm.checkSurve(wsbh);
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort();
        if (result != null){
            List<String> photos = result.getZps();
            if (photos != null && photos.size() > 0) {
                for (int i = 0; i < photos.size(); i++) {
                    String url = photos.get(i);
                    url = ImgTools.getLocalServiceUrl(url, basePath);
                    photos.set(i, url);
                }
            }
        }

        return (result != null)?result:null;
    }

    /**
     * 简易处罚详情
     */
    @Override
    public ViolationExtend getViolationInfo(String wsbh) throws Exception {
        ViolationExtend result = wdm.checkViolation(wsbh);
        return (result != null)?result:null;
    }

    /**
     * 强制措施详情
     */
    @Override
    public ForceExtend getForceInfo(String wsbh) throws Exception {

        ForceExtend result = wdm.checkForce(wsbh);

        //获取强制措施类型,并组装数组
        String qzcslx = result.getQzcslx();
        if (StringUtil.isNotEmpty(qzcslx)){
            String[] qzcs = new String[qzcslx.length()];
            for (int i = 0;i<qzcslx.length();i++){
                qzcs[i] = qzcslx.substring(i,i+1);
            }
        //循环结束，去code表中查出对应的值
        String[] qz = wdm.selectQzcslx(qzcs);
        //将qz中各值按/分开组成字符串
        qzcslx = "";
        for (int i = 0;i<qz.length;i++){
            qzcslx += qz[i] + "/";
        }
        //将结果存放到ForceExtend中
        result.setQzcsname(qzcslx);
        }

        //获取收缴项目名称数据数据，将#换成顿号
        String sjwpmc = result.getSjwpmc().replace('#','/');
        result.setSjwpmc(sjwpmc);

        //将违法行为 放入字符串数组中
       ArrayList<String> wfxw = new ArrayList<>();
       if (StringUtil.isNotEmpty(result.getWfxw1()))
           wfxw.add(result.getWfxw1());
       if (StringUtil.isNotEmpty(result.getWfxw2()))
           wfxw.add(result.getWfxw2());
       if (StringUtil.isNotEmpty(result.getWfxw3()))
           wfxw.add(result.getWfxw3());
       if (StringUtil.isNotEmpty(result.getWfxw4()))
           wfxw.add(result.getWfxw4());
       if (StringUtil.isNotEmpty(result.getWfxw5()))
           wfxw.add(result.getWfxw5());
       //通过违法代码获取违法信息
        List<WfdmExtend> wf = wdm.selectWfdm(wfxw);
        //将标准值和实测值装进list
        if (wf.size() >= 1){
            wf.get(0).setBzz(result.getBzz1());
            wf.get(0).setScz(result.getScz1());
        }
        if (wf.size() >= 2){
            wf.get(1).setBzz(result.getBzz2());
            wf.get(1).setScz(result.getScz2());
        }
        if (wf.size() >= 3){
            wf.get(2).setBzz(result.getBzz3());
            wf.get(2).setScz(result.getScz3());
        }
        if (wf.size() >= 4){
            wf.get(3).setBzz(result.getBzz4());
            wf.get(3).setScz(result.getScz4());
        }
        if (wf.size() >= 5){
            wf.get(4).setBzz(result.getBzz5());
            wf.get(4).setScz(result.getScz5());
        }
        result.setWf(wf);

        return (result != null)?result:null;
    }


}

