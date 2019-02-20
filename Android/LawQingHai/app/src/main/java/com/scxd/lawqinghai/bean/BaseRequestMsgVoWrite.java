/**
 * @�ļ�����BaseRequestMsgVoWrite.java
 * @author Administrator
 * @�������� 2016-8-18
 * @����������
 */
package com.scxd.lawqinghai.bean;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @类名: BaseRequestMsgVoWrite
 * @功能描述: 接口上传写入类
 * @作者: chenpan
 * @时间: 2016-9-26
 * @版权申明:四川星盾科技有限公司
 * @最后修改者:
 * @最后修改内容:
 */
public class BaseRequestMsgVoWrite {
    @XStreamAsAttribute
    private Object vioSurveil;

    public BaseRequestMsgVoWrite(Object vehcrpara) {
        this.vioSurveil = vehcrpara;
    }

    public Object getVehcrpara() {
        return vioSurveil;
    }

    public void setVehcrpara(Object vehcrpara) {
        this.vioSurveil = vehcrpara;
    }
}
