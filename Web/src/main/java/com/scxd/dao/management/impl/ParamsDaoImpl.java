package com.scxd.dao.management.impl;

import com.scxd.beans.management.ParamsBean;
import com.scxd.beans.management.PdaBean;
import com.scxd.common.StringEx;
import com.scxd.common.StringUtil;
import com.scxd.dao.management.ifaces.ParamsDao;
import com.scxd.db.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 张翔
 * 公司：四川星盾科技股份有限公司
 * 部门：技术部 PDA
 * 创建时间: 2018/6/20 下午 06:04
 * 描述：
 * 修改人：
 * 修改时间：
 */
@Repository
public class ParamsDaoImpl implements ParamsDao {

    @Override
    public List<ParamsBean> getPdaMessage(String bmbh, String csmc, String page, String pagesize) throws Exception {
        List<String> list = new ArrayList<>();
        List<ParamsBean>  beanList=new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("\n");



        try {
            connection = JdbcUtil.getConnection();

            ps = connection.prepareStatement(sqlBuilder.toString());

            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i+1, list.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                ParamsBean pdaBean = new ParamsBean();
                pdaBean.setId(rs.getString("ID"));
                pdaBean.setGjz(rs.getString("GJZ"));
                pdaBean.setCsmc(rs.getString("CSMC"));
                pdaBean.setCsz(rs.getString("CSZ"));
                pdaBean.setSfjc(rs.getString("SFJC"));
                pdaBean.setCzsj(rs.getString("CZSJ"));
                pdaBean.setRn(rs.getInt("RN"));
                beanList.add(pdaBean);
            }

        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }

        return beanList;
    }
}
