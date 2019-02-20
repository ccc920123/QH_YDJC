package com.scxd.dao.management.impl;

import com.scxd.beans.management.Dept;
import com.scxd.common.StringEx;
import com.scxd.common.StringUtil;
import com.scxd.dao.management.ifaces.DeptDao;
import com.scxd.dao.util.QueryUtil;
import com.scxd.db.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/12
 */
@Repository
public class DeptDaoImpl implements DeptDao {

    /**
     * 插入、更新
     *
     * @param dept
     * @throws Exception
     */
    @Override
    public void merge(Dept dept) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("MERGE INTO SYS_DEPARTMENT DEPT\n");
        sqlBuilder.append("USING (SELECT ? PARENTID,\n");
        sqlBuilder.append("              ? BMBH,\n");
        sqlBuilder.append("              ? SJZDBMBH,\n");
        sqlBuilder.append("              ? LEVELNUM,\n");
        sqlBuilder.append("              ? TYPE,\n");
        sqlBuilder.append("              ? NAME,\n");
        sqlBuilder.append("              ? FAX,\n");
        sqlBuilder.append("              ? LXR,\n");
        sqlBuilder.append("              ? LXRDH,\n");
        sqlBuilder.append("              ? LXDZ,\n");
        sqlBuilder.append("              ? ZT,\n");
        sqlBuilder.append("              ? BZ,\n");
        sqlBuilder.append("              ? CZRYZH,\n");
        sqlBuilder.append("              SYSDATE CZSJ,\n");
        sqlBuilder.append("              ? FZJG,\n");
        sqlBuilder.append("              ? SFZSBM,\n");
        sqlBuilder.append("              ? ZSBMBHS,\n");
        sqlBuilder.append("              ? CODE,\n");
        sqlBuilder.append("              ? XZQH\n");
        sqlBuilder.append("         FROM DUAL) A\n");
        sqlBuilder.append("ON (DEPT.BMBH =A.BMBH)\n");
        sqlBuilder.append("WHEN MATCHED THEN\n");
        sqlBuilder.append("  UPDATE\n");
        sqlBuilder.append("     SET PARENTID = A.PARENTID,\n");
        sqlBuilder.append("         SJZDBMBH = A.SJZDBMBH,\n");
        sqlBuilder.append("         LEVELNUM = A.LEVELNUM,\n");
        sqlBuilder.append("         TYPE     = A.TYPE,\n");
        sqlBuilder.append("         NAME     = A.NAME,\n");
        sqlBuilder.append("         FAX      = A.FAX,\n");
        sqlBuilder.append("         LXR      = A.LXR,\n");
        sqlBuilder.append("         LXRDH    = A.LXRDH,\n");
        sqlBuilder.append("         LXDZ     = A.LXDZ,\n");
        sqlBuilder.append("         ZT       = A.ZT,\n");
        sqlBuilder.append("         BZ       = A.BZ,\n");
        sqlBuilder.append("         CZRYZH   = A.CZRYZH,\n");
        sqlBuilder.append("         CZSJ     = A.CZSJ,\n");
        sqlBuilder.append("         FZJG     = A.FZJG,\n");
        sqlBuilder.append("         SFZSBM   = A.SFZSBM,\n");
        sqlBuilder.append("         ZSBMBHS  = A.ZSBMBHS,\n");
        sqlBuilder.append("         CODE     = A.CODE,\n");
        sqlBuilder.append("         XZQH     = A.XZQH\n");
        sqlBuilder.append("WHEN NOT MATCHED THEN\n");
        sqlBuilder.append("  INSERT\n");
        sqlBuilder.append("    (ID,\n");
        sqlBuilder.append("     PARENTID,\n");
        sqlBuilder.append("     BMBH,\n");
        sqlBuilder.append("     SJZDBMBH,\n");
        sqlBuilder.append("     LEVELNUM,\n");
        sqlBuilder.append("     TYPE,\n");
        sqlBuilder.append("     NAME,\n");
        sqlBuilder.append("     FAX,\n");
        sqlBuilder.append("     LXR,\n");
        sqlBuilder.append("     LXRDH,\n");
        sqlBuilder.append("     LXDZ,\n");
        sqlBuilder.append("     ZT,\n");
        sqlBuilder.append("     BZ,\n");
        sqlBuilder.append("     CZRYZH,\n");
        sqlBuilder.append("     CZSJ,\n");
        sqlBuilder.append("     FZJG,\n");
        sqlBuilder.append("     SFZSBM,\n");
        sqlBuilder.append("     ZSBMBHS,\n");
        sqlBuilder.append("     CODE,\n");
        sqlBuilder.append("     XZQH)\n");
        sqlBuilder.append("  VALUES\n");
        sqlBuilder.append("    (SEQ_SYS_DEPARTMENT.NEXTVAL,\n");
        sqlBuilder.append("     A.PARENTID,\n");
        sqlBuilder.append("     A.BMBH,\n");
        sqlBuilder.append("     A.SJZDBMBH,\n");
        sqlBuilder.append("     A.LEVELNUM,\n");
        sqlBuilder.append("     A.TYPE,\n");
        sqlBuilder.append("     A.NAME,\n");
        sqlBuilder.append("     A.FAX,\n");
        sqlBuilder.append("     A.LXR,\n");
        sqlBuilder.append("     A.LXRDH,\n");
        sqlBuilder.append("     A.LXDZ,\n");
        sqlBuilder.append("     A.ZT,\n");
        sqlBuilder.append("     A.BZ,\n");
        sqlBuilder.append("     A.CZRYZH,\n");
        sqlBuilder.append("     A.CZSJ,\n");
        sqlBuilder.append("     A.FZJG,\n");
        sqlBuilder.append("     A.SFZSBM,\n");
        sqlBuilder.append("     A.ZSBMBHS,\n");
        sqlBuilder.append("     A.CODE,\n");
        sqlBuilder.append("     A.XZQH)\n");

        Connection conn = null;
        PreparedStatement ps = null;

        ResultSet rs1=null;
        try {

            conn = JdbcUtil.getConnection();

            //新增先判断部门编号是否已存在
            String id=dept.getId();
            if(StringUtil.isEmpty(id))
            {
                String bmbh= dept.getBmbh();
                StringBuilder sql = new StringBuilder();
                sql.append(" SELECT COUNT(ID) TOTAL FROM SYS_DEPARTMENT WHERE BMBH=? ");
                ps = conn.prepareStatement(sql.toString());
                ps.setObject(1, StringEx.nullToStr(bmbh));
                rs1=  ps.executeQuery();
                if (rs1.next()) {
                    int count = rs1.getInt(1);
                    if (count > 0) {
                        throw new Exception("该部门编号 "+ bmbh+" 已存在");
                    }
                }
            }

            ps = conn.prepareStatement(sqlBuilder.toString());
            ps.setObject(1, StringEx.nullToZero(dept.getParentid()));         //? PARENTID,\n");
            ps.setObject(2, StringEx.nullToStr(dept.getBmbh()));         //? BMBH,\n");
            ps.setObject(3, StringEx.nullToStr(dept.getSjzdbmbh()));         //? SJZDBMBH,\n");
            ps.setObject(4, StringEx.nullToStr(dept.getLevelnum()));         //? LEVELNUM,\n");
            ps.setObject(5, StringEx.nullToStr(dept.getType()));         //? TYPE,\n");
            ps.setObject(6, StringEx.nullToStr(dept.getName()));         //? NAME,\n");
            ps.setObject(7, StringEx.nullToStr(dept.getFax()));         //? FAX,\n");
            ps.setObject(8, StringEx.nullToStr(dept.getLxr()));         //? LXR,\n");
            ps.setObject(9, StringEx.nullToStr(dept.getLxrdh()));         //? LXRDH,\n");
            ps.setObject(10, StringEx.nullToStr(dept.getLxdz()));         //? LXDZ,\n");
            ps.setObject(11, StringEx.nullToStr(dept.getZt()));         //? ZT,\n");
            ps.setObject(12, StringEx.nullToStr(dept.getBz()));         //? BZ,\n");
            ps.setObject(13, StringEx.nullToStr(dept.getCzryzh()));         //? CZRYZH,\n");
            ps.setObject(14, StringEx.nullToStr(dept.getFzjg()));         //? FZJG,\n");
            ps.setObject(15, StringEx.nullToZero(dept.getSfzsbm()));         //? SFZSBM,\n");
            ps.setObject(16, StringEx.nullToStr(dept.getZsbmbhs()));         //? ZSBMBHS,\n");
            ps.setObject(17, StringEx.nullToStr(dept.getCode()));         //? CODE,\n");
            ps.setObject(18, StringEx.nullToStr(dept.getXzqh()));         //? XZQH\n");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

            if (rs1 != null) {
                rs1.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    /**
     * 查询一组下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public List<Dept> selectNextLev(String bmbh, String searchBh, String searchName, String pageNo, String pageSize) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("--查询\n");
        sqlBuilder.append("WITH DEPT AS\n");
        sqlBuilder.append(" (SELECT ID,\n");
        sqlBuilder.append("         PARENTID,\n");
        sqlBuilder.append("         BMBH,\n");
        sqlBuilder.append("         SJZDBMBH,\n");
        sqlBuilder.append("         LEVELNUM,\n");
        sqlBuilder.append("         TYPE,\n");
        sqlBuilder.append("         NAME,\n");
        sqlBuilder.append("         FAX,\n");
        sqlBuilder.append("         LXR,\n");
        sqlBuilder.append("         LXRDH,\n");
        sqlBuilder.append("         LXDZ,\n");
        sqlBuilder.append("         ZT,\n");
        sqlBuilder.append("         BZ,\n");
        sqlBuilder.append("         CZRYZH,\n");
        sqlBuilder.append("         CZSJ,\n");
        sqlBuilder.append("         FZJG,\n");
        sqlBuilder.append("         SFZSBM,\n");
        sqlBuilder.append("         ZSBMBHS,\n");
        sqlBuilder.append("         CODE,\n");
        sqlBuilder.append("         XZQH,\n");
        sqlBuilder.append("         ROWNUM RN\n");
        sqlBuilder.append("    FROM SYS_DEPARTMENT\n");
        sqlBuilder.append("   WHERE BMBH LIKE ? \n");
        sqlBuilder.append("     AND NAME LIKE ? \n");
        sqlBuilder.append("   START WITH BMBH = ? \n");
        sqlBuilder.append("  CONNECT BY PRIOR ID = PARENTID)\n");
        sqlBuilder.append("SELECT ID,\n");
        sqlBuilder.append("       PARENTID,\n");
        sqlBuilder.append("       BMBH,\n");
        sqlBuilder.append("       SJZDBMBH,\n");
        sqlBuilder.append("       LEVELNUM,\n");
        sqlBuilder.append("       TYPE,\n");
        sqlBuilder.append("       NAME,\n");
        sqlBuilder.append("       FAX,\n");
        sqlBuilder.append("       LXR,\n");
        sqlBuilder.append("       LXRDH,\n");
        sqlBuilder.append("       LXDZ,\n");
        sqlBuilder.append("       ZT,\n");
        sqlBuilder.append("       BZ,\n");
        sqlBuilder.append("       CZRYZH,\n");
        sqlBuilder.append("       TO_CHAR(CZSJ, 'YYYY-MM-DD'),\n");
        sqlBuilder.append("       FZJG,\n");
        sqlBuilder.append("       SFZSBM,\n");
        sqlBuilder.append("       ZSBMBHS,\n");
        sqlBuilder.append("       CODE,\n");
        sqlBuilder.append("       XZQH,\n");
        sqlBuilder.append("       RN\n");
        sqlBuilder.append("  FROM DEPT\n");
        sqlBuilder.append(" WHERE RN BETWEEN (? - 1) * ? + 1 AND ? * ? \n");
        sqlBuilder.append("UNION ALL\n");
        sqlBuilder.append("SELECT 0,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       NULL,\n");
        sqlBuilder.append("       COUNT(1)\n");
        sqlBuilder.append("  FROM DEPT\n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            ps.setObject(1, "%" + StringEx.nullToStr(searchBh) + "%");
            ps.setObject(2, "%" + StringEx.nullToStr(searchName) + "%");
            ps.setObject(3, bmbh);
            ps.setObject(4, pageNo);
            ps.setObject(5, pageSize);
            ps.setObject(6, pageNo);
            ps.setObject(7, pageSize);
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

    /**
     * 查询一个
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public Dept selectOne(String bmbh, String searchBh, String searchName) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT A.ID,\n");
        sqlBuilder.append("       A.PARENTID,\n");
        sqlBuilder.append("       A.BMBH,\n");
        sqlBuilder.append("       A.SJZDBMBH,\n");
        sqlBuilder.append("       A.LEVELNUM,\n");
        sqlBuilder.append("       A.TYPE,\n");
        sqlBuilder.append("       A.NAME,\n");
        sqlBuilder.append("       A.FAX,\n");
        sqlBuilder.append("       A.LXR,\n");
        sqlBuilder.append("       A.LXRDH,\n");
        sqlBuilder.append("       A.LXDZ,\n");
        sqlBuilder.append("       A.ZT,\n");
        sqlBuilder.append("       A.BZ,\n");
        sqlBuilder.append("       A.CZRYZH,\n");
        sqlBuilder.append("       A.CZSJ,\n");
        sqlBuilder.append("       A.FZJG,\n");
        sqlBuilder.append("       A.SFZSBM,\n");
        sqlBuilder.append("       A.ZSBMBHS,\n");
        sqlBuilder.append("       A.CODE,\n");
        sqlBuilder.append("       A.XZQH,\n");
        sqlBuilder.append("       B.NAME     PRENT,\n");
        sqlBuilder.append("       ROWNUM     RN\n");
        sqlBuilder.append("  FROM SYS_DEPARTMENT A\n");
        sqlBuilder.append("  LEFT JOIN SYS_DEPARTMENT B\n");
        sqlBuilder.append("    ON A.PARENTID = B.ID\n");
        sqlBuilder.append(" WHERE A.BMBH = ? \n");
        sqlBuilder.append("   AND A.BMBH LIKE ? \n");
        sqlBuilder.append("   AND A.NAME LIKE ? \n");


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            ps.setObject(1, bmbh);
            ps.setObject(2, "%" + StringEx.nullToStr(searchBh) + "%");
            ps.setObject(3, "%" + StringEx.nullToStr(searchName) + "%");
            rs = ps.executeQuery();
            Dept dept = new Dept();
            if (rs.next()) {
                dept.setId(StringEx.nullToStr(rs.getString(1)));        //ID,
                dept.setParentid(StringEx.nullToStr(rs.getString(2)));        //PARENTID,
                dept.setBmbh(StringEx.nullToStr(rs.getString(3)));        //BMBH,
                dept.setSjzdbmbh(StringEx.nullToStr(rs.getString(4)));        //SJZDBMBH,
                dept.setLevelnum(StringEx.nullToStr(rs.getString(5)));        //LEVELNUM,
                dept.setType(StringEx.nullToStr(rs.getString(6)));        //TYPE,
                dept.setName(StringEx.nullToStr(rs.getString(7)));        //NAME,
                dept.setFax(StringEx.nullToStr(rs.getString(8)));        //FAX,
                dept.setLxr(StringEx.nullToStr(rs.getString(9)));        //LXR,
                dept.setLxrdh(StringEx.nullToStr(rs.getString(10)));        //LXRDH,
                dept.setLxdz(StringEx.nullToStr(rs.getString(11)));        //LXDZ,
                dept.setZt(StringEx.nullToStr(rs.getString(12)));        //ZT,
                dept.setBz(StringEx.nullToStr(rs.getString(13)));        //BZ,
                dept.setCzryzh(StringEx.nullToStr(rs.getString(14)));        //CZRYZH,
                dept.setCzsj(StringEx.nullToStr(rs.getString(15)));        //CZSJ,
                dept.setFzjg(StringEx.nullToStr(rs.getString(16)));        //FZJG,
                dept.setSfzsbm(StringEx.nullToStr(rs.getString(17)));        //SFZSBM,
                dept.setZsbmbhs(StringEx.nullToStr(rs.getString(18)));        //ZSBMBHS,
                dept.setCode(StringEx.nullToStr(rs.getString(19)));        //CODE,
                dept.setXzqh(StringEx.nullToStr(rs.getString(20)));        //XZQH,
                dept.setPrent(StringEx.nullToStr(rs.getString(21)));        //XZQH,
                dept.setRn(StringEx.nullToStr(rs.getString(22)));        //RN,
                return dept;
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
     * 查询全部下级dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public List<Dept> selectAll(String bmbh, String zdid) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ID,\n");
        sqlBuilder.append("       PARENTID,\n");
        sqlBuilder.append("       BMBH,\n");
        sqlBuilder.append("       SJZDBMBH,\n");
        sqlBuilder.append("       LEVELNUM,\n");
        sqlBuilder.append("       TYPE,\n");
        sqlBuilder.append("       NAME,\n");
        sqlBuilder.append("       FAX,\n");
        sqlBuilder.append("       LXR,\n");
        sqlBuilder.append("       LXRDH,\n");
        sqlBuilder.append("       LXDZ,\n");
        sqlBuilder.append("       ZT,\n");
        sqlBuilder.append("       BZ,\n");
        sqlBuilder.append("       CZRYZH,\n");
        sqlBuilder.append("       TO_CHAR(CZSJ,'YYYY-MM-DD') CZSJ,\n");
        sqlBuilder.append("       FZJG,\n");
        sqlBuilder.append("       SFZSBM,\n");
        sqlBuilder.append("       ZSBMBHS,\n");
        sqlBuilder.append("       CODE,\n");
        sqlBuilder.append("       XZQH,\n");
        sqlBuilder.append("       ROWNUM RN\n");
        sqlBuilder.append("  FROM SYS_DEPARTMENT\n");

        if ("".equals(StringEx.nullToStr(zdid))) {
            sqlBuilder.append(" START WITH BMBH = ?\n");
        } else {
            sqlBuilder.append(" START WITH id = ?\n");
            bmbh = zdid;
        }

        sqlBuilder.append("CONNECT BY PRIOR BMBH = SJZDBMBH\n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());

            ps.setObject(1, bmbh);
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


    /**
     * 查询全部下级支队dept
     *
     * @param bmbh
     * @return
     * @throws Exception
     */
    @Override
    public List<Dept> getZdList(String bmbh) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ID,\n");
        sqlBuilder.append("       PARENTID,\n");
        sqlBuilder.append("       BMBH,\n");
        sqlBuilder.append("       SJZDBMBH,\n");
        sqlBuilder.append("       LEVELNUM,\n");
        sqlBuilder.append("       TYPE,\n");
        sqlBuilder.append("       NAME,\n");
        sqlBuilder.append("       FAX,\n");
        sqlBuilder.append("       LXR,\n");
        sqlBuilder.append("       LXRDH,\n");
        sqlBuilder.append("       LXDZ,\n");
        sqlBuilder.append("       ZT,\n");
        sqlBuilder.append("       BZ,\n");
        sqlBuilder.append("       CZRYZH,\n");
        sqlBuilder.append("       TO_CHAR(CZSJ,'YYYY-MM-DD') CZSJ,\n");
        sqlBuilder.append("       FZJG,\n");
        sqlBuilder.append("       SFZSBM,\n");
        sqlBuilder.append("       ZSBMBHS,\n");
        sqlBuilder.append("       CODE,\n");
        sqlBuilder.append("       XZQH,\n");
        sqlBuilder.append("       ROWNUM RN\n");
        sqlBuilder.append("  FROM SYS_DEPARTMENT\n");
        sqlBuilder.append(" where LEVELNUM=12 and type=1 START WITH BMBH = ?\n");

        sqlBuilder.append("CONNECT BY PRIOR ID = PARENTID\n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            ps.setObject(1, bmbh);

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


    /**
     * 删除一个部门
     *
     * @param bmbh
     * @throws Exception
     */
    @Override
    public void delete(String bmbh) throws Exception {
        String sql = "DELETE FROM SYS_DEPARTMENT WHERE BMBH = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        try {
            conn = JdbcUtil.getConnection();

            //下级部门
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT COUNT(BMBH) TOTAL\n");
            sqlBuilder.append("  FROM SYS_DEPARTMENT\n");
            sqlBuilder.append(" WHERE BMBH <> ? \n");
            sqlBuilder.append(" START WITH BMBH = ? \n");
            sqlBuilder.append("CONNECT BY PRIOR ID = PARENTID\n");
            ps1 = conn.prepareStatement(sqlBuilder.toString());
            ps1.setObject(1, bmbh);
            ps1.setObject(2, bmbh);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                int count = rs1.getInt(1);
                if (count > 0) {
                    throw new Exception("不能删除部门：部门有下级部门");
                }
            }

            //下级用户
            sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT COUNT(USER_ID) TOTAL\n");
            sqlBuilder.append("  FROM SYS_USER\n");
            sqlBuilder.append(" WHERE SSBMBH IN (SELECT BMBH\n");
            sqlBuilder.append("                    FROM SYS_DEPARTMENT\n");
            sqlBuilder.append("                   START WITH BMBH = ?\n");
            sqlBuilder.append("                  CONNECT BY PRIOR ID = PARENTID)\n");
            ps1 = conn.prepareStatement(sqlBuilder.toString());
            ps1.setObject(1, bmbh);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                int count = rs1.getInt(1);
                if (count > 0) {
                    throw new Exception("不能删除部门：部门有下级用户");
                }
            }




            ps = conn.prepareStatement(sql);
            ps.setObject(1, bmbh);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs1 != null) {
                rs1.close();
            }

            if (ps1 != null) {
                ps1.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Dept> readDepts(ResultSet rs) throws Exception {
        try {
            List<Dept> list = new ArrayList<>();
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setId(StringEx.nullToStr(rs.getString(1)));        //ID,
                dept.setParentid(StringEx.nullToStr(rs.getString(2)));        //PARENTID,
                dept.setBmbh(StringEx.nullToStr(rs.getString(3)));        //BMBH,
                dept.setSjzdbmbh(StringEx.nullToStr(rs.getString(4)));        //SJZDBMBH,
                dept.setLevelnum(StringEx.nullToStr(rs.getString(5)));        //LEVELNUM,
                dept.setType(StringEx.nullToStr(rs.getString(6)));        //TYPE,
                dept.setName(StringEx.nullToStr(rs.getString(7)));        //NAME,
                dept.setFax(StringEx.nullToStr(rs.getString(8)));        //FAX,
                dept.setLxr(StringEx.nullToStr(rs.getString(9)));        //LXR,
                dept.setLxrdh(StringEx.nullToStr(rs.getString(10)));        //LXRDH,
                dept.setLxdz(StringEx.nullToStr(rs.getString(11)));        //LXDZ,
                dept.setZt(StringEx.nullToStr(rs.getString(12)));        //ZT,
                dept.setBz(StringEx.nullToStr(rs.getString(13)));        //BZ,
                dept.setCzryzh(StringEx.nullToStr(rs.getString(14)));        //CZRYZH,
                dept.setCzsj(StringEx.nullToStr(rs.getString(15)));        //CZSJ,
                dept.setFzjg(StringEx.nullToStr(rs.getString(16)));        //FZJG,
                dept.setSfzsbm(StringEx.nullToStr(rs.getString(17)));        //SFZSBM,
                dept.setZsbmbhs(StringEx.nullToStr(rs.getString(18)));        //ZSBMBHS,
                dept.setCode(StringEx.nullToStr(rs.getString(19)));        //CODE,
                dept.setXzqh(StringEx.nullToStr(rs.getString(20)));        //XZQH,
                dept.setRn(StringEx.nullToStr(rs.getString(21)));        //RN,
                list.add(dept);
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
     * type、type_id、dept、levelnum
     *
     * @return
     * @throws Exception
     */
    @Override
    public List typeList() throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("WITH TYPE AS\n");
        sqlBuilder.append(" (SELECT DMMC TYPE, DMZ TYPE_ID\n");
        sqlBuilder.append("    FROM BIZ_PARAM\n");
        sqlBuilder.append("   WHERE DMLB = 1001\n");
        sqlBuilder.append("     AND ZT = 1),\n");
        sqlBuilder.append("DEPT AS\n");
        sqlBuilder.append(" (SELECT DMMC NAME, SUBSTR(DMZ, 1, 1) TYPE_ID, DMZ LEV\n");
        sqlBuilder.append("    FROM BIZ_PARAM\n");
        sqlBuilder.append("   WHERE DMLB = 1002\n");
        sqlBuilder.append("     AND ZT = 1)\n");
        sqlBuilder.append("SELECT TYPE.TYPE, TYPE.TYPE_ID, DEPT.NAME DEPT, DEPT.LEV LEVELNUM\n");
        sqlBuilder.append("  FROM TYPE\n");
        sqlBuilder.append(" RIGHT JOIN DEPT\n");
        sqlBuilder.append("    ON TYPE.TYPE_ID = DEPT.TYPE_ID\n");
        sqlBuilder.append("    ORDER BY TYPE.TYPE_ID \n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            rs = ps.executeQuery();
            return QueryUtil.table(rs);
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
