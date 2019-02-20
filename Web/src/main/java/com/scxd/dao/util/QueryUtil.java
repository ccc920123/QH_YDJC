package com.scxd.dao.util;

import com.scxd.db.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 标题：dao工具类
 * 说明: 查询表格形式报表数据
 * 作者：武伟
 * 日期：2017/9/11
 */
public class QueryUtil {

    /**
     * 结果集合解析成List<Map<String, Object>>
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> listMap(ResultSet rs) throws Exception {
        try {
            ResultSetMetaData rmd = rs.getMetaData();
            int colCount = rmd.getColumnCount();
            List list = new ArrayList();
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>(colCount);
                for (int i = 1; i <= colCount; i++) {
                    map.put(rmd.getColumnName(i).toLowerCase(), rs.getObject(i)==null?"":rs.getObject(i));

                }
                list.add(map);
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
    public static List<Map<String, Object>> listMapUpperCase(ResultSet rs) throws Exception {
        try {
            ResultSetMetaData rmd = rs.getMetaData();
            int colCount = rmd.getColumnCount();
            List list = new ArrayList();
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>(colCount);
                for (int i = 1; i <= colCount; i++) {
                    map.put(rmd.getColumnName(i).toUpperCase(), rs.getObject(i)==null?"":rs.getObject(i));

                }
                list.add(map);
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
     * 解析一行数据为map
     * 列名-key
     * 值-vlaue
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static Map<String, Object> map(ResultSet rs) throws Exception {
        try {
            ResultSetMetaData rmd = rs.getMetaData();
            int colCount = rmd.getColumnCount();
            if (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>(colCount);
                for (int i = 1; i <= colCount; i++) {
                    map.put(rmd.getColumnName(i).toLowerCase(), rs.getObject(i));

                }
                return map;
            }
            return null;
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
     * 查询表格数据，返回List<List>
     * 不关闭数据库链接
     *
     * @param conn
     * @param sql
     * @param values
     * @return
     * @throws Exception
     */
    public static List<List> table(Connection conn, String sql, Object... values) throws Exception {
        PreparedStatement ps = QueryUtil.PreparedStatement(conn, sql, values);
        return table(ps);
    }

    /**
     * 查询表格数据，返回List<List>
     * 关闭连接
     *
     * @param sql
     * @param values
     * @return
     * @throws Exception
     */
    public static List<List> table(String sql, Object... values) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ps = PreparedStatement(conn, sql, values);
            return table(ps);
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
     * 执行查询，解析结果集
     *
     * @param ps
     * @return list list.get(0) 为表头
     * @throws Exception
     */
    public static List<List> table(PreparedStatement ps) throws Exception {
        try {
            ResultSet rs = ps.executeQuery();
            return QueryUtil.table(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public static List<List> table(ResultSet rs) throws Exception {
        try {
            List<List> table = new ArrayList<>();
            ResultSetMetaData rmd = rs.getMetaData();
            int colCount = rmd.getColumnCount();

            List tHead = new ArrayList(colCount);
            for (int i = 1; i <= colCount; i++) {
                tHead.add(rmd.getColumnName(i));
            }
            table.add(tHead);
            while (rs.next()) {
                List row = new ArrayList(colCount);
                for (int i = 1; i <= colCount; i++) {
                    row.add(rs.getObject(i));
                }
                table.add(row);
            }
            return table;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }


    public static Connection getConnection() throws Exception {
        return JdbcUtil.getConnection();
    }

    /**
     * 创建PreparedStatement
     *
     * @param conn
     * @param sql
     * @param values
     * @return
     * @throws Exception
     */
    private static PreparedStatement PreparedStatement(Connection conn, String sql, Object... values) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql);
        if (values == null || values.length == 0) {
            return ps;
        }
        int i = 1;
        for (Object value : values) {
            ps.setObject(i++, value);
        }
        return ps;
    }

    /**
     * 结果集解析成map
     * 第一列作为key
     * 第二列作为value
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static Map<String, String> keyValue(ResultSet rs) throws Exception {
        try {
            Map<String, String> map = new LinkedHashMap<>();
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
        }
    }

    /**
     * 只解析一行
     * 列名作为key，值作为value
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static Map<String, String> MapFieldValue(ResultSet rs) throws Exception {
        try {
            ResultSetMetaData rmd = rs.getMetaData();
            int colCount = rmd.getColumnCount();
            if (rs.next()) {
                Map<String, String> map = new LinkedHashMap<>(colCount);
                for (int i = 1; i <= colCount; i++) {
                    map.put(rmd.getColumnName(i).toLowerCase(), rs.getString(i));

                }
                return map;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
