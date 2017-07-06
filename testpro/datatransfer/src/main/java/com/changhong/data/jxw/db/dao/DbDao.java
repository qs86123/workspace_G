package com.changhong.data.jxw.db.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changhong.data.jxw.db.entity.DataEntity;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.SumaryDataEntity;
import com.changhong.data.jxw.db.entity.SumaryTableInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;

/**
 * 
 * 数据库连接抽象类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午9:43:40
 */
public abstract class DbDao {

	private static Map<String, DbDao> map = new HashMap<>();

	public DbDao(String key) {
		map.put(key, this);
	}

	public static DbDao getDao(String type) throws ConnException {
		DbDao dao = map.get(type);
		if (dao == null)
			throw new ConnException("不支持的数据库类型：" + type);
		return dao;
	}

	public abstract DataEntity getData(DatasourceInfo dbInfo, String tableName, int pageSize, int pageNum)
			throws ConnException, SQLException;

	public abstract String[] getTables(DatasourceInfo dbInfo) throws ConnException;

	public abstract TableInfo getTableInfo(DatasourceInfo dbInfo, String tableName) throws ConnException;

	public abstract List<TableInfo> getTablesInfo(DatasourceInfo dbInfo) throws ConnException;

	public abstract void alterTable(String tableName, String sql) throws ConnException;

	public abstract String getCreateTableDDL(DatasourceInfo dbInfo, String tableSource) throws ConnException;

	public abstract void createTable(List<String> sqls) throws ConnException;

	public abstract SumaryDataEntity getSumaryData(DatasourceInfo dbInfo, String tableSource, List<String> columns)
			throws ConnException, SQLException;

	public abstract void insertIntoDataPlat(List<SumaryDataEntity> sumaryDataList) throws ConnException;

	public abstract List<SumaryDataEntity> getPlatSumaryData(List<SumaryTableInfo> stis)
			throws SQLException, ConnException;

	public abstract DataEntity getDataByColumns(List<String> columns, String tableName, int pageSize, int pageNum)
			throws ConnException, SQLException;

	public abstract DataEntity getMixTableData(String tableName, int pageSize, int pageNum)
			throws ConnException, SQLException;
}
