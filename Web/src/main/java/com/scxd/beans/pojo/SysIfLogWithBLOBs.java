package com.scxd.beans.pojo;

public class SysIfLogWithBLOBs extends SysIfLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_IF_LOG.INDATA
     *
     * @mbggenerated
     */
    private String indata;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_IF_LOG.OUTDATA
     *
     * @mbggenerated
     */
    private String outdata;
    private String errormsg;

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_IF_LOG.INDATA
     *
     * @return the value of SYS_IF_LOG.INDATA
     *
     * @mbggenerated
     */
    public String getIndata() {
        return indata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_IF_LOG.INDATA
     *
     * @param indata the value for SYS_IF_LOG.INDATA
     *
     * @mbggenerated
     */
    public void setIndata(String indata) {
        this.indata = indata == null ? null : indata.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_IF_LOG.OUTDATA
     *
     * @return the value of SYS_IF_LOG.OUTDATA
     *
     * @mbggenerated
     */
    public String getOutdata() {
        return outdata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_IF_LOG.OUTDATA
     *
     * @param outdata the value for SYS_IF_LOG.OUTDATA
     *
     * @mbggenerated
     */
    public void setOutdata(String outdata) {
        this.outdata = outdata == null ? null : outdata.trim();
    }
}