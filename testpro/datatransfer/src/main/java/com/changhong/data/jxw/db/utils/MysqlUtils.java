package com.changhong.data.jxw.db.utils;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.GetConnectionTimeoutException;
import com.changhong.data.jxw.db.entity.TableFieldInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * mysql数据库连接工具类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午10:11:03
 */
public class MysqlUtils {

	private static Map<String, Connection> conns = new HashMap<>();

	/**
	 * 获得连接
	 *
	 * @return
	 * @throws ConnException
	 */
	public static Connection getConnection(String ip, String port, String dbName, String user, String password)
			throws ConnException {
		// 添加连接缓存，避免每次访问都重新连接
		if (conns.get(ip + port + dbName + user + password) != null) {
			System.out.println("return cached mysqlconn");
			return conns.get(ip + port + dbName + user + password);
		}
		try {
			String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName
					+ "?useUnicode=true&amp;amp;characterEncoding=utf-8?connectTimeout=10000&socketTimeout=10000";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			conns.put(ip + port + dbName + user + password, conn);
			return conn; // 获得连接
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof UnknownHostException) {
				throw new ConnException("未知主机,请检查IP地址是否正确");
			} else if (e instanceof CommunicationsException) {
				throw new ConnException("连接超时，请检查端口和ip是否正确");
			} else if (e instanceof MySQLSyntaxErrorException) {
				throw new ConnException("未知数据库:" + dbName);
			} else {
				if (e.getMessage().indexOf("Access denied") != -1)
					throw new ConnException("用户名或密码错误");
				else
					throw new ConnException("其他错误:" + e.getMessage());
			}
		}
	}

	/**
	 * 使用druid连接处获取连接，暂未使用
	 *
	 * @param url
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 * @return
	 * @throws ConnException
	 */
	public static Connection getConnectionDruid(String url, String port, String dbName, String username,
			String password) throws ConnException {
		Map<String, String> map = new HashMap<>();
		map.put("url",
				"jdbc:mysql://" + url + ":" + port + "/" + dbName + "?useUnicode=true&amp;amp;characterEncoding=utf-8");
		map.put("username", username);
		map.put("password", password);
		map.put("driverClassName", "com.mysql.jdbc.Driver");
		map.put("maxWait", "10000");
		try {
			DataSource datasource = DruidDataSourceFactory.createDataSource(map);
			Connection conn = datasource.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof UnknownHostException) {
				throw new ConnException("未知主机错误:" + e.getMessage());
			} else if (e instanceof GetConnectionTimeoutException) {
				throw new ConnException("连接超时错误:" + e.getMessage());
			} else {
				throw new ConnException("密码错误:" + e.getMessage());
			}
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

	/**
	 * 获取执行sql的statement
	 *
	 * @param conn
	 * @param sql
	 * @return
	 */
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

	/**
	 * 获取某个table的字段名等信息
	 *
	 * @param conn
	 * @param tableName
	 * @return
	 * @throws ConnException
	 */
	public static TableInfo getTableInfo(Connection conn, String tableSource, String schemaName) throws ConnException {
		try {
			String sql = "select b.TABLE_COMMENT TABLE_COMMENT,COLUMN_NAME, COLUMN_TYPE,IS_NULLABLE, COLUMN_KEY ,COLUMN_DEFAULT,`EXTRA`,COLUMN_COMMENT "
					+ "from Information_schema.columns as a left join Information_schema.tables as b on a.TABLE_NAME = b.TABLE_NAME and b.table_schema=a.TABLE_SCHEMA "
					+ "where a.TABLE_SCHEMA='" + schemaName + "' and a.TABLE_NAME='" + tableSource + "' ";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			String tableName = "";
			List<TableFieldInfo> tableFieldInfos = new ArrayList<>();
			boolean tableExitis = false;
			while (rs.next()) {
				tableExitis = true;
				tableName = rs.getString("TABLE_COMMENT");
				TableFieldInfo ti = new TableFieldInfo();
				ti.setColumnName(rs.getString("COLUMN_NAME"));
				ti.setColumnType(rs.getString("COLUMN_TYPE"));
				ti.setIsNullAble(rs.getString("IS_NULLABLE"));
				ti.setColumnKey(rs.getString("COLUMN_KEY"));
				ti.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
				ti.setExtra(rs.getString("EXTRA"));
				ti.setColumnComment(rs.getString("COLUMN_COMMENT"));
				tableFieldInfos.add(ti);
			}
			if (!tableExitis) {
				throw new ConnException(schemaName + "库中没有" + tableSource + "该表信息");
			}
			// 关闭连接
			closeResource(null, st, rs);
			return new TableInfo(tableSource, tableName, tableFieldInfos);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * @author:ZhouFan [https://git.oschina.net/fantazy]
	 * @Date:2017/3/10 9:10
	 * @Content:获取建表语句
	 */
	public static String getTableDDL(String ip, int port, String dbName, String user, String password, String tabelName)
			throws ConnException {
		Connection connection = getConnection(ip, String.valueOf(port), dbName, user, password);
		String ddl = "SHOW CREATE TABLE `" + tabelName + "`";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = connection.prepareStatement(ddl);
			rs = psmt.executeQuery();
			while (rs.next()) {
				return rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConnException("获取建表语句失败:" + e.getMessage());
		} finally {
			closeResource(null, psmt, rs);
		}
		return null;
	}

	// 该接口已被批量sql建表代替
	public static void createTable(String createSql, String ip, String port, String dbName, String user,
			String password) throws ConnException {
		Connection connection = getConnection(ip, port, dbName, user, password);
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(createSql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConnException("建表失败:" + e.getMessage());
		} finally {
			conns.remove(ip + port + dbName + user + password);
			closeResource(connection, ps, null);
		}
	}

	// 批量建表语句
	public static void createTable(List<String> createSqls, String ip, String port, String dbName, String user,
			String password) throws ConnException {
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
			throw new ConnException("建表失败:" + e.getMessage());
		} finally {
			conns.remove(ip + port + dbName + user + password);
			closeResource(connection, ps, null);
		}
	}

	// 批量执行sql语句，这个接口跟上面你那个批量建表接口一样
	public static void batchSql(List<String> createSqls, String ip, String port, String dbName, String user,
			String password) throws ConnException {
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
			throw new ConnException("操作失败:" + e.getMessage());
		} finally {
			conns.remove(ip + port + dbName + user + password);
			closeResource(connection, ps, null);
		}
	}

	/**
	 * @author:ZhouFan [https://git.oschina.net/fantazy]
	 * @Date:2017/3/13 10:20
	 * @Content:获取指定列的字段信息
	 */
	public static List<TableFieldInfo> getTableFieldInfos(String ip, int port, String dbName, String username,
			String password, String tableName, String columnNames[]) throws ConnException {
		List<TableFieldInfo> tableFieldInfos = null;
		Connection connection = null;
		connection = getConnection(ip, String.valueOf(port), dbName, username, password);
		tableFieldInfos = getTableFieldInfos(connection, tableName, columnNames);
		return tableFieldInfos;
	}

	/**
	 * @author:ZhouFan [https://git.oschina.net/fantazy]
	 * @Date:2017/3/13 10:31
	 * @Content: getTableFieldInfos(String ip, int port, String dbName, String
	 *           username, String password, String tableName, String
	 *           columnNames[]) 的重载方法,获取指定列的字段信息.
	 */
	public static List<TableFieldInfo> getTableFieldInfos(Connection connection, String tableName, String[] columnNames)
			throws ConnException {
		String sql = "select COLUMN_NAME, COLUMN_TYPE,IS_NULLABLE,COLUMN_KEY,COLUMN_DEFAULT,`EXTRA`,COLUMN_COMMENT from Information_schema.columns where table_Name=";
		List<TableFieldInfo> fieldInfos = new ArrayList<>();
		for (String columnName : columnNames) {
			// 指定的字段描述
			sql += "'" + tableName + "' and COLUMN_NAME='" + columnName + "'";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					TableFieldInfo ti = new TableFieldInfo();
					ti.setColumnName(rs.getString("COLUMN_NAME"));
					ti.setColumnType(rs.getString("COLUMN_TYPE"));
					ti.setIsNullAble(rs.getString("IS_NULLABLE"));
					ti.setColumnKey(rs.getString("COLUMN_KEY"));
					ti.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
					ti.setExtra(rs.getString("EXTRA"));
					ti.setColumnComment(rs.getString("COLUMN_COMMENT"));
					fieldInfos.add(ti);
				}
			} catch (SQLException e) {
				throw new ConnException("字段描述有误:" + e.getMessage());
			} finally {
				closeResource(null, pstmt, rs);
			}
		}
		return fieldInfos;
	}

	/**
	 * @Author:ZhouFan [https://git.oschina.net/fantazy]
	 * @Date:2017/3/13 13:56
	 * @Content:修改表
	 */
	public static void alterTable(String ip, int port, String dbName, String username, String password,
			String tableName, String sql) throws ConnException {
		if (sql == null || "".equals(sql))
			return;
		PreparedStatement pstmt = null;
		try {
			Connection connection = getConnection(ip, String.valueOf(port), dbName, username, password);
			pstmt = connection.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException e) {
			throw new ConnException(e.getMessage());
		} finally {
			closeResource(null, pstmt, null);
		}
	}

	/**
	 * @author:ZhouFan [https://git.oschina.net/fantazy]
	 * @Date:2017/3/13 13:52
	 * @Content:getTableInfo方法重载,获取表信息
	 */
	public static TableInfo getTableInfo(String ip, int port, String dbName, String username, String password,
			String tableName) throws ConnException {
		Connection connection = getConnection(ip, String.valueOf(port), dbName, username, password);
		TableInfo tableInfo = getTableInfo(connection, tableName, dbName);
		return tableInfo;
	}

	// 在定时任务中调度，定时清除链接信息
	public static void closeConn() {
		System.out.println("mysql conn close all");
		Set<String> keys = conns.keySet();
		for (String key : keys) {
			closeResource(conns.get(key), null, null);
			conns.remove(key);
		}
	}

}
