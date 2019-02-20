package com.scxd.dao.management.impl;

import com.scxd.beans.common.Role;
import com.scxd.beans.management.PdaBean;
import com.scxd.common.StringEx;
import com.scxd.common.StringUtil;
import com.scxd.dao.management.ifaces.PDADao;
import com.scxd.db.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:46 2018/6/6
 * @Modified By:
 */
@Repository
public class PdaDaoImpl implements PDADao {

    @Override
    public List<PdaBean> getPdaMessage(String pdamc, String pdawym, String bmbh, String bhxj, String pageindex, String pagesize) throws Exception {
        List<String> list = new ArrayList<>();
        List<PdaBean>  pdaBeanList=new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("\n");
        sqlBuilder.append("WITH PDA AS(\n");
        sqlBuilder.append("SELECT T.ID,MC,T.BMBH,B.NAME BMMC,WYM,T.ZT,ROWNUM AS RN FROM SYS_PDA_INFO T LEFT JOIN SYS_DEPARTMENT B ON T.BMBH=B.BMBH WHERE 1=1\n");
        sqlBuilder.append("\n");
        if (StringUtil.isNotEmpty(pdamc)) {
            sqlBuilder.append(" AND  T.MC LIKE ?");
            list.add('%' + pdamc + '%');
        }
        if (StringUtil.isNotEmpty(pdawym)) {
            sqlBuilder.append("  AND  T.WYM LIKE  ?");
            list.add('%' + pdawym + '%');
        }

        if (Integer.valueOf(bhxj) == 1) {
            sqlBuilder.append(" AND T.BMBH IN(SELECT BMBH FROM SYS_DEPARTMENT  START WITH BMBH =?  CONNECT BY PRIOR ID = PARENTID);");

        } else {
            sqlBuilder.append("  AND  T.BMBH =?");
        }
        list.add(bmbh);
        sqlBuilder.append(")\n");
        sqlBuilder.append("SELECT *  FROM PDA WHERE RN BETWEEN (? - 1) * ? + 1 AND ? * ?\n");

        sqlBuilder.append("UNION ALL\n");
        sqlBuilder.append("SELECT  '0' ,NULL,NULL,NULL,NULL,NULL,COUNT(RN) FROM PDA\n");
        list.add(pageindex);
        list.add(pagesize);
        list.add(pageindex);
        list.add(pagesize);
        try {
            connection = JdbcUtil.getConnection();

            ps = connection.prepareStatement(sqlBuilder.toString());

            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i+1, list.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                PdaBean pdaBean = new PdaBean();
                pdaBean.setId(rs.getString("ID"));
                pdaBean.setMc(StringEx.nullToStr(rs.getString("MC")));
                pdaBean.setBmbh(rs.getString("BMBH"));
                pdaBean.setBmmc(rs.getString("BMMC"));
                pdaBean.setWym(rs.getString("WYM"));
                pdaBean.setZt(StringEx.nullToStr(rs.getString("ZT")));
                pdaBean.setRn(rs.getInt("RN"));
                pdaBeanList.add(pdaBean);
            }

        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return pdaBeanList;
    }
}
