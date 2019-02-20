package com.scxd.beans.webBeans;

import com.scxd.beans.biz.BizVioInfo;
import com.scxd.beans.pdaBeans.Head;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "root")
public class WebRoot {
    @XmlElement(name = "head")
    private Head head;

    public Head getHead() {
        return head;
    }

    @XmlElement(name = "body")
    private WebBody body;

    public WebBody getBody() {
        return body;
    }

}