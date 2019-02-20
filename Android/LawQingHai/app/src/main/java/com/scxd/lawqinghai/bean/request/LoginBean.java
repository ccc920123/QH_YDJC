package com.scxd.lawqinghai.bean.request;

import java.io.Serializable;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LoginBean implements Serializable {
    
    private String user;
    private String password;
    private String bmbh;


    public String getLoginname() {
        return user;
    }

    public void setLoginname(String loginname) {
        this.user = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public LoginBean( String loginname, String password, String bmbh) {
    

        this.user = loginname;
        this.password = password;
        this.bmbh = bmbh;
    }
}
