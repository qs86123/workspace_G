package com.myself.edu.movedata.compare.dao;

import java.sql.*;
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

    /**
     * 获取某个库所有table的名字
     *
     * @param conn
     * @return
     */
    public static String[] getTables(Connection conn) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("show tables");
            rs = st.executeQuery();
            String str = "";
            while (rs.next()) {
                str += rs.getString(1) + ",";
            }
            closeResource(null, st, rs);
            return str.split(",");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // 批量建表语句
    public static void createTable(List<String> createSqls, String ip, String port, String dbName, String user,
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
            throw new Exception("建表失败:" + e.getMessage());
        } finally {
            closeResource(connection, ps, null);
        }
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
        } finally {
            closeResource(connection, ps, null);
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
