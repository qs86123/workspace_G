package com.myself.edu.movedata.utils;

import com.myself.edu.movedata.parse.bean.UsrCashMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql数据库连接工具类
 * <p>
 * Company: changhong
 *
 * @author wangtao
 * @date 2017年3月24日上午10:11:03
 */
public class MysqlUtils {

    private static final Logger logger = LoggerFactory.getLogger(MysqlUtils.class);

    /**
     * 获得连接
     *
     * @return
     */
    public static Connection getConnection(String ip, String port, String dbName, String user, String password)
            throws Exception {
        try {
            String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName
                    + "?useUnicode=true&amp;amp;characterEncoding=utf-8?connectTimeout=10000&socketTimeout=10000";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn; // 获得连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getStatement(Connection conn, String sql) {
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            return st;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UsrCashMonth select(String sql, Connection connection) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UsrCashMonth ucm = null;
            int count = 0;
            while (rs.next()) {
                count++;
                ucm = new UsrCashMonth();
                ucm.setYear(rs.getString("year"));
                String[] str = new String[12];
                for (int i = 1; i < 13; i++)
                    str[i - 1] = rs.getString("month" + i);
                ucm.setCashs(str);
            }
            logger.info("查询到<" + count + ">条数据");
            return ucm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> selectAllDzb(String sql, Connection connection) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UsrCashMonth ucm = null;
            List<String> dzbs = new ArrayList<>();
            while (rs.next()) {
                dzbs.add(rs.getString("username"));
            }
            return dzbs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 批量执行sql语句，这个接口跟上面你那个批量建表接口一样
    public static void batchSql(List<String> createSqls, String ip, String port, String dbName, String user,
                                String password) throws Exception {
        Connection connection = getConnection(ip, port, dbName, user, password);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("");
            for (String sql : createSqls) {
                ps.addBatch(sql);
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("操作失败:" + e.getMessage());
        }
    }

    // 批量执行sql语句，这个接口跟上面你那个批量建表接口一样
    public static void batchSql(List<String> createSqls, Connection connection) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("");
            for (String sql : createSqls) {
                ps.addBatch(sql);
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("操作失败:" + e.getMessage());
        }
    }

    // 关闭连接
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
