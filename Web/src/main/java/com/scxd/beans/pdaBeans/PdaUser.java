package com.scxd.beans.pdaBeans;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:12 2018/6/12
 * @Modified By:
 */
public class PdaUser implements PDABaseRequestBean {
  private String 	user;
    private String 	password;
    private String 	bmbh;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
