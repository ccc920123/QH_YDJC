package com.scxd.dao.mapper;

import com.scxd.beans.biz.ForceExtend;

import com.scxd.beans.biz.SurveExtend;
import com.scxd.beans.biz.ViolationExtend;
import com.scxd.beans.biz.WfdmExtend;
import com.scxd.beans.common.WritBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文书操作数据库访问接口
 */
public interface WritDaoMapper {
    //查询文书总数
    int selectTotal(Map map);
    //文书查询
    List<WritBean> selectWrit(Map map);
    //删除文书
    int deleteWrit(Map map);
    //通过wsbh查询电子监控文书详情
    SurveExtend checkSurve(String wsbh);

    //通过wsbh查询强制措施文书详情
    ForceExtend checkForce(String wsbh);
    //去代码表中查询强制措施名称
    String[] selectQzcslx(@Param("qzcs") String[] qzcs);
    //在违法代码表中查询违法行为 违法内容法律依据
    List<WfdmExtend> selectWfdm(@Param("wfxw") List<String> wfxw);

    //通过wsbh查询简易处罚文书详情
    ViolationExtend checkViolation(String wsbh);


}
