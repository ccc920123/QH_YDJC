package com.scxd.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.szdt.security.des.DESUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 文件描述：数据分析库
 * C3P0数据库连接池工具类
 * 作者：齐遥遥
 * 时间：2017/2/14 11:19
 * 公司：数字灯塔
 * 部门：技术部
 * 修改人：
 * 修改时间：
 */
public class JdbcUtil {

    /* 密钥 */
    private final static String PRIVATE_KEY = "$2a$10$xbZGbbVR.HgdNgJ9hv.bCehfIxwG764uqASttBWes89pYR/6FUJNLO/8i";

    /* 数据源 */
    private static ComboPooledDataSource dataSource = null;

    /**
     * 静态代码块，创建数据库连接池
     */
    static{

        //只被创建一次
        dataSource = new ComboPooledDataSource("trffIcsCp30");

        String pwd = getPassword(dataSource.getPassword());
        dataSource.setPassword(pwd);

    }

    /**s
     * 功能描述：获取解密
     * 作者：齐遥遥
     * 时间：2017-04-11
     * @param oldPassword
     * @return
     */
    public static String getPassword(String oldPassword) {

        String pwd = "";

        try {

            DESUtil desUtil = new DESUtil(PRIVATE_KEY);
            pwd = desUtil.decrypt(oldPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pwd;
    }

    /**
     * 功能描述：得到连接
     * 作者：齐遥遥
     * 时间：2017/2/14 11:21
     */
    public static Connection getConnection() throws Exception{
        return dataSource.getConnection();
    }

    /**
     * 功能描述：释放资源
     * 作者：齐遥遥
     * 时间：2017/2/14 11:22
     */
    public static void releaseConnection(Connection connection, PreparedStatement ps, ResultSet rs) {

        try {

            if (null != rs) {
                rs.close();
            }

            if(null != ps) {
                ps.close();
            }

            if (null != connection) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述：获取主键序号
     * 作者：齐遥遥
     * 时间：2017-10-13
     * @return 序号
     */
    public static long getQxXh() throws Exception {

        long xh = 0;

        Connection connection = null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try {

            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement("SELECT SEQ_SYS_ROLE.NEXTVAL ROLE_XH FROM DUAL");
            rs = ps.executeQuery();

            while (rs.next()) {
                xh = rs.getInt("ROLE_XH");
            }

        } finally {
            JdbcUtil.releaseConnection(connection,ps,rs);
        }

        return xh;
    }
}
