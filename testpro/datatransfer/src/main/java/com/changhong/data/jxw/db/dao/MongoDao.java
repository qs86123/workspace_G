package com.changhong.data.jxw.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.entity.DataEntity;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.SumaryDataEntity;
import com.changhong.data.jxw.db.entity.SumaryTableInfo;
import com.changhong.data.jxw.db.entity.TableFieldInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.utils.MongoDBUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * mongo数据库连接类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午9:44:09
 */
@Repository
public class MongoDao extends DbDao {

	public MongoDao() {
		super("mongo");
	}

	/**
	 * 分页查询mongo某集合的数据
	 * 
	 * @param dbInfo
	 * @return
	 */
	public DataEntity getData(DatasourceInfo dbInfo, String tableName, int pageSize, int pageNum)
			throws ConnException, SQLException {
		DataEntity de = new DataEntity();
		List<JSONObject> jsons = new ArrayList<>();
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		MongoClient client = MongoDBUtils.getMongoClient(ip, port, dbName, username, password);
		MongoDatabase mdb = MongoDBUtils.getMongoDataBase(client, dbName);
		MongoCollection<Document> collection = mdb.getCollection(tableName);
		long total = collection.count();
		// 执行分页查询
		FindIterable<Document> iterable = collection.find().skip(pageNum * pageSize).limit(pageSize);
		MongoCursor<Document> cursor = iterable.iterator();
		// 结果处理
		while (cursor.hasNext()) {
			Document document = cursor.next();
			String jsonStr = document.toJson();
			jsons.add(JSONObject.parseObject(jsonStr));
		}
		de.setPageSize(pageSize);
		de.setPageNum(pageNum);
		de.setTotalCount(total);
		de.setData(jsons);
		return de;
	}

	// 获取所有mongo的集合
	public String[] getTables(DatasourceInfo dbInfo) throws ConnException {
		String[] tables = null;
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		MongoClient client = MongoDBUtils.getMongoClient(ip, port, dbName, username, password);
		MongoDatabase mdb = MongoDBUtils.getMongoDataBase(client, dbName);
		tables = MongoDBUtils.getCollectionNames(mdb);
		return tables;
	}

	// 获取某格集合的详细信息，这里会直接查询集合中的一行数据，获取字段信息，以便在平台mysql建表
	@Override
	public TableInfo getTableInfo(DatasourceInfo dbInfo, String tableName) throws ConnException {
		TableInfo tbInfo = new TableInfo();
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		tbInfo.setTableSource(tableName);
		MongoClient client = MongoDBUtils.getMongoClient(ip, port, dbName, username, password);
		MongoDatabase mdb = MongoDBUtils.getMongoDataBase(client, dbName);
		MongoCollection<Document> collection = MongoDBUtils.getMongoCollection(mdb, tableName);
		FindIterable<Document> iterable = collection.find().limit(1);
		MongoCursor<Document> cursor = null;
		try {
			cursor = iterable.iterator();
		} catch (Exception e) {
			throw new ConnException("查询数据出错" + e.getMessage());
		}
		JSONObject json = null;
		while (cursor.hasNext()) {
			Document document = cursor.next();
			String jsonStr = document.toJson();
			json = JSONObject.parseObject(jsonStr);
		}
		if (json != null) {
			List<TableFieldInfo> tableFieldInfos = new ArrayList<>();
			Set<String> keys = json.keySet();
			for (String key : keys) {
				TableFieldInfo tf = new TableFieldInfo();
				tf.setColumnName(key);
				tableFieldInfos.add(tf);
			}
			tbInfo.setTableFieldInfos(tableFieldInfos);
		}
		return tbInfo;
	}

	// 获取所有集合的详细信息会用到上面一个方法
	@Override
	public List<TableInfo> getTablesInfo(DatasourceInfo dbInfo) throws ConnException {
		List<TableInfo> tbInfos = new ArrayList<>();
		String[] tables = null;
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		MongoClient client = MongoDBUtils.getMongoClient(ip, port, dbName, username, password);
		MongoDatabase mdb = MongoDBUtils.getMongoDataBase(client, dbName);
		tables = MongoDBUtils.getCollectionNames(mdb);
		for (String table : tables) {
			if (!table.trim().equals("")) {
				TableInfo tbInfo = new TableInfo();
				tbInfo = getTableInfo(dbInfo, table);
				tbInfos.add(tbInfo);
			}
		}
		return tbInfos;
	}

	// zhoufan写的，没用到
	@Override
	public void alterTable(String tableName, String sql) throws ConnException {

	}

	// 根据表信息在平台建立相应的mysql表，如果这个时候查询到的mongodb数据库里面没有数据，那么拿不到字段信息，
	// 则无法在平台mysql建表，直接抛出异常
	@Override
	public String getCreateTableDDL(DatasourceInfo dbInfo, String tableSource) throws ConnException {
		TableInfo tbInfo = getTableInfo(dbInfo, tableSource);
		String sql = "(";
		List<TableFieldInfo> infos = tbInfo.getTableFieldInfos();
		if (infos == null) {
			throw new ConnException("mongo建表语句获取失败，数据可能为空");
		}
		if (infos.size() == 0) {
			throw new ConnException("mongo建表语句获取失败，数据可能为空");
		}
		for (TableFieldInfo tf : infos) {
			String columnName = tf.getColumnName();
			String columnType = tf.getColumnType();
			if (columnType == null)
				columnType = "varchar(255)";
			if (columnType.equals(""))
				columnType = "varchar(255)";
			sql += "`" + columnName + "` " + columnType + " NULL ,";
		}
		// 去掉最后一个逗号
		sql = sql.substring(0, sql.length() - 1);
		sql += ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}

	// 由于平台只有mysql数据库，这里建表的方法就没用了
	@Override
	public void createTable(List<String> sqls) throws ConnException {

	}

	// 抽取部分提供方数据库里面的数据，用来存放在示例数据表中
	@Override
	public SumaryDataEntity getSumaryData(DatasourceInfo dbInfo, String tableSource, List<String> columns)
			throws ConnException, SQLException {
		SumaryDataEntity sde = new SumaryDataEntity();
		TableInfo tbInfo = new TableInfo();
		String ip = dbInfo.getUrl();
		int port = dbInfo.getPort();
		String dbName = dbInfo.getSchemaName();
		String username = dbInfo.getUsername();
		String password = dbInfo.getPwd();
		tbInfo.setTableSource(tableSource);
		MongoClient client = MongoDBUtils.getMongoClient(ip, port, dbName, username, password);
		MongoDatabase mdb = MongoDBUtils.getMongoDataBase(client, dbName);
		MongoCollection<Document> collection = MongoDBUtils.getMongoCollection(mdb, tableSource);
		FindIterable<Document> iterable = collection.find().limit(10);
		MongoCursor<Document> cursor = null;
		try {
			cursor = iterable.iterator();
		} catch (Exception e) {
			throw new ConnException(e.getMessage());
		}
		List<JSONObject> data = new ArrayList<>();
		while (cursor.hasNext()) {
			JSONObject json = JSONObject.parseObject(cursor.next().toJson());
			data.add(json);
		}
		sde.setData(data);
		MongoDBUtils.closeResource(cursor, null);
		return sde;
	}

	// 将示例数据插入平台数据库，由于平台之后又mysql数据库，所以这里就不写了
	@Override
	public void insertIntoDataPlat(List<SumaryDataEntity> sumaryDataList) throws ConnException {

	}

	// 获取平台的数据，平台只有mysql，这里就不写了
	@Override
	public List<SumaryDataEntity> getPlatSumaryData(List<SumaryTableInfo> stis) throws SQLException, ConnException {
		return null;
	}

	// 获取平台的数据，平台只有mysql，这里就不写了
	@Override
	public DataEntity getDataByColumns(List<String> columns, String tableName, int pageSize, int pageNum)
			throws ConnException, SQLException {
		return null;
	}

	// 混合表数据查询的方法，未使用
	@Override
	public DataEntity getMixTableData(String tableName, int pageSize, int pageNum) throws ConnException, SQLException {
		return null;
	}

}
