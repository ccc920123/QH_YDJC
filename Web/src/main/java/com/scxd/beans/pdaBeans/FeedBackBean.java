package com.scxd.beans.pdaBeans;

import com.scxd.beans.pojo.BizAlarmInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 10:04 2018/6/20
 * @Modified By:
 */
@XmlRootElement( name = "root" )
public class FeedBackBean {
    @XmlElement(name = "feedback")
    private BizAlarmInfo bizAlarmInfo;



    public void setBizAlarmInfo(BizAlarmInfo bizAlarmInfo) {
        this.bizAlarmInfo = bizAlarmInfo;
    }
}
