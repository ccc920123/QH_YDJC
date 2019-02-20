/**
  * Copyright 2018 bejson.com 
  */
package com.scxd.beans.pdaBeans;

import javax.xml.bind.annotation.XmlElement;

/**
 * Auto-generated: 2018-06-20 10:58:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Head {
    @XmlElement(name = "code")
    private String code;
    @XmlElement(name = "message")
    private String message;
    @XmlElement(name = "value")
    private String value;
     public String getCode() {
         return code;
     }

     public String getMessage() {
         return message;
     }

     public String getValue() {
         return value;
     }

}