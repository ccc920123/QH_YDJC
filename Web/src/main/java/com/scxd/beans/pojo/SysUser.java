package com.scxd.beans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scxd.common.CustomJosnDateYearDeserializer;

import java.util.Date;

public class SysUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_ID
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.SSBMBH
     *
     * @mbggenerated
     */
    private String ssbmbh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.LOGINNAME
     *
     * @mbggenerated
     */
    private String loginname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.REALNAME
     *
     * @mbggenerated
     */
    private String realname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.SFZHM
     *
     * @mbggenerated
     */
    private String sfzhm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.MMYXQ
     *
     * @mbggenerated
     */
    private Date mmyxq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.LXDH
     *
     * @mbggenerated
     */
    private String lxdh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.KQIPXZ
     *
     * @mbggenerated
     */
    private Integer kqipxz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.IPADDRESS1
     *
     * @mbggenerated
     */
    private String ipaddress1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.IPADDRESS2
     *
     * @mbggenerated
     */
    private String ipaddress2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.IPADDRESS3
     *
     * @mbggenerated
     */
    private String ipaddress3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.ZT
     *
     * @mbggenerated
     */
    private Integer zt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.LXDZ
     *
     * @mbggenerated
     */
    private String lxdz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.BZ
     *
     * @mbggenerated
     */
    private String bz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CZSJ
     *
     * @mbggenerated
     */
    private Date czsj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CZRYZH
     *
     * @mbggenerated
     */
    private String czryzh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.PASSWORD
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.LASTTIME
     *
     * @mbggenerated
     */
    private Date lasttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.PREIPADDRESS
     *
     * @mbggenerated
     */
    private String preipaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.SFMJ
     *
     * @mbggenerated
     */
    private String sfmj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.YGBH
     *
     * @mbggenerated
     */
    private String ygbh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.YHYXQ
     *
     * @mbggenerated
     */
    private Date yhyxq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.YXQZT
     *
     * @mbggenerated
     */
    private String yxqzt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.SBCS
     *
     * @mbggenerated
     */
    private Integer sbcs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.SBSJ
     *
     * @mbggenerated
     */
    private Date sbsj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.SFSYPDA
     *
     * @mbggenerated
     */
    private Integer sfsypda;


    private String dlkssj;
    private String dljssj;
    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDlkssj() {
        return dlkssj;
    }

    public void setDlkssj(String dlkssj) {
        this.dlkssj = dlkssj;
    }

    public String getDljssj() {
        return dljssj;
    }

    public void setDljssj(String dljssj) {
        this.dljssj = dljssj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_ID
     *
     * @return the value of sys_user.USER_ID
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_ID
     *
     * @param userId the value for sys_user.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.SSBMBH
     *
     * @return the value of sys_user.SSBMBH
     *
     * @mbggenerated
     */
    public String getSsbmbh() {
        return ssbmbh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.SSBMBH
     *
     * @param ssbmbh the value for sys_user.SSBMBH
     *
     * @mbggenerated
     */
    public void setSsbmbh(String ssbmbh) {
        this.ssbmbh = ssbmbh == null ? null : ssbmbh.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.LOGINNAME
     *
     * @return the value of sys_user.LOGINNAME
     *
     * @mbggenerated
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.LOGINNAME
     *
     * @param loginname the value for sys_user.LOGINNAME
     *
     * @mbggenerated
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.REALNAME
     *
     * @return the value of sys_user.REALNAME
     *
     * @mbggenerated
     */
    public String getRealname() {
        return realname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.REALNAME
     *
     * @param realname the value for sys_user.REALNAME
     *
     * @mbggenerated
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.SFZHM
     *
     * @return the value of sys_user.SFZHM
     *
     * @mbggenerated
     */
    public String getSfzhm() {
        return sfzhm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.SFZHM
     *
     * @param sfzhm the value for sys_user.SFZHM
     *
     * @mbggenerated
     */
    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm == null ? null : sfzhm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.MMYXQ
     *
     * @return the value of sys_user.MMYXQ
     *
     * @mbggenerated
     */
    @JsonDeserialize(using = CustomJosnDateYearDeserializer.class)
    public Date getMmyxq() {
        return mmyxq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.MMYXQ
     *
     * @param mmyxq the value for sys_user.MMYXQ
     *
     * @mbggenerated
     */
    public void setMmyxq(Date mmyxq) {
        this.mmyxq = mmyxq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.LXDH
     *
     * @return the value of sys_user.LXDH
     *
     * @mbggenerated
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.LXDH
     *
     * @param lxdh the value for sys_user.LXDH
     *
     * @mbggenerated
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.KQIPXZ
     *
     * @return the value of sys_user.KQIPXZ
     *
     * @mbggenerated
     */
    public Integer getKqipxz() {
        return kqipxz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.KQIPXZ
     *
     * @param kqipxz the value for sys_user.KQIPXZ
     *
     * @mbggenerated
     */
    public void setKqipxz(Integer kqipxz) {
        this.kqipxz = kqipxz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.IPADDRESS1
     *
     * @return the value of sys_user.IPADDRESS1
     *
     * @mbggenerated
     */
    public String getIpaddress1() {
        return ipaddress1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.IPADDRESS1
     *
     * @param ipaddress1 the value for sys_user.IPADDRESS1
     *
     * @mbggenerated
     */
    public void setIpaddress1(String ipaddress1) {
        this.ipaddress1 = ipaddress1 == null ? null : ipaddress1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.IPADDRESS2
     *
     * @return the value of sys_user.IPADDRESS2
     *
     * @mbggenerated
     */
    public String getIpaddress2() {
        return ipaddress2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.IPADDRESS2
     *
     * @param ipaddress2 the value for sys_user.IPADDRESS2
     *
     * @mbggenerated
     */
    public void setIpaddress2(String ipaddress2) {
        this.ipaddress2 = ipaddress2 == null ? null : ipaddress2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.IPADDRESS3
     *
     * @return the value of sys_user.IPADDRESS3
     *
     * @mbggenerated
     */
    public String getIpaddress3() {
        return ipaddress3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.IPADDRESS3
     *
     * @param ipaddress3 the value for sys_user.IPADDRESS3
     *
     * @mbggenerated
     */
    public void setIpaddress3(String ipaddress3) {
        this.ipaddress3 = ipaddress3 == null ? null : ipaddress3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.ZT
     *
     * @return the value of sys_user.ZT
     *
     * @mbggenerated
     */
    public Integer getZt() {
        return zt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.ZT
     *
     * @param zt the value for sys_user.ZT
     *
     * @mbggenerated
     */
    public void setZt(Integer zt) {
        this.zt = zt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.LXDZ
     *
     * @return the value of sys_user.LXDZ
     *
     * @mbggenerated
     */
    public String getLxdz() {
        return lxdz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.LXDZ
     *
     * @param lxdz the value for sys_user.LXDZ
     *
     * @mbggenerated
     */
    public void setLxdz(String lxdz) {
        this.lxdz = lxdz == null ? null : lxdz.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.BZ
     *
     * @return the value of sys_user.BZ
     *
     * @mbggenerated
     */
    public String getBz() {
        return bz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.BZ
     *
     * @param bz the value for sys_user.BZ
     *
     * @mbggenerated
     */
    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CZSJ
     *
     * @return the value of sys_user.CZSJ
     *
     * @mbggenerated
     */
    public Date getCzsj() {
        return czsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.CZSJ
     *
     * @param czsj the value for sys_user.CZSJ
     *
     * @mbggenerated
     */
    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CZRYZH
     *
     * @return the value of sys_user.CZRYZH
     *
     * @mbggenerated
     */
    public String getCzryzh() {
        return czryzh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.CZRYZH
     *
     * @param czryzh the value for sys_user.CZRYZH
     *
     * @mbggenerated
     */
    public void setCzryzh(String czryzh) {
        this.czryzh = czryzh == null ? null : czryzh.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.PASSWORD
     *
     * @return the value of sys_user.PASSWORD
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.PASSWORD
     *
     * @param password the value for sys_user.PASSWORD
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.LASTTIME
     *
     * @return the value of sys_user.LASTTIME
     *
     * @mbggenerated
     */
    public Date getLasttime() {
        return lasttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.LASTTIME
     *
     * @param lasttime the value for sys_user.LASTTIME
     *
     * @mbggenerated
     */
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.PREIPADDRESS
     *
     * @return the value of sys_user.PREIPADDRESS
     *
     * @mbggenerated
     */
    public String getPreipaddress() {
        return preipaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.PREIPADDRESS
     *
     * @param preipaddress the value for sys_user.PREIPADDRESS
     *
     * @mbggenerated
     */
    public void setPreipaddress(String preipaddress) {
        this.preipaddress = preipaddress == null ? null : preipaddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.SFMJ
     *
     * @return the value of sys_user.SFMJ
     *
     * @mbggenerated
     */
    public String getSfmj() {
        return sfmj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.SFMJ
     *
     * @param sfmj the value for sys_user.SFMJ
     *
     * @mbggenerated
     */
    public void setSfmj(String sfmj) {
        this.sfmj = sfmj == null ? null : sfmj.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.YGBH
     *
     * @return the value of sys_user.YGBH
     *
     * @mbggenerated
     */
    public String getYgbh() {
        return ygbh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.YGBH
     *
     * @param ygbh the value for sys_user.YGBH
     *
     * @mbggenerated
     */
    public void setYgbh(String ygbh) {
        this.ygbh = ygbh == null ? null : ygbh.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.YHYXQ
     *
     * @return the value of sys_user.YHYXQ
     *
     * @mbggenerated
     */
    public Date getYhyxq() {
        return yhyxq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.YHYXQ
     *
     * @param yhyxq the value for sys_user.YHYXQ
     *
     */
    @JsonDeserialize(using = CustomJosnDateYearDeserializer.class)
    public void setYhyxq(Date yhyxq) {
        this.yhyxq = yhyxq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.YXQZT
     *
     * @return the value of sys_user.YXQZT
     *
     * @mbggenerated
     */
    public String getYxqzt() {
        return yxqzt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.YXQZT
     *
     * @param yxqzt the value for sys_user.YXQZT
     *
     * @mbggenerated
     */
    public void setYxqzt(String yxqzt) {
        this.yxqzt = yxqzt == null ? null : yxqzt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.SBCS
     *
     * @return the value of sys_user.SBCS
     *
     * @mbggenerated
     */
    public Integer getSbcs() {
        return sbcs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.SBCS
     *
     * @param sbcs the value for sys_user.SBCS
     *
     * @mbggenerated
     */
    public void setSbcs(Integer sbcs) {
        this.sbcs = sbcs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.SBSJ
     *
     * @return the value of sys_user.SBSJ
     *
     * @mbggenerated
     */
    public Date getSbsj() {
        return sbsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.SBSJ
     *
     * @param sbsj the value for sys_user.SBSJ
     *
     * @mbggenerated
     */
    public void setSbsj(Date sbsj) {
        this.sbsj = sbsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.SFSYPDA
     *
     * @return the value of sys_user.SFSYPDA
     *
     * @mbggenerated
     */
    public Integer getSfsypda() {
        return sfsypda;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.SFSYPDA
     *
     * @param sfsypda the value for sys_user.SFSYPDA
     *
     * @mbggenerated
     */
    public void setSfsypda(Integer sfsypda) {
        this.sfsypda = sfsypda;
    }
}