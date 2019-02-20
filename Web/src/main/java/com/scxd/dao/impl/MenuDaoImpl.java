package com.scxd.dao.impl;

import com.scxd.beans.common.MenuBean;
import com.scxd.common.StringEx;
import com.scxd.dao.MenuDao;
import com.scxd.db.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdfs on 2017/9/9.
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    private MenuBean menuBean;

    /**
     * 功能描述：克隆一个菜单对象
     * 作者：齐遥遥
     * 时间：2017-09-09
     * @return 克隆的菜单对象
     * @throws CloneNotSupportedException
     */
    public MenuBean getMenuBean() throws CloneNotSupportedException {

        if(null == this.menuBean) {
            this.menuBean = new MenuBean();
        }

        return menuBean.clone();
    }

    @Override
    public List<MenuBean> getMenusList(String type, String menuId) throws Exception {

        List<MenuBean> menuBeanList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sbSql = new StringBuilder("");

        sbSql.append("SELECT MENU_ID,");
        sbSql.append(      " A.PARENTID    PRE_MENU_ID,");
        sbSql.append(      " A.NAME        MENU_NAME,");
        sbSql.append(      " A.LEVELNUM    LEVEL_ORDER,");
        sbSql.append(      " A.NAVIGATEURL DO_PATH,");
        sbSql.append(      " A.TARGET      MENU_TYPE,");
        sbSql.append(      " A.ICO        ICON,");
        sbSql.append(      " A.STATE       VALIDITY");
        sbSql.append("  FROM SYS_MENU A");
        sbSql.append(" WHERE A.STATE = 1");
        sbSql.append(" ORDER BY A.SORTCODE");

        try {

            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sbSql.toString());

            rs = ps.executeQuery();

            MenuBean menuTmp = null;

            while (rs.next()) {

                menuTmp = this.getMenuBean();
                menuTmp.setId(StringEx.nullToStr(rs.getString("MENU_ID")));
                menuTmp.setpId(StringEx.nullToStr(rs.getString("PRE_MENU_ID")));
                menuTmp.setName(StringEx.nullToStr(rs.getString("MENU_NAME")));
                menuTmp.setDoPath(StringEx.nullToStr(rs.getString("DO_PATH")));
                menuTmp.setType(StringEx.nullToStr(rs.getString("MENU_TYPE")));
                menuTmp.setLevel(rs.getInt("LEVEL_ORDER"));

                if(4 == rs.getInt("LEVEL_ORDER")) {
                    menuTmp.setIcon("/Web/imgs/menu/menu_1.png");
                } else {
                    menuTmp.setIcon("/Web/imgs/menu/menu_2.png");
                }

                menuBeanList.add(menuTmp);
            }

        }  finally {
            JdbcUtil.releaseConnection(connection,ps,rs);
        }

        return menuBeanList;
    }

    /**
     * 功能描述：保存所有菜单
     * 作者：齐遥遥
     * 时间：2017-09-11
     * @param menuBeanList
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveMenus(List<MenuBean> menuBeanList) throws Exception {

        boolean flag = false;

        Connection connection = null;

        int size = menuBeanList.size();

        try {

            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);

            // 菜单变动需求较小，全部删除重建比较方便快捷（主要是为了解决排序问题）
            this.truncateMenu(connection);

            MenuBean tmp = null;

            for(int i = 0; i < size;i++) {

                tmp = menuBeanList.get(i);
                tmp.setSortcode(i + 1);

                this.saveOneMenu(connection,tmp);
            }

            connection.commit();

            flag = true;
        } catch (Exception e) {

            connection.rollback();
            e.printStackTrace();
            throw e;

        } finally {
            JdbcUtil.releaseConnection(connection,null,null);
        }

        return flag;
    }

    /**
     * 功能描述：清空菜单表
     * 作者：齐遥遥
     * 时间：2017-10-23
     * @param connection
     * @throws Exception
     */
    public void truncateMenu(Connection connection) throws Exception {

        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("DELETE FROM SYS_MENU");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.releaseConnection(null,ps,null);
        }
    }

    /**
     * 功能描述：删除菜单
     * 作者：齐遥遥
     * 时间：2017-09-13
     * @param connection
     * @param menuBean
     * @return
     * @throws Exception
     */
    public boolean deleteMenu(Connection connection,MenuBean menuBean) throws Exception {

        boolean flag = false;

        PreparedStatement ps = null;
        int result = -1;

        if(null != menuBean) {

            StringBuilder sbSql = new StringBuilder("DELETE FROM SYS_MENU A WHERE A.MENU_ID = ? ");
            String[] menues = menuBean.getName().replaceAll("hasno","").split(",");
            int len = menues.length;

            for(int i = 0;i < len;i++) {

                try {

                    ps = connection.prepareStatement(sbSql.toString());
                    ps.setString(1,menues[i]);

                    result = ps.executeUpdate();

                    if(result > 0) {
                        flag = true;
                    }

                } finally {
                    JdbcUtil.releaseConnection(null,ps,null);
                }

            }

        }

        return flag;
    }

    /**
     * 功能描述：保存一条菜单
     * 作者：齐遥遥
     * @param connection
     * @param menuBean
     * @return
     * @throws Exception
     */
    public boolean saveOneMenu(Connection connection,MenuBean menuBean) throws Exception {

        boolean flag = false;
        PreparedStatement ps = null;

        StringBuilder sbSql = new StringBuilder("");

        sbSql.append("INSERT INTO SYS_MENU");
        sbSql.append("  (MENU_ID,");
        sbSql.append("   PARENTID,");
        sbSql.append("   NAVIGATEURL,");
        sbSql.append("   NAME,");
        sbSql.append("   MID,");
        sbSql.append("   SORTCODE,");
        sbSql.append("   STATE,");
        sbSql.append("   TARGET,");
        sbSql.append("   ICO,");
        sbSql.append("   LEVELNUM)");
        sbSql.append("VALUES");
        sbSql.append("  (?,"); // 1.菜单ID
        sbSql.append("   ?,"); // 2.父ID
        sbSql.append("   ?,"); // 3.路径
        sbSql.append("   ?,"); // 4.菜单名
        sbSql.append("   0,"); // 所属功能
        sbSql.append("   ?,"); // 5排序
        sbSql.append("   1,"); // 状态
        sbSql.append("   ?,"); // 6.target
        sbSql.append("   ?,"); // 7.图标
        sbSql.append("   ?)"); // 8菜单 等级

        if(!checkMenuById(connection,menuBean.getId())) {

            try {

                ps = connection.prepareStatement(sbSql.toString());

                ps.setString(1,menuBean.getId());
                ps.setString(2,null == menuBean.getpId() ? "0" : menuBean.getpId());
                ps.setString(3,"undefined".equals(menuBean.getDoPath()) ? "" : menuBean.getDoPath());
                ps.setString(4,menuBean.getName());
                ps.setInt(5,menuBean.getSortcode());
                ps.setString(6,("".equals(StringEx.nullToStr(menuBean.getType())) || "undefined".equals(StringEx.nullToStr(menuBean.getType()))) ? "_parend" : menuBean.getType());
                ps.setString(7,"".equals(StringEx.nullToStr(menuBean.getIcon())) || "undefined".equals(StringEx.nullToStr(menuBean.getIcon())) ? "" : menuBean.getIcon());
                ps.setInt(8,menuBean.getLevel());

                ps.execute();

                flag = true;

            } finally {
                JdbcUtil.releaseConnection(null,ps,null);
            }

        }

        return flag;
    }

    /**
     * 功能描述：判断菜单ID是否存在
     * 作者：齐遥遥
     * 时间：2017-09-13
     * @param connection
     * @param menuId
     * @return
     * @throws Exception
     */
    public boolean checkMenuById(Connection connection,String menuId) throws Exception{

        boolean flag = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = connection.prepareStatement("SELECT A.MENU_ID FROM SYS_MENU A WHERE A.MENU_ID = ? ");
            ps.setString(1,menuId);

            rs = ps.executeQuery();

            while (rs.next()) {
                flag = true;
            }

        } finally {
            JdbcUtil.releaseConnection(null,ps,rs);
        }

        return flag;
    }

    @Override
    public List<Integer> getMenuId(int[] id) throws Exception {
        String menuId="";
        int deptId;
        for(int i=0;i<id.length-1;i++){
            deptId=id[i];
            menuId+=deptId+",";
        }
        menuId+=id[id.length-1];
        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("SELECT DISTINCT PARENTID");
        sbSql.append(" FROM SYS_MENU ");
        sbSql.append("WHERE MENU_ID IN (SELECT DISTINCT (PARENTID)");
        sbSql.append("                    FROM SYS_MENU");
        sbSql.append("                   WHERE MENU_ID IN (").append(menuId).append("))");

        sbSql.append("UNION ALL ");
        sbSql.append("SELECT DISTINCT (PARENTID)");
        sbSql.append("  FROM SYS_MENU");
        sbSql.append("                   WHERE MENU_ID IN (").append(menuId).append(")");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        conn=JdbcUtil.getConnection();
        ps=conn.prepareStatement(sbSql.toString());
        rs=ps.executeQuery();
        List<Integer> list=null;

        try{
            list=new ArrayList<>();
            while (rs.next()){
                 list.add(rs.getInt(1));
            }

        }catch (Exception e){
            JdbcUtil.releaseConnection(conn,ps,rs);
        }

        return list;

    }
}
