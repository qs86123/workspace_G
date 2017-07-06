package com.changhong.data.jxw.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DataEntity;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.SumaryDataEntity;
import com.changhong.data.jxw.db.entity.SumaryTableInfo;
import com.changhong.data.jxw.db.entity.TableFieldInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.utils.MysqlUtils;

/**
 * 
 * mysql数据库连接类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午9:44:23
 */
@Repository
public class MysqlDao extends DbDao {

	@Value("${db.ip}")
	private String ip;
	@Value("${db.port}")
	private String port;
	@Value("${db.Name}")
	private String dbName;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	public MysqlDao() {
		super("mysql");
	}

	/**
	 * 分页查询某张表的数据
	 * 
	 * @param dbInfo
	 * @return
	 * @throws ConnException
	 * @throws SQLException
	 */
	public DataEntity getData(DatasourceInfo dbInfo, String tableName, int pageSize, int pageNum)
			throws ConnException, SQLException {
		DataEntity de = new DataEntity();
		List<JSONObject> result = new ArrayList<>();
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		Connection conn = MysqlUtils.getConnection(ip, port + "", dbName, username, password);
		// 获取该表字段名等信息
		TableInfo tbInfo = MysqlUtils.getTableInfo(conn, tableName, dbName);
		// 分页查询sql语句
		String sql = "select * from `" + tableName + "` limit " + (pageSize * pageNum) + "," + pageSize;
		PreparedStatement stData = MysqlUtils.getStatement(conn, sql);
		// 执行查询返回结果
		ResultSet rs = stData.executeQuery();
		String countSql = "select count(*) total from `" + tableName + "`";
		// 查询数据结果总条数
		PreparedStatement stCount = MysqlUtils.getStatement(conn, countSql);
		ResultSet rsCount = stCount.executeQuery();
		while (rsCount.next()) {
			de.setPageNum(pageNum);
			de.setPageSize(pageSize);
			de.setTotalCount(rsCount.getLong("total"));
		}
		while (rs.next()) {
			JSONObject json = new JSONObject();
			for (TableFieldInfo tbfi : tbInfo.getTableFieldInfos()) {
				// 以该表的字段名座位key，获取该字段的值，封装json数据
				json.put(tbfi.getColumnName(), rs.getString(tbfi.getColumnName()));
			}
			result.add(json);
		}
		de.setData(result);
		// 关闭连接，conn连接暂时不关闭，以便用户接下来的操作不重新连接数据库
		rs.close();
		stData.close();
		stCount.close();
		return de;
	}

	/**
	 * 获取表名列表
	 */
	public String[] getTables(DatasourceInfo dbInfo) throws ConnException {
		String[] tables = null;
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		Connection conn = MysqlUtils.getConnection(ip, port + "", dbName, username, password);
		tables = MysqlUtils.getTables(conn);
		return tables;
	}

	/**
	 * 获取所有表的结构信息
	 */
	public List<TableInfo> getTablesInfo(DatasourceInfo dbInfo) throws ConnException {
		List<TableInfo> tbInfos = new ArrayList<>();
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		// 获得连接
		Connection conn = MysqlUtils.getConnection(ip, port + "", dbName, username, password);
		// 获取所有的表名
		String[] tables = MysqlUtils.getTables(conn);
		for (String tb : tables) {
			// 根据表名获取表结构信息
			if (!tb.trim().equals("")) {
				TableInfo tbInfo = MysqlUtils.getTableInfo(conn, tb, dbName);
				tbInfos.add(tbInfo);
			}
		}
		return tbInfos;
	}

	/**
	 * 建表接口，暂未使用，已被批量建表接口替代
	 * 
	 * @param dbInfo
	 * @throws ConnException
	 */
	public void createTable(DatasourceInfo dbInfo) throws ConnException {
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		String tableName = "";
		String createSql = MysqlUtils.getTableDDL(ip, port, dbName, username, password, tableName);
		MysqlUtils.createTable(createSql, this.ip, this.port, this.dbName, this.username, this.password);
	}

	/**
	 * zhoufan写的接口，未使用
	 */
	@Override
	public void alterTable(String tableName, String sql) throws ConnException {
		MysqlUtils.alterTable(this.ip, Integer.valueOf(this.port), this.dbName, this.username, this.password, tableName,
				sql);
	}

	/**
	 * 获取单个表的结构信息，好像没有用到，不删
	 */
	@Override
	public TableInfo getTableInfo(DatasourceInfo dbInfo, String tableName) throws ConnException {
		TableInfo tbInfo = new TableInfo();
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		Connection conn = MysqlUtils.getConnection(ip, port + "", dbName, username, password);
		tbInfo = MysqlUtils.getTableInfo(conn, tableName, dbName);
		return tbInfo;
	}

	/**
	 * 获取建表语句
	 */
	public String getCreateTableDDL(DatasourceInfo dbInfo, String tableSource) throws ConnException {
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		String sql = MysqlUtils.getTableDDL(ip, port, dbName, username, password, tableSource);
		// 返回创建表的sql语句，从第一个"("处开始截取
		// 如果表有外键约束，那么需要去掉生成外键的sql语句，从CONSTRAINT到后括号处都不要
		if (sql.indexOf("CONSTRAINT") != -1)
			sql = sql.substring(sql.indexOf("("), sql.indexOf("CONSTRAINT") - 1) + sql.substring(sql.lastIndexOf(")"));
		else
			sql = sql.substring(sql.indexOf("("));
		return sql;
	}

	/**
	 * 在平台建表，批量执行sql语句
	 */
	public void createTable(List<String> sqls) throws ConnException {
		MysqlUtils.createTable(sqls, this.ip, this.port, this.dbName, this.username, this.password);
	}

	// 向平台数据库保存数据
	public void insertIntoDataPlat(List<SumaryDataEntity> sumaryDataList) throws ConnException {
		List<String> sqls = new ArrayList<>();
		for (SumaryDataEntity sde : sumaryDataList) {
			List<JSONObject> datas = sde.getData();
			for (JSONObject data : datas) {
				String sql = "insert into `" + sde.getSumaryTable() + "` (";
				Set<String> columns = data.keySet();
				for (String column : columns) {
					sql += "`" + column + "`,";
				}
				// 去掉最后一个逗号,继续拼接
				sql = sql.substring(0, sql.length() - 1) + ") values(";
				for (String column : columns) {
					Object d = data.get(column);
					if (d == null || d.equals("null"))
						sql += "null,";
					else
						sql += "'" + d + "',";
				}
				// 去掉最后一个逗号,插入一条数据的sql拼接完成
				sql = sql.substring(0, sql.length() - 1) + ");";
				sqls.add(sql);
			}
		}
		// sql完成之后，开始批量执行
		MysqlUtils.batchSql(sqls, this.ip, this.port, this.dbName, this.username, this.password);
	}

	// 抽取部分提供方数据库里面的数据，用来存放在示例数据表中
	@Override
	public SumaryDataEntity getSumaryData(DatasourceInfo dbInfo, String tableSource, List<String> columns)
			throws ConnException, SQLException {
		SumaryDataEntity sde = new SumaryDataEntity();
		String sql = "select * ";
		// for (String column : columns) {
		// sql += "`" + column + "`,";
		// }
		// 租掉最后一个逗号
		// sql = sql.substring(0, sql.length() - 1);
		sql += " from `" + tableSource + "` limit 10";
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		Connection conn = MysqlUtils.getConnection(ip, port + "", dbName, username, password);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<JSONObject> data = new ArrayList<>();
		// 获取该表字段名等信息,将所有字段的信息都抽取到sumaryTable，由于建的表是一样的，避免有些字段要求为非空的话，保存会出错
		TableInfo tbInfo = MysqlUtils.getTableInfo(conn, tableSource, dbName);
		while (rs.next()) {
			JSONObject json = new JSONObject();
			for (TableFieldInfo tbfi : tbInfo.getTableFieldInfos()) {
				// 以该表的字段名座位key，获取该字段的值，封装json数据
				json.put(tbfi.getColumnName(), rs.getObject(tbfi.getColumnName()));
			}
			data.add(json);
		}
		// while (rs.next()) {
		// JSONObject json = new JSONObject();
		// for (String column : columns) {
		// json.put(column, rs.getString(column));
		// }
		// data.add(json);
		// }
		sde.setData(data);
		// 关闭连接
		MysqlUtils.closeResource(null, ps, rs);
		return sde;
	}

	// 获取平台的临时表数据,根据字段
	@Override
	public List<SumaryDataEntity> getPlatSumaryData(List<SumaryTableInfo> stis) throws SQLException, ConnException {
		Connection conn = MysqlUtils.getConnection(this.ip, this.port, this.dbName, this.username, this.password);
		List<SumaryDataEntity> sdes = new ArrayList<>();
		for (SumaryTableInfo sti : stis) {
			String sql = "select ";
			for (String column : sti.getColumns()) {
				sql += "`" + column + "`,";
			}
			// 租掉最后一个逗号
			sql = sql.substring(0, sql.length() - 1);
			sql += " from `" + sti.getSumaryTable() + "` limit 10";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<JSONObject> data = new ArrayList<>();
			while (rs.next()) {
				JSONObject json = new JSONObject();
				for (String column : sti.getColumns()) {
					json.put(column, rs.getString(column));
				}
				data.add(json);
			}
			SumaryDataEntity sde = new SumaryDataEntity();
			sde.setTableSource(sti.getTableSource());
			sde.setSumaryTable(sti.getSumaryTable());
			sde.setData(data);
			sdes.add(sde);
			// 关闭连接
			MysqlUtils.closeResource(null, ps, rs);
		}
		return sdes;
	}

	// 根据字段获取生产数据，该数据是平台target表中的数据
	@Override
	public DataEntity getDataByColumns(List<String> columns, String tableSource, int pageSize, int pageNum)
			throws ConnException, SQLException {
		DataEntity de = new DataEntity();
		List<JSONObject> result = new ArrayList<>();
		Connection conn = MysqlUtils.getConnection(this.ip, this.port, this.dbName, this.username, this.password);
		String sql = "select ";
		for (String column : columns) {
			sql += "`" + column + "`,";
		}
		// 去掉最后一个逗号
		sql = sql.substring(0, sql.length() - 1);
		// 分页查询sql语句
		sql = sql + "from `" + tableSource + "` limit " + (pageSize * pageNum) + "," + pageSize;
		PreparedStatement stData = MysqlUtils.getStatement(conn, sql);
		// 执行查询返回结果
		ResultSet rs = stData.executeQuery();
		String countSql = "select count(*) total from `" + tableSource + "`";
		PreparedStatement stCount = MysqlUtils.getStatement(conn, countSql);
		ResultSet rsCount = stCount.executeQuery();
		while (rsCount.next()) {
			de.setPageNum(pageNum);
			de.setPageSize(pageSize);
			de.setTotalCount(rsCount.getLong("total"));
		}
		while (rs.next()) {
			JSONObject json = new JSONObject();
			for (String column : columns) {
				// 以该表的字段名座位key，获取该字段的值，封装json数据
				json.put(column, rs.getString(column));
			}
			result.add(json);
		}
		de.setData(result);
		// 关闭连接
		MysqlUtils.closeResource(null, stCount, rsCount);
		MysqlUtils.closeResource(null, stData, rs);
		return de;
	}

	// 获取混合表数据，暂未使用
	@Override
	public DataEntity getMixTableData(String tableName, int pageSize, int pageNum) throws ConnException, SQLException {
		DatasourceInfo dbInfo = new DatasourceInfo();
		dbInfo.setUrl(this.ip);
		dbInfo.setDbType("mysql");
		dbInfo.setPort(Integer.parseInt(this.port));
		dbInfo.setUsername(this.username);
		dbInfo.setPwd(this.password);
		dbInfo.setSchemaName(this.dbName);
		return getData(dbInfo, tableName, pageSize, pageNum);
	}

}
