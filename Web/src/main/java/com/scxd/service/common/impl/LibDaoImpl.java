package com.scxd.service.common.impl;

import com.scxd.dao.mapper.SysParamMapper;
import com.scxd.db.JdbcUtil;
import com.scxd.service.common.LibDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LibDaoImpl implements LibDao {
//    @Resource
//    private SysParamMapper sysParamMapper;

    @Override
    public Map<String, String> sysParam() throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT KEY, VALUE \n");
        sqlBuilder.append("  FROM SYS_PARAM\n");
        sqlBuilder.append(" WHERE KEY IS NOT NULL\n");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            rs = ps.executeQuery();
            Map<String, String> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getString(2));
            }
            return map;
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
    public Map<String, String> getCJbhMap() throws Exception{


        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("  select jkid key,cjsqbh value from sys_interface \n");
        sqlBuilder.append(" WHERE jkid IS NOT NULL\n");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            rs = ps.executeQuery();
            Map<String, String> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getString(2));
            }
            return map;
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
}