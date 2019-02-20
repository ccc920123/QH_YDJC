package com.scxd.dao.impl;

import com.scxd.beans.common.MenuBean;
import com.scxd.beans.common.Role;
import com.scxd.beans.common.User;
import com.scxd.common.*;
import com.scxd.dao.UserDao;
import com.scxd.dao.util.QueryUtil;
import com.scxd.db.JdbcUtil;
import com.szdt.security.des.DESUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hdfs on 2018/5/15.
 */
@Component
public class UserDaoImpl implements UserDao {

    /* 密钥 */
    private final String PRIVATE_KEY = "5b0e6add6cbff7bdafd9b48be476dc81b65294d3812cda23";

    /* 角色 */
    private Role role;

    /* 菜单 */
    private MenuBean menuBean;

    /**
     * 功能描述：用户角色克隆方法
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Role getRoleClone() throws CloneNotSupportedException {
        if (null == this.role) {
            this.role = new Role();
        }
        return this.role.clone();
    }

    /**
     * 功能描述：菜单克隆方法
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public MenuBean getMenueClone() throws CloneNotSupportedException {
        if (null == menuBean) {
            menuBean = new MenuBean();
        }
        return menuBean.clone();
    }

    @Override
    public User getUserInfoByUsername(String username) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT A.*,\n");
        sqlBuilder.append("       B.NAME AS SSBMNAME,\n");
        sqlBuilder.append("       DBMS_LOB.SUBSTR(C.ROLES, 4000) ROLES,\n");
        sqlBuilder.append("       C.ROLESNAME,\n");
        sqlBuilder.append("       b.id deptid\n");
        sqlBuilder.append("  FROM SYS_USER A\n");
        sqlBuilder.append("  LEFT JOIN SYS_DEPARTMENT B\n");
        sqlBuilder.append("    ON A.SSBMBH = B.BMBH\n");
        sqlBuilder.append("  LEFT JOIN (SELECT A.USER_ID,\n");
        sqlBuilder.append("                    WM_CONCAT(TO_CHAR(C.ROLE_ID)) AS ROLES,\n");
        sqlBuilder.append("                    '' AS ROLESNAME\n");
        sqlBuilder.append("               FROM SYS_USER A\n");
        sqlBuilder.append("               JOIN SYS_USER_ROLE B\n");
        sqlBuilder.append("                 ON A.USER_ID = B.USER_ID\n");
        sqlBuilder.append("               JOIN SYS_ROLE C\n");
        sqlBuilder.append("                 ON B.ROLE_ID = C.ROLE_ID\n");
        sqlBuilder.append("              GROUP BY A.USER_ID) C\n");
        sqlBuilder.append("    ON A.USER_ID = C.USER_ID\n");
        sqlBuilder.append(" WHERE A.LOGINNAME = ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                User userTmp = new User();
                userTmp.setUser_id(rs.getString("USER_ID"));
                userTmp.setBz(StringEx.nullToStr(rs.getString("BZ")));
                userTmp.setCzryzh(StringEx.nullToStr(rs.getString("CZRYZH")));
                userTmp.setCzsj(StringEx.nullToStr(rs.getString("CZSJ")));
                userTmp.setIpaddress1(StringEx.nullToStr(rs.getString("IPADDRESS1")));

                userTmp.setIpaddress2(StringEx.nullToStr(rs.getString("IPADDRESS2")));
                userTmp.setIpaddress3(StringEx.nullToStr(rs.getString("IPADDRESS3")));
                userTmp.setKqipxz(rs.getInt("KQIPXZ"));
                userTmp.setLasttime(StringEx.nullToStr(rs.getString("LASTTIME")));
                userTmp.setLoginname(StringEx.nullToStr(rs.getString("LOGINNAME")));

                userTmp.setLxdh(StringEx.nullToStr(rs.getString("LXDH")));
                userTmp.setLxdz(StringEx.nullToStr(rs.getString("LXDZ")));
                userTmp.setMmyxq(StringEx.nullToStr(rs.getString("MMYXQ")));
                userTmp.setPreipaddress(StringEx.nullToStr(rs.getString("PREIPADDRESS")));

                userTmp.setRealname(StringEx.nullToStr(rs.getString("REALNAME")));
                userTmp.setSbcs(rs.getInt("SBCS"));
                userTmp.setSbsj(StringEx.nullToStr(rs.getString("SBSJ")));
                userTmp.setSfmj(StringEx.nullToStr(rs.getString("SFMJ")));

                userTmp.setSsbmbh(StringEx.nullToStr(rs.getString("SSBMbh")));
                userTmp.setYgbh(StringEx.nullToStr(rs.getString("YgBH")));
                userTmp.setYhyxq(StringEx.nullToStr(rs.getString("YHYXQ")));
                userTmp.setYxqzt(StringEx.nullToStr(rs.getString("YXQZT")));
                userTmp.setZt(rs.getInt("ZT"));
                userTmp.setRoles(StringEx.nullToStr(rs.getString("ROLES")));

                userTmp.setRolesname(StringEx.nullToStr(rs.getString("ROLESNAME")));
                userTmp.setSsbmname(StringEx.nullToStr(rs.getString("SSBMNAME")));
                userTmp.setDeptId(Integer.valueOf(StringEx.nullToStr(rs.getString("DEPTID"))));
                return userTmp;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void merge(User user) throws Exception {
        StringBuilder sqlbuilder = new StringBuilder();
        user.setPassword("888888");
        /*
        * 注释掉加密
        */
        // DESUtil desUtil = new DESUtil(PRIVATE_KEY);
        //user.setPassword(desUtil.encrypt(user.getPassword()));

        sqlbuilder.append("  MERGE INTO SYS_USER USERE");
        sqlbuilder.append(" USING (SELECT ? BZ，");
        sqlbuilder.append(" ? CZRYZH，");
        sqlbuilder.append(" SYSDATE CZSJ，");
        sqlbuilder.append(" ? IPADDRESS1，");
        sqlbuilder.append(" ? IPADDRESS2，");
        sqlbuilder.append(" ? IPADDRESS3， ");
        sqlbuilder.append(" ? KQIPXZ，");
        sqlbuilder.append(" TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') LASTTIME， ");
        sqlbuilder.append(" ? LOGINNAME，");
        sqlbuilder.append(" ? LXDH，");
        sqlbuilder.append(" ? LXDZ，");
        sqlbuilder.append(" TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') MMYXQ，");
        sqlbuilder.append(" ? PASSWORD，");
        sqlbuilder.append(" ? PREIPADDRESS，");
        sqlbuilder.append(" ? REALNAME，");
        sqlbuilder.append(" ? SBCS，");
        sqlbuilder.append(" TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') SBSJ，");
        sqlbuilder.append(" ? SFMJ，");
        sqlbuilder.append(" ? SFZHM，");
        sqlbuilder.append(" ? SSBMBH，");
        sqlbuilder.append(" ? USER_ID，");
        sqlbuilder.append(" ? YGBH，");
        sqlbuilder.append(" TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') YHYXQ，");
        sqlbuilder.append(" ? YXQZT，");
        sqlbuilder.append(" ? ZT ,");
        sqlbuilder.append("? SFSYPDA  ");
        sqlbuilder.append("FROM DUAL) A ");
        sqlbuilder.append("ON (USERE.LOGINNAME = A.LOGINNAME) ");
        sqlbuilder.append("WHEN MATCHED THEN ");
        sqlbuilder.append("        UPDATE ");
        sqlbuilder.append("SET BZ = A.BZ， ");
        sqlbuilder.append(" CZRYZH = A.CZRYZH， ");
        sqlbuilder.append(" CZSJ = A.CZSJ，");
        sqlbuilder.append("IPADDRESS1 = A.IPADDRESS1，");
        sqlbuilder.append(" IPADDRESS2 = A.IPADDRESS2，");
        sqlbuilder.append("IPADDRESS3 = A.IPADDRESS3，");
        sqlbuilder.append(" KQIPXZ = A.KQIPXZ，");

        sqlbuilder.append(" LXDH = A.LXDH，");
        sqlbuilder.append(" LXDZ = A.LXDZ，");
        sqlbuilder.append("MMYXQ = A.MMYXQ，");

        sqlbuilder.append(" REALNAME = A.REALNAME，");

        sqlbuilder.append(" SFMJ = A.SFMJ，");
        sqlbuilder.append(" SFZHM = A.SFZHM，");
        sqlbuilder.append("SSBMBH = A.SSBMBH，");
        sqlbuilder.append(" YGBH = A.YGBH，");
        sqlbuilder.append(" YHYXQ = A.YHYXQ，");
        sqlbuilder.append("YXQZT = A.YXQZT，");
        sqlbuilder.append(" ZT = A.ZT, ");
        sqlbuilder.append("SFSYPDA = A.SFSYPDA  ");

        sqlbuilder.append("WHEN NOT MATCHED THEN ");
        sqlbuilder.append("INSERT ");
        sqlbuilder.append("        (BZ，");
        sqlbuilder.append(" CZRYZH，");
        sqlbuilder.append(" CZSJ，");
        sqlbuilder.append(" IPADDRESS1，");
        sqlbuilder.append(" IPADDRESS2，");
        sqlbuilder.append(" IPADDRESS3，");
        sqlbuilder.append(" KQIPXZ，");
        sqlbuilder.append("  LASTTIME，");
        sqlbuilder.append(" LOGINNAME，");
        sqlbuilder.append(" LXDH，");
        sqlbuilder.append(" LXDZ，");
        sqlbuilder.append(" MMYXQ，");
        sqlbuilder.append(" PASSWORD，");
        sqlbuilder.append(" PREIPADDRESS，");
        sqlbuilder.append("                REALNAME，");
        sqlbuilder.append(" SBCS， ");
        sqlbuilder.append("SBSJ，");
        sqlbuilder.append(" SFMJ，");
        sqlbuilder.append(" SFZHM，");
        sqlbuilder.append(" SSBMBH，");
        sqlbuilder.append(" USER_ID，");
        sqlbuilder.append(" YGBH，");
        sqlbuilder.append(" YHYXQ，");
        sqlbuilder.append("                YXQZT，");
        sqlbuilder.append(" ZT,");
        sqlbuilder.append("SFSYPDA)");
        sqlbuilder.append("VALUES");
        sqlbuilder.append("        (A.BZ，");
        sqlbuilder.append(" A.CZRYZH，");
        sqlbuilder.append(" SYSDATE，");
        sqlbuilder.append(" A.IPADDRESS1，");
        sqlbuilder.append(" A.IPADDRESS2，");
        sqlbuilder.append(" A.IPADDRESS3，");
        sqlbuilder.append("                A.KQIPXZ，");
        sqlbuilder.append(" A.LASTTIME，");
        sqlbuilder.append(" A.LOGINNAME，");
        sqlbuilder.append(" A.LXDH，");
        sqlbuilder.append(" A.LXDZ，");
        sqlbuilder.append(" A.MMYXQ，");
        sqlbuilder.append("                A.PASSWORD，");
        sqlbuilder.append(" A.PREIPADDRESS，");
        sqlbuilder.append(" A.REALNAME，");
        sqlbuilder.append(" A.SBCS，");
        sqlbuilder.append(" A.SBSJ，");
        sqlbuilder.append(" A.SFMJ，");
        sqlbuilder.append("                A.SFZHM，");
        sqlbuilder.append(" A.SSBMBH， ");
        sqlbuilder.append("SEQ_SYS_USER.NEXTVAL,");
        sqlbuilder.append("                A.YGBH，");
        sqlbuilder.append(" A.YHYXQ，");
        sqlbuilder.append(" A.YXQZT，");
        sqlbuilder.append(" A.ZT,");
        sqlbuilder.append("A.SFSYPDA)");

        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        ResultSet rs1 = null;
        try {
            conn = JdbcUtil.getConnection();


            //新增 先判断登录账号 和 身份证号码 是否均已存在
            String message = "";
            StringBuilder sql = null;
            String id = user.getUser_id();
            if (id.equals("0")) {
                //判断登录账号  是否已存在
                String loginname = user.getLoginname();
                sql = new StringBuilder();
                sql.append(" SELECT USER_ID FROM SYS_USER WHERE LOGINNAME=? ");
                ps = conn.prepareStatement(sql.toString());
                ps.setObject(1, StringEx.nullToStr(loginname));
                rs1 = ps.executeQuery();
                if (rs1.next()) {
                    int count = rs1.getInt(1);
                    if (count > 0) {
                        message = message + "该登录账号 " + loginname + " 已存在,";
                    }
                }
                //判断身份证号码  是否已存在
                String sfzhm = user.getSfzhm();
                sql = new StringBuilder();
                sql.append(" SELECT USER_ID  FROM SYS_USER WHERE SFZHM=? ");
                ps = conn.prepareStatement(sql.toString());
                ps.setObject(1, StringEx.nullToStr(sfzhm));
                rs1 = ps.executeQuery();
                if (rs1.next()) {
                    int count = rs1.getInt(1);
                    if (count > 0) {
                        message = message + "该身份证号码 " + sfzhm + " 已存在,";
                    }
                }
            } else {
                //修改  判断身份证号码  是否已存在
                String sfzhm = user.getSfzhm();
                sql = new StringBuilder();
                sql.append(" SELECT USER_ID  FROM SYS_USER WHERE SFZHM=? ");
                ps = conn.prepareStatement(sql.toString());
                ps.setObject(1, StringEx.nullToStr(sfzhm));
                rs1 = ps.executeQuery();
                if (rs1.next()) {
                    String userid = rs1.getString(1);
                    if (userid.equals(id)) {
                    } else {
                        message = message + "该身份证号码 " + sfzhm + " 已存在,";
                    }
                }
            }
            if (StringUtil.isNotEmpty(message)) {
                throw new Exception(message + "请重新输入。");
            }


            conn.setAutoCommit(false);

            //更新用户表
            ps = conn.prepareStatement(sqlbuilder.toString());
            ps.setObject(1, user.getBz());
            ps.setObject(2, user.getCzryzh());
            ps.setObject(3, user.getIpaddress1());
            ps.setObject(4, user.getIpaddress2());
            ps.setObject(5, user.getIpaddress3());

            ps.setObject(6, user.getKqipxz());
            ps.setObject(7, user.getLasttime());
            ps.setObject(8, user.getLoginname());
            ps.setObject(9, user.getLxdh());
            ps.setObject(10, user.getLxdz());

            ps.setObject(11, user.getMmyxq());
            ps.setObject(12, user.getPassword());
            ps.setObject(13, user.getPreipaddress());
            ps.setObject(14, user.getRealname());
            ps.setObject(15, user.getSbcs());

            ps.setObject(16, user.getSbsj());
            ps.setObject(17, user.getSfmj());
            ps.setObject(18, user.getSfzhm());
            ps.setObject(19, user.getSsbmbh());
            ps.setObject(20, user.getUser_id());

            ps.setObject(21, user.getYgbh());
            ps.setObject(22, user.getYhyxq());
            ps.setObject(23, user.getYxqzt());
            ps.setObject(24, user.getZt());
            ps.setObject(25, user.getSfsypda());

            ps.execute();

            //获取用户表id
            StringBuilder sqlBuilder2 = new StringBuilder();
            sqlBuilder2.append(" SELECT USER_ID FROM SYS_USER WHERE LOGINNAME=? ");
            ps2 = conn.prepareStatement(sqlBuilder2.toString());
            ps2.setObject(1, user.getLoginname());

            ResultSet rs = ps2.executeQuery();
            int userid = 0;
            while (rs.next()) {
                userid = rs.getInt("USER_ID");
            }

            //更新用户角色表
            //先删除
            String[] roles = user.getRoles().split(",");
            if (roles.length > 0) {
                // 批量添加
                StringBuilder sqlBuilder3 = new StringBuilder();
                sqlBuilder3.append(" DELETE FROM SYS_USER_ROLE WHERE USER_ID=? ");

                List list = new ArrayList();
                list.add(userid);
                commonExecute(sqlBuilder3.toString(), conn, list);

                for (String roleid : roles) {
                    StringBuilder sqlBuilder4 = new StringBuilder();
                    sqlBuilder4.append("  INSERT INTO SYS_USER_ROLE(ID, USER_ID, ROLE_ID,CREATETIME,CREATER)")
                            .append(" VALUES(SEQ_SYS_USER_ROLE.NEXTVAL,?,?,SYSDATE,?) ");
                    list = new ArrayList();
                    list.add(userid);
                    list.add(roleid);
                    list.add(user.getCzryzh());
                    commonExecute(sqlBuilder4.toString(), conn, list);
                }
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (rs1 != null) {
                rs1.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ps2 != null) {
                ps2.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private void commonExecute(String sql, Connection conn, List list) throws Exception {
        PreparedStatement ps4 = null;
        try {
            ps4 = conn.prepareStatement(sql.toString());
            int cnt = 1;
            for (Object item : list) {
                ps4.setObject(cnt, item);
                cnt = cnt + 1;
            }
            ps4.execute();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (ps4 != null) {
                ps4.close();
            }
        }
    }

    @Override
    public User getUseById(int id) throws Exception {
        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("  SELECT  A.*,B.NAME AS SSBMNAME,DBMS_LOB.SUBSTR(C.ROLES,4000) ROLES,C.ROLESNAME,ROWNUM AS RN\n");
        sbSql.append("FROM SYS_USER A\n");
        sbSql.append("LEFT JOIN SYS_DEPARTMENT B ON A.SSBMBH=B.BMBH\n");
        sbSql.append("LEFT JOIN \n");
        sbSql.append("(\n");
        sbSql.append("SELECT A.USER_ID,WM_CONCAT(TO_CHAR(C.ROLE_ID)) AS ROLES,'' AS ROLESNAME\n");
        sbSql.append("FROM SYS_USER A\n");
        sbSql.append("JOIN SYS_USER_ROLE B ON A.USER_ID=B.USER_ID\n");
        sbSql.append("JOIN SYS_ROLE C ON B.ROLE_ID=C.ROLE_ID\n");
        sbSql.append("GROUP BY A.USER_ID\n");
        sbSql.append(")C ON A.USER_ID=C.USER_ID ");

        sbSql.append(" WHERE A.USER_ID = ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<User> reads = readDepts(rs);

            return reads.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    /**
     * 删除
     *
     * @param user_id
     * @throws Exception
     */
    @Override
    public void delete(String user_id) throws Exception {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "DELETE FROM SYS_USER WHERE USER_ID = ? ";
            List list = new ArrayList();
            list.add(user_id);
            commonExecute(sql, conn, list);

            String sql2 = "DELETE FROM SYS_USER_ROLE WHERE USER_ID = ? ";
            commonExecute(sql2, conn, list);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 重置密码
     *
     * @param user_id
     * @throws Exception
     */
    @Override
    public void ResetPasswd(int user_id) throws Exception {
        Connection conn = null;
        try {
            String pwd = "88888888";
//            DESUtil desUtil = new DESUtil(PRIVATE_KEY);
//            pwd = desUtil.encrypt(pwd);

            StringBuilder sbl = new StringBuilder("");
            sbl.append(" UPDATE  SYS_USER SET PASSWORD=? WHERE USER_ID = ? ");

            conn = JdbcUtil.getConnection();
            List list = new ArrayList();
            list.add(pwd);
            list.add(user_id);
            commonExecute(sbl.toString(), conn, list);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }


    public List<User> readDepts(ResultSet rs) throws Exception {
        try {
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User userTmp = new User();
                userTmp.setUser_id(rs.getString("USER_ID"));
                userTmp.setBz(StringEx.nullToStr(rs.getString("BZ")));
                userTmp.setCzryzh(StringEx.nullToStr(rs.getString("CZRYZH")));
                userTmp.setCzsj(StringEx.nullToStr(rs.getString("CZSJ")));
                userTmp.setIpaddress1(StringEx.nullToStr(rs.getString("IPADDRESS1")));

                userTmp.setIpaddress2(StringEx.nullToStr(rs.getString("IPADDRESS2")));
                userTmp.setIpaddress3(StringEx.nullToStr(rs.getString("IPADDRESS3")));
                userTmp.setKqipxz(rs.getInt("KQIPXZ"));
                userTmp.setLasttime(StringEx.nullToStr(rs.getString("LASTTIME")));
                userTmp.setLoginname(StringEx.nullToStr(rs.getString("LOGINNAME")));

                userTmp.setLxdh(StringEx.nullToStr(rs.getString("LXDH")));
                userTmp.setLxdz(StringEx.nullToStr(rs.getString("LXDZ")));
                userTmp.setMmyxq(StringEx.nullToStr(rs.getString("MMYXQ")));
                userTmp.setPassword(StringEx.nullToStr(rs.getString("PASSWORD")));
                userTmp.setPreipaddress(StringEx.nullToStr(rs.getString("PREIPADDRESS")));

                userTmp.setRealname(StringEx.nullToStr(rs.getString("REALNAME")));
                userTmp.setSbcs(rs.getInt("SBCS"));
                userTmp.setSbsj(StringEx.nullToStr(rs.getString("SBSJ")));
                userTmp.setSfmj(StringEx.nullToStr(rs.getString("SFMJ")));
                userTmp.setSfzhm(StringEx.nullToStr(rs.getString("SFZHM")));

                userTmp.setSsbmbh(StringEx.nullToStr(rs.getString("SSBMbh")));
                userTmp.setYgbh(StringEx.nullToStr(rs.getString("YgBH")));
                userTmp.setYhyxq(StringEx.nullToStr(rs.getString("YHYXQ")));
                userTmp.setYxqzt(StringEx.nullToStr(rs.getString("YXQZT")));
                userTmp.setZt(rs.getInt("ZT"));

                userTmp.setRn(rs.getInt("RN"));
                userTmp.setRoles(StringEx.nullToStr(rs.getString("ROLES")));

                userTmp.setRolesname(StringEx.nullToStr(rs.getString("ROLESNAME")));
                //   userTmp.setRolesname(StringEx.nullToStr(StringEx.readCLOB(rs.getClob("ROLESNAME"))));
                userTmp.setSsbmname(StringEx.nullToStr(rs.getString("SSBMNAME")));

                list.add(userTmp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    /**
     * 获取用户列表（分页查询）
     *
     * @yhxm 用户姓名
     * @dlzh 登录账号
     * @bhxj 包含下级（1 是；2 否；）
     * @jgbh 机构编号
     */
    @Override
    public List<User> selectNextLev(String yhxm, String dlzh, String bhxj, String jgbh, int pageindex, int pagesize) throws Exception {

        StringBuilder sbsql = new StringBuilder("");

        //分页查询 最后人为添加一条记录表示总数
        sbsql.append("WITH DEPT AS(\n");

        sbsql.append(" SELECT  A.*,B.NAME AS SSBMNAME,DBMS_LOB.SUBSTR(C.ROLES,4000) ROLES,DBMS_LOB.SUBSTR(C.ROLESNAME,4000)  ROLESNAME,ROWNUM AS RN\n");
        sbsql.append("FROM SYS_USER A\n");
        sbsql.append("LEFT JOIN SYS_DEPARTMENT B ON A.SSBMBH=B.BMBH\n");
        sbsql.append("LEFT JOIN \n");
        sbsql.append("(\n");
        sbsql.append("SELECT A.USER_ID,WM_CONCAT(TO_CHAR(C.ROLE_ID)) AS ROLES,WM_CONCAT(TO_CHAR(C.NAME)) AS ROLESNAME\n");
        sbsql.append("FROM SYS_USER A\n");
        sbsql.append("JOIN SYS_USER_ROLE B ON A.USER_ID=B.USER_ID\n");
        sbsql.append("JOIN SYS_ROLE C ON B.ROLE_ID=C.ROLE_ID\n");
        sbsql.append("GROUP BY A.USER_ID\n");
        sbsql.append(")C ON A.USER_ID=C.USER_ID");
        sbsql.append("\n");
        sbsql.append("    WHERE 1=1 ");

        List list = new ArrayList();
        if (StringUtil.isNotEmpty(yhxm)) {
            sbsql.append("  AND A.REALNAME LIKE ? ");
            list.add('%' + yhxm + '%');
        }

        if (StringUtil.isNotEmpty(dlzh)) {
            sbsql.append("  AND A.LOGINNAME LIKE ? ");
            list.add('%' + dlzh + '%');
        }

        if (bhxj.equals("1")) {
            sbsql.append("  AND A.SSBMBH IN(SELECT BMBH FROM SYS_DEPARTMENT  START WITH BMBH =?  CONNECT BY PRIOR ID = PARENTID)");
            list.add(jgbh);
            // select bmbh from sys_department  start with bmbh ='360000000001' connect by prior id = parentid
        } else {
            sbsql.append("  AND A.SSBMBH=? ");
            list.add(jgbh);
        }

        sbsql.append("  ORDER BY A.USER_ID DESC ");
        sbsql.append("  )    ");

        sbsql.append("  SELECT USER_ID, SSBMBH, LOGINNAME, REALNAME, SFZHM, MMYXQ, LXDH, \n" +
                "KQIPXZ, IPADDRESS1, IPADDRESS2,IPADDRESS3, ZT, LXDZ,BZ, CZSJ,CZRYZH,PASSWORD, LASTTIME, \n" +
                "PREIPADDRESS, SFMJ, YGBH, YHYXQ, YXQZT, SBCS,SBSJ,\n" +
                "SSBMNAME,ROLES,ROLESNAME,RN \n");
        sbsql.append("  FROM DEPT    ");
        sbsql.append(" WHERE RN BETWEEN (? - 1) * ? + 1 AND ? * ? \n");

        list.add(pageindex);
        list.add(pagesize);
        list.add(pageindex);
        list.add(pagesize);

        sbsql.append("UNION ALL\n");

        sbsql.append("  SELECT '' USER_ID,NULL SSBMBH,NULL LOGINNAME,NULL REALNAME,NULL SFZHM," +
                "NULL MMYXQ,NULL LXDH, \n" +
                " NULL KQIPXZ,NULL  IPADDRESS1,NULL  IPADDRESS2,NULL IPADDRESS3,NULL  ZT,NULL  LXDZ," +
                "NULL BZ,NULL  CZSJ,NULL CZRYZH,NULL PASSWORD,NULL  LASTTIME, \n" +
                "NULL PREIPADDRESS,NULL  SFMJ,NULL  YGBH,NULL  YHYXQ,NULL  YXQZT,NULL  SBCS,NULL SBSJ,\n" +
                "NULL SSBMNAME,NULL ROLES,NULL ROLESNAME,COUNT(1) RN \n");
        sbsql.append("  FROM DEPT    ");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();//链接业务库
            ps = conn.prepareStatement(sbsql.toString());
            int cnt = 1;
            for (Object item : list) {
                ps.setObject(cnt, item);
                cnt = cnt + 1;
            }

            rs = ps.executeQuery();
            return readDepts(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Role> getRolesByBmbh(String bmbh) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Role> list = new ArrayList<>();
            conn = JdbcUtil.getConnection();

            int type = 0;
            int levelnum = 0;
            // 获取部门信息
            StringBuilder sbSql = new StringBuilder("");
            sbSql.append(" SELECT TYPE,LEVELNUM FROM SYS_DEPARTMENT WHERE BMBH=? ");
            ps = conn.prepareStatement(sbSql.toString());
            ps.setObject(1, bmbh);
            rs = ps.executeQuery();//
            while (rs.next()) {
                type = rs.getInt("TYPE");
                levelnum = rs.getInt("LEVELNUM");
            }

            //判断是否为直属部门，若是 则 只根据levelnum查询
            //否则 根据 type 和 levelnum 查询；
            List objList = new ArrayList();
            sbSql = new StringBuilder("");
            sbSql.append(" SELECT  ROLE_ID,NAME  FROM SYS_ROLE WHERE STATE=1 ");
            sbSql.append(" AND  LEVELNUM=? ");
            objList.add(levelnum);
            if (type == 0) {
            } else {
                sbSql.append(" AND  TYPE=? ");
                objList.add(type);
            }
            sbSql.append(" UNION SELECT  ROLE_ID,NAME  FROM SYS_ROLE WHERE STATE=1 ");
            sbSql.append(" AND  LEVELNUM IN(SELECT DISTINCT LEVELNUM FROM SYS_DEPARTMENT  START WITH BMBH =? CONNECT BY PRIOR ID = PARENTID )");
            objList.add(bmbh);
            if (type == 0) {
            } else {
                sbSql.append(" AND  TYPE IN(SELECT DISTINCT TYPE FROM SYS_DEPARTMENT  START WITH BMBH =? CONNECT BY PRIOR ID = PARENTID ) ");
                objList.add(bmbh);
            }

            ps = conn.prepareStatement(sbSql.toString());

            int cnt = 1;
            for (Object item : objList) {
                ps.setObject(cnt, item);
                cnt = cnt + 1;
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Role RoleTmp = new Role();
                RoleTmp.setRole_id(rs.getInt("ROLE_ID"));
                RoleTmp.setName(StringEx.nullToStr(rs.getString("NAME")));
                list.add(RoleTmp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Map> getSysUserRoles(String type) throws Exception {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from sys_role where type = ?\n");

        List<Map> roleList = new ArrayList<>();

        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sqlBuilder.toString());
            ps.setString(1, type);
            rs = ps.executeQuery();
            Map role = null;

            while (rs.next()) {

                role = new HashMap();
                role.put("roleId", rs.getInt("ROLE_ID"));
                role.put("name", StringEx.nullToStr(rs.getString("NAME")));
                role.put("type", rs.getInt("TYPE"));

                roleList.add(role);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return roleList;
    }
    @Override
    public Map getSysUserRolesByID(String roleId) throws Exception {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from sys_role where role_id = ?\n");

        Map roleList = new HashMap();

        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sqlBuilder.toString());
            ps.setString(1, roleId);
            rs = ps.executeQuery();

            while (rs.next()) {

                roleList.put("roleId", rs.getInt("ROLE_ID"));
                roleList.put("name", StringEx.nullToStr(rs.getString("NAME")));
                roleList.put("type", rs.getInt("TYPE"));

            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return roleList;
    }

    /**
     * 功能描述：根据用户名获取用户权限列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @param loginname
     * @return
     */
    @Override
    public List<Role> getUserRoles(String loginname) throws Exception {

        if ("".equals(StringEx.nullToStr(loginname))) {
            throw new Exception("根据用户名查询角色列表，用户名不能为空！");
        }

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sbSql = null;

        if (!"admin".equals(loginname)) {

            sbSql = new StringBuilder("");

            sbSql.append("SELECT A.ROLE_ID, A.NAME, A.LEVELNUM, A.TYPE, A.STATE, A.CJBMBH");
            sbSql.append("  FROM SYS_ROLE A, SYS_USER B, SYS_USER_ROLE C, SYS_USER D");
            sbSql.append(" WHERE A.ROLE_ID = C.ROLE_ID");
            sbSql.append("   AND B.USER_ID = C.USER_ID");
            sbSql.append("   AND D.USER_ID = C.USER_ID");
            sbSql.append("   AND A.STATE = 1");
            sbSql.append("   AND D.ZT = 1");
            sbSql.append("   AND D.LOGINNAME = ? ");

        } else {
            sbSql = new StringBuilder("");
            sbSql.append("SELECT A.ROLE_ID, A.NAME, A.LEVELNUM, A.TYPE, A.STATE, A.CJBMBH");
            sbSql.append("     FROM SYS_ROLE A");
            sbSql.append("    WHERE A.STATE = 1");
        }

        List<Role> roleList = new ArrayList<>();

        try {

            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sbSql.toString());

            if (!"admin".equals(loginname)) {
                ps.setString(1, loginname);
            }

            rs = ps.executeQuery();

            Role role = null;

            while (rs.next()) {

                role = this.getRoleClone();
                role.setRole_id(rs.getInt("ROLE_ID"));
                role.setName(StringEx.nullToStr(rs.getString("NAME")));
                role.setCjbmbh(StringEx.nullToStr(rs.getString("CJBMBH")));
                role.setLevelnum(rs.getInt("LEVELNUM"));
                role.setType(rs.getInt("TYPE"));

                roleList.add(role);
            }

        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return roleList;
    }

    /**
     * 功能描述：根据用户名获取用户菜单列表
     * 作者：齐遥遥
     * 时间：2017-10-17
     *
     * @param loginname
     * @return
     */
    @Override
    public List<MenuBean> getUserMenus(String loginname) throws Exception {

        if ("".equals(StringEx.nullToStr(loginname))) {
            throw new Exception("根据用户名查询用户菜单列表，用户名不能为空！");
        }

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sbSql = null;

        if (!"admin".equals(loginname)) {

            sbSql = new StringBuilder("");

            sbSql.append("SELECT DISTINCT A.MENU_ID,");
            sbSql.append(" A.PARENTID,");
            sbSql.append(" A.NAVIGATEURL,");
            sbSql.append(" A.NAME,");
            sbSql.append(" A.MID,");
            sbSql.append(" A.SORTCODE,");
            sbSql.append(" A.ICO,");
            sbSql.append(" A.TARGET,");
            sbSql.append(" A.LEVELNUM");
            sbSql.append(" FROM SYS_MENU A, SYS_MENU_ROLE B, SYS_USER_ROLE C, SYS_USER D");
            sbSql.append(" WHERE A.MENU_ID = B.MENU_ID");
            sbSql.append(" AND B.ROLE_ID = C.ROLE_ID");
            sbSql.append(" AND C.USER_ID = D.USER_ID");
            sbSql.append(" AND A.STATE = 1");
            sbSql.append(" AND D.ZT = 1");
            sbSql.append(" AND D.LOGINNAME = ? ");
            sbSql.append(" ORDER BY A.SORTCODE ASC");

        } else {

            sbSql = new StringBuilder("");

            sbSql.append("SELECT A.MENU_ID,");
            sbSql.append(" A.PARENTID,");
            sbSql.append(" A.NAVIGATEURL,");
            sbSql.append(" A.NAME,");
            sbSql.append(" A.MID,");
            sbSql.append(" A.SORTCODE,");
            sbSql.append(" A.ICO,");
            sbSql.append(" A.TARGET,");
            sbSql.append(" A.LEVELNUM");
            sbSql.append(" FROM SYS_MENU A");

            sbSql.append(" WHERE A.STATE = 1");
            sbSql.append(" ORDER BY A.SORTCODE ASC");
        }

        List<MenuBean> menuBeanList = new ArrayList<>();

        try {

            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sbSql.toString());

            if (!"admin".equals(loginname)) {
                ps.setString(1, loginname);
            }

            rs = ps.executeQuery();

            MenuBean menuBean = null;

            while (rs.next()) {

                menuBean = this.getMenueClone();
                menuBean.setId(StringEx.nullToZero(rs.getString("MENU_ID")));
                menuBean.setpId(StringEx.nullToStr(rs.getString("PARENTID")));
                menuBean.setName(StringEx.nullToStr(rs.getString("NAME")));
                menuBean.setDoPath(StringEx.nullToStr(rs.getString("NAVIGATEURL")));
                menuBean.setIcon(StringEx.nullToStr(rs.getString("ICO")));
                menuBean.setMid(rs.getInt("MID"));
                menuBean.setType(StringEx.nullToStr(rs.getString("TARGET")));
                menuBean.setTarget(StringEx.nullToStr(rs.getString("TARGET")));
                menuBean.setLevel(rs.getInt("LEVELNUM"));

                menuBeanList.add(menuBean);
            }

        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return menuBeanList;
    }

    @Override
    public User getUserByLoginname(String loginname) throws Exception {

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("SELECT A.*,\n");
        sbSql.append("       B.NAME AS SSBMNAME,\n");
        sbSql.append("       DBMS_LOB.SUBSTR(C.ROLES, 4000) ROLES,\n");
        sbSql.append("       C.ROLESNAME,\n");
        sbSql.append("       b.id deptid\n");
        sbSql.append("  FROM SYS_USER A\n");
        sbSql.append("  LEFT JOIN SYS_DEPARTMENT B\n");
        sbSql.append("    ON A.SSBMBH = B.BMBH\n");
        sbSql.append("  LEFT JOIN (SELECT A.USER_ID,\n");
        sbSql.append(            "        WM_CONCAT(TO_CHAR(C.ROLE_ID)) AS ROLES,\n");
        sbSql.append(            "        '' AS ROLESNAME\n");
        sbSql.append(            "   FROM SYS_USER A\n");
        sbSql.append(            "   JOIN SYS_USER_ROLE B\n");
        sbSql.append(            "     ON A.USER_ID = B.USER_ID\n");
        sbSql.append(            "   JOIN SYS_ROLE C\n");
        sbSql.append(            "     ON B.ROLE_ID = C.ROLE_ID\n");
        sbSql.append(            "  GROUP BY A.USER_ID) C\n");
        sbSql.append("    ON A.USER_ID = C.USER_ID\n");
        sbSql.append(" WHERE A.LOGINNAME = ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setString(1, loginname);
            rs = ps.executeQuery();
            if (rs.next()) {
                User userTmp = new User();
                userTmp.setUser_id(rs.getString("USER_ID"));
                userTmp.setBz(StringEx.nullToStr(rs.getString("BZ")));
                userTmp.setCzryzh(StringEx.nullToStr(rs.getString("CZRYZH")));
                userTmp.setCzsj(StringEx.nullToStr(rs.getString("CZSJ")));
                userTmp.setIpaddress1(StringEx.nullToStr(rs.getString("IPADDRESS1")));

                userTmp.setIpaddress2(StringEx.nullToStr(rs.getString("IPADDRESS2")));
                userTmp.setIpaddress3(StringEx.nullToStr(rs.getString("IPADDRESS3")));
                userTmp.setKqipxz(rs.getInt("KQIPXZ"));
                userTmp.setLasttime(StringEx.nullToStr(rs.getString("LASTTIME")));
                userTmp.setLoginname(StringEx.nullToStr(rs.getString("LOGINNAME")));

                userTmp.setLxdh(StringEx.nullToStr(rs.getString("LXDH")));
                userTmp.setLxdz(StringEx.nullToStr(rs.getString("LXDZ")));
                userTmp.setMmyxq(StringEx.nullToStr(rs.getString("MMYXQ")));
//                userTmp.setPassword(StringEx.nullToStr(rs.getString("PASSWORD")));
                userTmp.setPreipaddress(StringEx.nullToStr(rs.getString("PREIPADDRESS")));

                userTmp.setRealname(StringEx.nullToStr(rs.getString("REALNAME")));
                userTmp.setSbcs(rs.getInt("SBCS"));
                userTmp.setSbsj(StringEx.nullToStr(rs.getString("SBSJ")));
                userTmp.setSfmj(StringEx.nullToStr(rs.getString("SFMJ")));
//                userTmp.setSfzhm(StringEx.nullToStr(rs.getString("SFZHM")));

                userTmp.setSsbmbh(StringEx.nullToStr(rs.getString("SSBMbh")));
                userTmp.setYgbh(StringEx.nullToStr(rs.getString("YgBH")));
                userTmp.setYhyxq(StringEx.nullToStr(rs.getString("YHYXQ")));
                userTmp.setYxqzt(StringEx.nullToStr(rs.getString("YXQZT")));
                userTmp.setZt(rs.getInt("ZT"));
                userTmp.setRoles(StringEx.nullToStr(rs.getString("ROLES")));

                userTmp.setRolesname(StringEx.nullToStr(rs.getString("ROLESNAME")));
                userTmp.setSsbmname(StringEx.nullToStr(rs.getString("SSBMNAME")));
                userTmp.setDeptId(Integer.valueOf(StringEx.nullToStr(rs.getString("DEPTID"))));
                return userTmp;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    /**
     * 获取当前登录用户信息
     *
     * @param user_id
     * @return
     * @throws Exception
     */
    @Override
    public String getUserInfo(String user_id) throws Exception {
        String sfzhm = "";
        String sql = "SELECT SFZHM FROM SYS_USER WHERE USER_ID=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                sfzhm = rs.getString(1);
            }

            return sfzhm;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
    }

    @Override
    public List<Map<String, Object>> getUserSj() throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from sys_user_safeauditset\n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            rs = ps.executeQuery();
            return QueryUtil.listMap(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Map<String, String> getUserSj2(String username) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ctime\n");
        sqlBuilder.append("  from (select *\n");
        sqlBuilder.append("          from SYS_OP_LOG\n");
        sqlBuilder.append("         where uname = ?\n");
        sqlBuilder.append("           and log_type = 1\n");
        sqlBuilder.append("           and op_type = 1\n");
        sqlBuilder.append("           and op_module = 1\n");
        sqlBuilder.append("           and op_result = 1\n");
        sqlBuilder.append("           and func_type = 1\n");
        sqlBuilder.append("         order by ctime desc) t\n");
        sqlBuilder.append(" where rownum = 1\n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            ps.setObject(1, username);

            rs = ps.executeQuery();
            return QueryUtil.MapFieldValue(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 修改密码
     *
     * @param loginname
     * @param password
     * @param newPassword
     * @return
     * @throws Exception
     */
    @Override
    public Response updatePassword(String loginname, String password, String newPassword) throws Exception {

        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("SELECT LOGINNAME,PASSWORD FROM SYS_USER");
        sbSql.append(" WHERE LOGINNAME = ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setString(1, loginname);

            rs = ps.executeQuery();
            String dbPwd = "";
            while (rs.next()) {
                dbPwd = StringEx.nullToStr(rs.getString("PASSWORD"));
            }
            if (dbPwd == "") {
                return new Response().failure("用户名不存在");
            }
            DESUtil desUtil = new DESUtil(PRIVATE_KEY);
            if (!password.equals(desUtil.decrypt(dbPwd))) {
                return new Response().failure("原密码错误");
            }
            newPassword = desUtil.encrypt(newPassword);

            StringBuilder sbSqlstr = new StringBuilder("");
            sbSqlstr.append(" UPDATE SYS_USER SET PASSWORD = ? ");
            sbSqlstr.append(" WHERE LOGINNAME = ? ");

            ps = conn.prepareStatement(sbSqlstr.toString());
            ps.setString(1, newPassword);
            ps.setString(2, loginname);

//            boolean flat = ps.execute();
            int temp = ps.executeUpdate();
            if (temp >= 1) {
                return new Response().success("密码修改成功");
            }
            return new Response().failure("密码修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    
}
