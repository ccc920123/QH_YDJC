package com.scxd.service.management.impl;

import com.scxd.beans.common.ListTotal;
import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SCY_LOG;
import com.scxd.beans.pojo.SYS_BLACK_LIST;
import com.scxd.common.DateUtils;
import com.scxd.common.Response;
import com.scxd.dao.mapper.SCY_LOGMapper;
import com.scxd.dao.mapper.SYS_BLACK_LISTMapper;
import com.scxd.service.management.ifaces.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:19 2018/9/28
 * @Modified By:
 */
@Service
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    SYS_BLACK_LISTMapper sys_black_listMapper;
    @Resource
    SCY_LOGMapper scy_logMapper;
    @Autowired
    HttpSession session;

    @Override
    public Response getBlackList(String bmbh, String type, String name, Integer pageindex, Integer pagesize) {

        int total = sys_black_listMapper.getBlackListCount(type, name);
        List<SYS_BLACK_LIST> listList = null;
        if (total > 0) {
            try {
                listList = sys_black_listMapper.getBlackListTable(type, name, pageindex, pagesize);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        ListTotal listTotal = new ListTotal(listList, total);

        return new Response().success(listTotal);
    }

    @Override
    public Response deleteBlack(String blackId) {
        SYS_BLACK_LIST sys_black_list=sys_black_listMapper.selectBeanByID(blackId);
        int n = sys_black_listMapper.deleteByID(blackId);
        User user= (User) session.getAttribute("userInfo");

        if (n > 0) {
            SCY_LOG scy_log = new SCY_LOG();
            scy_log.setId(UUID.randomUUID().toString());
            scy_log.setValue(sys_black_list.getValue());
            scy_log.setEvent("1");
            scy_log.setJg("0");
            scy_log.setLy("2");
            scy_log.setModule("1");
            scy_log.setSffcgyw("1");
            scy_log.setSfhxgn("1");
            String ms="用户"+user.getLoginname()+"与"+
                    DateUtils.dateToStrSS(new Date())+"将"+sys_black_list.getValue()+"从黑名单中移除";
            scy_log.setMs(ms);
            scy_log.setType("4");
            scy_logMapper.insertSelective(scy_log);
            return new Response().success();
        } else {
            return new Response().failure("删除失败");
        }
    }
}
