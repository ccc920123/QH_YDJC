/**
  * Copyright 2018 bejson.com 
  */
package com.scxd.beans.pdaBeans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Auto-generated: 2018-06-20 10:58:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@XmlRootElement( name = "root" )
public class Root {
    @XmlElement(name = "head")
    private Head head;
     public Head getHead() {
         return head;
     }

}