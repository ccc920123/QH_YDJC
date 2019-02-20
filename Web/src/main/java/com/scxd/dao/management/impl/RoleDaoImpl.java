package com.scxd.dao.management.impl;

import com.scxd.beans.common.Role;
import com.scxd.beans.management.MenuRoleBean;
import com.scxd.common.StringEx;
import com.scxd.dao.management.ifaces.RoleDao;
import com.scxd.dao.util.QueryUtil;
import com.scxd.db.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    /**
     * 角色数据访问层
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> getRole(String pageNo, String pageSize, String NAME, String TYPE, String LEVELNUM) throws Exception {

        List<Role> roleList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sbSql = new StringBuilder("");

        sbSql.append("WITH ROLE AS");
        sbSql.append(" (SELECT A.ROLE_ID,");
        sbSql.append("         A.NAME,");
        sbSql.append("         A.LEVELNUM,");
        sbSql.append("         A.TYPE,");
        sbSql.append("         A.STATE,");
        sbSql.append("         A.CZRYZH,");
        sbSql.append("         TO_CHAR(A.CZSJ, 'YYYY-MM-DD HH24:MI:SS') CZSJ,");
        sbSql.append("         A.CJBMBH");
        sbSql.append("    FROM SYS_ROLE A");
        sbSql.append("   WHERE 1 = 1");

        if (!(NAME == null || "".equals(NAME))) {
            sbSql.append("  AND A.NAME LIKE '%").append(NAME).append("%' ");
        }

        if (!(TYPE == null || "".equals(TYPE))) {
            sbSql.append("  AND A.TYPE LIKE '%").append(TYPE).append("%' ");
        }
        if (!(LEVELNUM == null || "".equals(LEVELNUM))) {
            sbSql.append("  AND A.LEVELNUM LIKE '%").append(LEVELNUM).append("%' ");
        }


        sbSql.append(" ORDER BY CZSJ DESC),");
        sbSql.append("ROLE2 AS");
        sbSql.append(" (SELECT ROLE.ROLE_ID,");
        sbSql.append("         ROLE.NAME,");
        sbSql.append("         ROLE.LEVELNUM,");
        sbSql.append("         ROLE.TYPE,");
        sbSql.append("         ROLE.STATE,");
        sbSql.append("         ROLE.CZRYZH,");
        sbSql.append("         ROLE.CZSJ,");
        sbSql.append("         ROLE.CJBMBH,");
        sbSql.append("         ROWNUM RN");
        sbSql.append("    FROM ROLE),");
        sbSql.append("ROLE3 AS");
        sbSql.append(" (SELECT COUNT(1) TOTAL FROM ROLE)");
        sbSql.append("SELECT *");
        sbSql.append("  FROM ROLE2");
        sbSql.append("  LEFT JOIN ROLE3");
        sbSql.append("    ON 1 = 1");
        sbSql.append(" WHERE RN BETWEEN (? -1) * ? +1 AND ? * ?");

//        sbSql.append("  order by ROLE_ID desc ");
//        sbSql.append("  )aa    ");
//        sbSql.append(" WHERE RN BETWEEN (? - 1) * ? + 1 AND ? * ? \n");


        try {
            connection = JdbcUtil.getConnection();
//            ps = connection.prepareStatement(sbSql.toString());
//            ps.setString(1, NAME);
//            ps.setString(2, TYPE);
//            ps.setString(3, LEVELNUM);

            ps = connection.prepareStatement(sbSql.toString());

            ps.setObject(1, pageNo);
            ps.setObject(2, pageSize);
            ps.setObject(3, pageNo);
            ps.setObject(4, pageSize);


            rs = ps.executeQuery();
            while (rs.next()) {
                Role RoleTmp = new Role();
                RoleTmp.setRole_id(rs.getInt("ROLE_ID"));
                RoleTmp.setName(StringEx.nullToStr(rs.getString("NAME")));
                RoleTmp.setLevelnum(rs.getInt("LEVELNUM"));
                RoleTmp.setType(rs.getInt("TYPE"));
                RoleTmp.setState(rs.getInt("STATE"));
                RoleTmp.setCzryzh(StringEx.nullToStr(rs.getString("CZRYZH")));
                RoleTmp.setCzsj(rs.getString("CZSJ"));
                RoleTmp.setCjbmbh(StringEx.nullToStr(rs.getString("CJBMBH")));
                RoleTmp.setTotal(rs.getInt("TOTAL"));
                RoleTmp.setRn(rs.getInt("RN"));

                roleList.add(RoleTmp);
            }

        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return roleList;
    }

    @Override
    public List<List> getRoleList(int pageNo, int pageSize) throws Exception {
        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("WITH MENU_ROLE AS");
        sbSql.append(" (SELECT AA.ROLE_ID,");
        sbSql.append("         AA.NAME,");
        sbSql.append("         AA.LEVELNUM,");
        sbSql.append("         AA.TYPE,");
        sbSql.append("         AA.STATE,");
        sbSql.append("         AA.CZRYZH,");
        sbSql.append("         AA.CZSJ,");
        sbSql.append("         AA.CJBMBH,");
        sbSql.append("         ROWNUM RN");
        sbSql.append("    FROM (SELECT A.ROLE_ID,");
        sbSql.append("                 A.NAME,");
        sbSql.append("                 A.LEVELNUM,");
        sbSql.append("                 A.TYPE,");
        sbSql.append("                 A.STATE,");
        sbSql.append("                 A.CZRYZH,");
        sbSql.append("                 A.CZSJ,");
        sbSql.append("                 A.CJBMBH");
        sbSql.append("            FROM SYS_ROLE A");
        sbSql.append("           ORDER BY CZSJ DESC) AA),");
        sbSql.append("MENU_ROLE2 AS");
        sbSql.append(" (SELECT COUNT(1) FROM MENU_ROLE)");
        sbSql.append("SELECT *");
        sbSql.append("  FROM MENU_ROLE");
        sbSql.append("  LEFT JOIN MENU_ROLE2");
        sbSql.append("    ON 1 = 1");
        sbSql.append(" WHERE RN BETWEEN (? - 1 )* ? + 1 AND ? * ? ");
        return QueryUtil.table(sbSql.toString(), pageNo, pageSize, pageNo, pageSize);

    }

    public boolean addRole(Role role) throws Exception {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("INSERT INTO SYS_ROLE");
        sbSql.append("  (ROLE_ID, NAME, LEVELNUM, TYPE, STATE, CZRYZH,CZSJ,CJBMBH)");
        sbSql.append("VALUES");
        sbSql.append("?,?,?,?,?,?,?,?");

        try {

            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setInt(1, role.getRole_id());
            ps.setString(2, role.getName());
            ps.setInt(3, role.getLevelnum());
            ps.setInt(4, role.getType());
            ps.setInt(5, role.getState());
            ps.setString(6, role.getCzryzh());
            ps.setString(7, role.getCzsj());
            ps.setString(8, role.getCjbmbh());

            ps.executeUpdate();
            flag = true;
        } finally {
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
        return flag;
    }

    public static Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(d.getTime());
        return date;
    }

    public Role getRoleById(int Role_id) throws Exception {
        Role role = new Role();

        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("SELECT ROLE_ID, NAME, LEVELNUM, TYPE, STATE, CZRYZH,CZSJ,CJBMBH");
        sbSql.append("  FROM SYS_ROLE A");
        sbSql.append(" WHERE A.ROLE_ID = ?");

        Role RoleTmp = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            RoleTmp = new Role();
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setInt(1, Role_id);

            rs = ps.executeQuery();

            while (rs.next()) {
                RoleTmp.setRole_id(rs.getInt("ROLE_ID"));
                RoleTmp.setName(StringEx.nullToStr(rs.getString("NAME")));
                RoleTmp.setLevelnum(rs.getInt("LEVELNUM"));
                RoleTmp.setType(rs.getInt("TYPE"));
                RoleTmp.setState(rs.getInt("STATE"));
                RoleTmp.setCzryzh(StringEx.nullToStr(rs.getString("CZRYZH")));
                RoleTmp.setCzsj(rs.getString("CZSJ"));
                RoleTmp.setCjbmbh(StringEx.nullToStr(rs.getString("CJBMBH")));
            }
        } finally {
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
        return RoleTmp;
    }


    public boolean updateRoleById(Role role) throws Exception {

        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("UPDATE SYS_ROLE ");
        sbSql.append(" SET NAME= ?, LEVELNUM= ?, TYPE= ?, STATE= ?, CZRYZH= ?,CZSJ= ?,CJBMBH= ? ");
        sbSql.append(" WHERE ROLE_ID= ? ");

        try {

            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setString(1, role.getName());
            ps.setInt(2, role.getLevelnum());
            ps.setInt(3, role.getType());
            ps.setInt(4, role.getState());
            ps.setString(5, role.getCzryzh());
            ps.setString(6, role.getCzsj());
            ps.setString(7, role.getCjbmbh());
            ps.setInt(8, role.getRole_id());

            ps.executeUpdate();
            flag = true;
        } finally {
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
        return flag;
    }


    public boolean deleteRole(int Role_id) throws Exception {

        boolean flag = false;

        PreparedStatement ps = null;
        Connection conn = null;

        int result = -1;
        StringBuilder sbSql = new StringBuilder("DELETE FROM SYS_ROle A WHERE A.ROLE_ID = ? ");
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sbSql.toString());
            ps.setInt(1, Role_id);

            result = ps.executeUpdate();

            if (result > 0) {
                flag = true;
            }
        } finally {
            JdbcUtil.releaseConnection(null, ps, null);
        }
        return flag;
    }


    public void deleteRoleMenu(int Role_id) throws Exception {

        int count=-1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = "SELECT COUNT(*) FROM SYS_USER_ROLE WHERE ROLE_ID = ? ";
        String sql1 = "DELETE FROM SYS_ROLE WHERE ROLE_ID = ? ";
        String sql2 = "DELETE FROM SYS_MENU_ROLE WHERE ROLE_ID = ? ";
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps=conn.prepareStatement(sql);
            ps.setInt(1,Role_id);
            rs=ps.executeQuery();
            while (rs.next()) {
                 count=rs.getInt(1);
                if (count > 0) {
                    throw new Exception("不能删除：该角色与"+count+"个用户有关联");
                }
            }
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, Role_id);
            ps.execute();
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, Role_id);
            ps.execute();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
    }

    @Override
    public boolean addMenuRole(int[] id, Role role) throws Exception {
        int count=-1;
        boolean flag = true;
        long roleId = JdbcUtil.getQxXh();
        String sql = "INSERT INTO SYS_ROLE\n" +
                "  (ROLE_ID, NAME, LEVELNUM, TYPE, STATE, CZRYZH, CZSJ, CJBMBH)\n" +
                "VALUES\n" +
                "  (?,?,?,?,?,?,SYSDATE,?)";
        String sql2="SELECT COUNT(1) FROM SYS_ROLE WHERE NAME = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs=null;
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            ps=conn.prepareStatement(sql2);
            ps.setString(1,role.getName());
            rs=ps.executeQuery();
            while (rs.next()) {
                count=rs.getInt(1);
                if (count > 0) {
                    flag=false;
                    throw new Exception("-1");//返回-1表示添加的角色名重复
                }
            }
            st = conn.createStatement();
            for (int aid : id) {
                String values = "(" + aid + "," + roleId + ",SYSDATE," + "\'" + role.getCzryzh() + "\'" + ")";
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" INSERT INTO SYS_MENU_ROLE ");
                sqlBuilder.append("  (MENU_ID, ROLE_ID, CREATETIME, CREATER) ");
                sqlBuilder.append("VALUES ");
                sqlBuilder.append(values);
                st.addBatch(sqlBuilder.toString());
            }
            st.executeBatch();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, roleId);
            ps.setString(2, role.getName());
            ps.setInt(3, role.getLevelnum());
            ps.setInt(4, role.getType());
            ps.setInt(5, role.getState());
            ps.setString(6, role.getCzryzh());
            ps.setString(7, role.getCjbmbh());
            ps.execute();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            flag = false;
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
        return flag;
    }

    @Override
    public List<List> modifyRole(int roleId) throws Exception {
        String sql = "SELECT MENU_ID, ROLE_ID FROM SYS_MENU_ROLE WHERE ROLE_ID = ? ";
        return QueryUtil.table(sql, roleId);
    }

    /**
     * 查询要修改的用户信息
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public List<List> menuRoleList(int roleId) throws Exception {
        String sql = "SELECT * FROM SYS_MENU_ROLE WHERE ROLE_ID = ? ";
        return QueryUtil.table(sql, roleId);
    }

    /**
     * 返回 1成功  0失败  -1用户名与数据库不合
     *
     * @param menuRoleBean
     * @return
     * @throws Exception
     */
    @Override
    public int modifyMenuRole(MenuRoleBean menuRoleBean) throws Exception {
        int[] id = menuRoleBean.getId();
        Role role = menuRoleBean.getRole();
        int flag = 100;
            String sql = "INSERT INTO SYS_ROLE\n" +
                    "  (ROLE_ID, NAME, LEVELNUM, TYPE, STATE, CZRYZH, CZSJ, CJBMBH)\n" +
                    "VALUES\n" +
                    "  (?,?,?,?,?,?,SYSDATE,?)";
            String sql2 = "DELETE SYS_MENU_ROLE WHERE ROLE_ID = ? ";
            String sql3 = "DELETE FROM SYS_ROLE WHERE ROLE_ID = ? ";
            Connection conn = null;
            PreparedStatement ps = null;
            Statement st = null;
            try {
                conn = JdbcUtil.getConnection();
                conn.setAutoCommit(false);
                st = conn.createStatement();
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, role.getRole_id());
                //ps.setString(2, role.getCzryzh());
                ps.execute();
                ps.close();
                ps = conn.prepareStatement(sql3);
                ps.setInt(1, role.getRole_id());
                ps.execute();
                for (int aid : id) {
                    String values = "(" + aid + "," + role.getRole_id() + ",SYSDATE," + "\'" + role.getCzryzh() + "\'" + ")";
                    StringBuilder sqlBuilder = new StringBuilder();
                    sqlBuilder.append(" INSERT INTO SYS_MENU_ROLE ");
                    sqlBuilder.append("  (MENU_ID, ROLE_ID, CREATETIME, CREATER) ");
                    sqlBuilder.append("VALUES ");
                    sqlBuilder.append(values);
                    st.addBatch(sqlBuilder.toString());
                }
                st.executeBatch();
                ps = conn.prepareStatement(sql);
                ps.setLong(1, role.getRole_id());
                ps.setString(2, role.getName());
                ps.setInt(3, role.getLevelnum());
                ps.setInt(4, role.getType());
                ps.setInt(5, role.getState());
                ps.setString(6, role.getCzryzh());
                ps.setString(7, role.getCjbmbh());
                ps.execute();
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
                flag = 0;
                throw e;
            } finally {
                if (st != null) {
                    st.close();
                }
                JdbcUtil.releaseConnection(conn, ps, null);
            }
            flag = 1;

        return flag;
    }

    /**
     * 查询角色下是否还有用户
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getUser(int roleId) throws Exception {
        String sql = "SELECT COUNT(*) FROM SYS_USER_ROLE WHERE ROLE_ID = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> list = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list = new ArrayList<>();
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.releaseConnection(conn, ps, rs);
        }
        return list;
    }
}

