package com.scxd.beans.pojo;

public class BizRmConfigDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BIZ_RM_CONFIG_DETAIL.ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BIZ_RM_CONFIG_DETAIL.RMID
     *
     * @mbggenerated
     */
    private String rmid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BIZ_RM_CONFIG_DETAIL.PZDM
     *
     * @mbggenerated
     */
    private String pzdm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BIZ_RM_CONFIG_DETAIL.TYPE
     *
     * @mbggenerated
     */
    private Short type;

    private  String pzzdm;

    public String getPzzdm() {
        return pzzdm;
    }

    public void setPzzdm(String pzzdm) {
        this.pzzdm = pzzdm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BIZ_RM_CONFIG_DETAIL.ID
     *
     * @return the value of BIZ_RM_CONFIG_DETAIL.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BIZ_RM_CONFIG_DETAIL.ID
     *
     * @param id the value for BIZ_RM_CONFIG_DETAIL.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BIZ_RM_CONFIG_DETAIL.RMID
     *
     * @return the value of BIZ_RM_CONFIG_DETAIL.RMID
     *
     * @mbggenerated
     */
    public String getRmid() {
        return rmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BIZ_RM_CONFIG_DETAIL.RMID
     *
     * @param rmid the value for BIZ_RM_CONFIG_DETAIL.RMID
     *
     * @mbggenerated
     */
    public void setRmid(String rmid) {
        this.rmid = rmid == null ? null : rmid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BIZ_RM_CONFIG_DETAIL.PZDM
     *
     * @return the value of BIZ_RM_CONFIG_DETAIL.PZDM
     *
     * @mbggenerated
     */
    public String getPzdm() {
        return pzdm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BIZ_RM_CONFIG_DETAIL.PZDM
     *
     * @param pzdm the value for BIZ_RM_CONFIG_DETAIL.PZDM
     *
     * @mbggenerated
     */
    public void setPzdm(String pzdm) {
        this.pzdm = pzdm == null ? null : pzdm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BIZ_RM_CONFIG_DETAIL.TYPE
     *
     * @return the value of BIZ_RM_CONFIG_DETAIL.TYPE
     *
     * @mbggenerated
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BIZ_RM_CONFIG_DETAIL.TYPE
     *
     * @param type the value for BIZ_RM_CONFIG_DETAIL.TYPE
     *
     * @mbggenerated
     */
    public void setType(Short type) {
        this.type = type;
    }
}