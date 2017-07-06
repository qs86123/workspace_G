package com.changhong.data.jxw.db.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.dao.DbDao;
import com.changhong.data.jxw.db.entity.DataPackageColumnInfo;
import com.changhong.data.jxw.db.entity.DataPackageInfo;
import com.changhong.data.jxw.db.entity.DataPackageTableInfo;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.SumaryDataEntity;
import com.changhong.data.jxw.db.entity.req.ColumnInfoReq;
import com.changhong.data.jxw.db.entity.req.DataPackageReq;
import com.changhong.data.jxw.db.entity.req.DbAndTableInfo;
import com.changhong.data.jxw.db.entity.req.TableInfoReq;
import com.changhong.data.jxw.db.entity.res.DataPackageInfoRes;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.db.mapper.DataPackageColumnMapper;
import com.changhong.data.jxw.db.mapper.DataPackageMapper;
import com.changhong.data.jxw.db.mapper.DataPackageTableMapper;
import com.changhong.data.jxw.db.mapper.DatasourceMapper;

/**
 * 
 * 用户打包使用到的service层
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午9:50:05
 */
@Service
public class DataPackageService {

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

	@Autowired
	private DataPackageMapper dpMapper;
	@Autowired
	private DataPackageTableMapper dptMapper;
	@Autowired
	private DataPackageColumnMapper dpcMapper;
	@Autowired
	private DatasourceMapper dsMapper;

	// 根据userid查询数据源id，再根据数据源id查询datapackageinfo信息
	public List<DataPackageInfo> getPackages(String userId) {
		return dpMapper.selectPackageByUserId(userId);
	}

	// 根据packageId查询包的信息，包括package包含的表名，列名等信息
	public DataPackageInfoRes getDataPackageInfo(String dataPackageId) {
		return dpMapper.selectPackageInfoByPackageId(dataPackageId);
	}

	public String addDataPackage(DataPackageReq dpr) throws ConnException, SQLException {
		// 需要保存的data_package_table集合
		List<DataPackageTableInfo> dptList = new ArrayList<>();
		// 需要保存的data_package_column集合
		List<DataPackageColumnInfo> dpcList = new ArrayList<>();
		// 保存批量执行的建表语句
		List<String> allCreateSqls = new ArrayList<>();
		// 需要保存的示例数据集合
		List<SumaryDataEntity> sumaryDataList = new ArrayList<>();
		// String samyrySql = "select ";
		// 创建保存对象dataPAckageInfo
		DataPackageInfo dp = new DataPackageInfo();
		String dpid = UUID.randomUUID().toString().substring(0, 32);
		dp.setId(dpid);
		dp.setUid(dpr.getUserId());
		dp.setIsDel(0);
		dp.setTitle(dpr.getTitle());
		List<DbAndTableInfo> dbAndTableInfoList = dpr.getDbAndTableInfo();
		for (DbAndTableInfo dbAndTableInfo : dbAndTableInfoList) {
			DatasourceInfo dbInfo = dsMapper.selectOne(dbAndTableInfo.getDatasourceId());
			if (dbInfo == null) {
				throw new ConnException("错误的数据源信息");
			}
			List<TableInfoReq> tbInfos = dbAndTableInfo.getTableInfo();
			// 遍历所有table
			for (TableInfoReq tbInfo : tbInfos) {
				// 为了避免平台建表重复，表名加上uuid字段
				String uuid = UUID.randomUUID().toString().substring(0, 32);
				// 示例数据表表表名
				String sumaryTableName = "sumary_" + tbInfo.getTableSource() + "_" + uuid;
				// 获取建表sql
				String sql = DbDao.getDao(dbInfo.getDbType()).getCreateTableDDL(dbInfo, tbInfo.getTableSource());
				// 重组sql
				String createSumaryTableSql = "CREATE TABLE `" + sumaryTableName + "`" + sql + ";";
				System.out.println(createSumaryTableSql);
				// 将sql拼接在一起，最后一起执行
				allCreateSqls.add(createSumaryTableSql);
				// 创建需要保存的dataPackageTableInfo
				DataPackageTableInfo dpt = new DataPackageTableInfo();
				// 为实体设置id
				String dptid = UUID.randomUUID().toString().substring(0, 32);
				dpt.setId(dptid);
				dpt.setDpid(dpid);
				dpt.setTableSource(tbInfo.getTableSource());
				dpt.setTableName(tbInfo.getTableName());
				dpt.setTargetTable(tbInfo.getTargetTable());
				dpt.setSumaryTable(sumaryTableName);
				dptList.add(dpt);
				// 遍历每一个table的所有列，并创建需要保存的实体DataPacageColumnInfo
				List<ColumnInfoReq> columnInfos = tbInfo.getColumns();
				// 用来保存需要获取的字段的列
				List<String> columns = new ArrayList<>();
				for (ColumnInfoReq tableFieldInfo : columnInfos) {
					DataPackageColumnInfo dpc = new DataPackageColumnInfo();
					dpc.setId(UUID.randomUUID().toString().substring(0, 32));
					// 设置DataPacageColumnInfo对象的dptid为当前datapakagetableInfo的id
					dpc.setDptid(dptid);
					dpc.setColumnName(tableFieldInfo.getColumnName());
					dpc.setColumnSource(tableFieldInfo.getColumnSource());
					dpc.setColumnType(tableFieldInfo.getColumnType());
					dpcList.add(dpc);
					columns.add(tableFieldInfo.getColumnSource());
					// samyrySql += "`" + tableFieldInfo.getColumnName() + "` ";
				}
				// samyrySql += " from " + tbInfo.getTableSource() + "limit 10";
				// 从提供方数据库抽取示例数据
				SumaryDataEntity sde = DbDao.getDao(dbInfo.getDbType()).getSumaryData(dbInfo, tbInfo.getTableSource(),
						columns);
				// 设置平台上示例数据表的名字，以便保存
				sde.setSumaryTable(sumaryTableName);
				sumaryDataList.add(sde);
			}
		}

		// 所有操作执行完毕，开始对数据库操作，建表及保存数据
		createTableAddSave(allCreateSqls, dp, dptList, dpcList, "mysql", sumaryDataList);
		return dpid;
	}

	// 保存数据包操作，包括建表和保存数据
	@Transactional
	private void createTableAddSave(List<String> allCreateSqls, DataPackageInfo dp, List<DataPackageTableInfo> dptList,
			List<DataPackageColumnInfo> dpcList, String dbType, List<SumaryDataEntity> sumaryDataList)
			throws ConnException {
		// 在平台建表
		DbDao dao = DbDao.getDao("mysql");
		dao.createTable(allCreateSqls);
		// 保存示例数据
		dao.insertIntoDataPlat(sumaryDataList);
		// 保存dataPackage
		dpMapper.insert(dp);
		// 保存dataPackageTable
		for (DataPackageTableInfo dpt : dptList) {
			dptMapper.insert(dpt);
		}
		// 保存dataPackageColumn
		for (DataPackageColumnInfo dpc : dpcList) {
			dpcMapper.insert(dpc);
		}
	}

	// 删除数据包，实际上只是改变了一下数据包的状态，实际数据未删除
	public void deletePackageChangeIsDel(String dataPackageId) {
		dpMapper.deleteByChangeIsDel(dataPackageId);
	}

	// 删除数据包，包括建立的表等，已被修改状态来删除的方法代替,测试时为了清除数据库添加的测试数据会有使用该方法
	@Transactional
	public void deletePackage(String dataPackageId) {
		List<DataPackageTableInfo> dpts = dptMapper.selectByDataPackageId(dataPackageId);
		// 删除数据包在平台建立的表
		for (DataPackageTableInfo dpt : dpts) {
			dpMapper.deleteTable(dpt.getTargetTable());
			dpMapper.deleteTable(dpt.getSumaryTable());
		}
		// 根据dptid删除dataPackage
		dpcMapper.deleteByDataPackageId(dataPackageId);
		// 根据dpid删除dataPackageTable
		dptMapper.deleteByDataPackageId(dataPackageId);
		// 根据dpid删除dataPackage
		dpMapper.deleteById(dataPackageId);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////// 未使用到的方法，暂不删/////////////////////////////////////////////////////////////////
	///////////////////////////// 生成sumarytable的时候从平台targettable抽取数据////////////////////////////////////////
//	public String addDataPsackage(DataPackageReq dpr) throws ConnException, SQLException {
//		// 保存sql语句
//		List<String> allCreateSqls = new ArrayList<>();
//		// 创建需要保存的实体类
//		DataPackageInfo dp = new DataPackageInfo();
//		List<DataPackageTableInfo> dptList = new ArrayList<>();
//		List<DataPackageColumnInfo> dpcList = new ArrayList<>();
//		String dpid = UUID.randomUUID().toString().substring(0, 32);
//		dp.setId(dpid);
//		dp.setUid(dpr.getUserId());
//		dp.setIsDel(0);
//		dp.setTitle(dpr.getTitle());
//		List<TableInfoReq> tables = null;
//		dpr.getTableInfo();
//		for (TableInfoReq tbInfo : tables) {
//			DatasourceInfo dbInfo = new DatasourceInfo();
//			dbInfo.setUrl(this.ip);
//			dbInfo.setPort(Integer.parseInt(this.port));
//			dbInfo.setUsername(this.username);
//			dbInfo.setPwd(this.password);
//			dbInfo.setSchemaName(this.dbName);
//			// 建立sumaryTanle，先获取平台上面target表的建表语句，平台上只有mysql数据库,
//			String sql = DbDao.getDao("mysql").getCreateTableDDL(dbInfo, tbInfo.getTargetTable());
//			String sumaryTableName = UUID.randomUUID().toString().substring(0, 32) + "_sumary_"
//					+ tbInfo.getTableSource();
//			if (sumaryTableName.length() > 128)
//				sumaryTableName = sumaryTableName.substring(0, 32);
//			String createTargetTableSql = "CREATE TABLE `" + sumaryTableName + "`" + sql + ";";
//			// 将sql拼接在一起，最后一起执行
//			allCreateSqls.add(createTargetTableSql);
//			// 添加示例数据语句
//			allCreateSqls.add("insert into `" + sumaryTableName + "` (select * from `" + tbInfo.getTargetTable()
//					+ "` limit 10);");
//			// 创建需要保存的实体类
//			DataPackageTableInfo dpt = new DataPackageTableInfo();
//			String dptid = UUID.randomUUID().toString().substring(0, 32);
//			dpt.setId(dptid);
//			dpt.setDpid(dpid);
//			dpt.setTableSource(tbInfo.getTableSource());
//			dpt.setTableName(tbInfo.getTableName());
//			dpt.setTargetTable(tbInfo.getTargetTable());
//			dpt.setSumaryTable(sumaryTableName);
//			dptList.add(dpt);
//			List<ColumnInfoReq> columns = tbInfo.getColumns();
//			for (ColumnInfoReq column : columns) {
//				DataPackageColumnInfo dpc = new DataPackageColumnInfo();
//				dpc.setId(UUID.randomUUID().toString().substring(0, 32));
//				dpc.setDptid(dptid);
//				dpc.setColumnName(column.getColumnName());
//				dpc.setColumnSource(column.getColumnSource());
//				dpc.setColumnType(column.getColumnType());
//				dpcList.add(dpc);
//			}
//		}
//		createTableAndSave(allCreateSqls, dp, dptList, dpcList);
//		return dpid;
//	}
//
//	// 保存数据包操作，包括建表和保存数据
//	@Transactional
//	private void createTableAndSave(List<String> allCreateSqls, DataPackageInfo dp, List<DataPackageTableInfo> dptList,
//			List<DataPackageColumnInfo> dpcList) throws ConnException {
//		// 在平台批量执行sql，包括建表和插入部分示例数据
//		DbDao dao = DbDao.getDao("mysql");
//		dao.createTable(allCreateSqls);
//		// 保存dataPackage
//		dpMapper.insert(dp);
//		// 保存dataPackageTable
//		for (DataPackageTableInfo dpt : dptList) {
//			dptMapper.insert(dpt);
//		}
//		// 保存dataPackageColumn
//		for (DataPackageColumnInfo dpc : dpcList) {
//			dpcMapper.insert(dpc);
//		}
//	}
//
//
//	// 拼接sql语句
//	private String getSql(TableInfo tbInfo) {
//		// sql用来拼接sql语句
//		StringBuffer sql = new StringBuffer();
//		sql.append(" (");
//		List<TableFieldInfo> fieldInfos = tbInfo.getTableFieldInfos();
//		for (TableFieldInfo tf : fieldInfos) {
//			String key = tf.getColumnKey().equals("PRI") ? "PRIMARY KEY" : "";
//			String extra = tf.getExtra();
//			String columnDefault = "DEFAULT '" + tf.getColumnDefault() + "'";
//			String isNullAble = tf.getIsNullAble().equals("YES") ? "NULL" : "NOT NULL";
//			// 判断该属性书否为主键，如果是主键，则令extra和default都为"";
//			if (!key.equals("")) {
//				extra = "";
//				columnDefault = "";
//			}
//			sql.append("`" + tf.getColumnName() + "`" + " " + tf.getColumnType() + " ");
//			sql.append(isNullAble + " " + key + " ");
//			sql.append(columnDefault + " " + extra + " ");
//			if (!tf.getColumnComment().equals(""))
//				sql.append("COMMENT '" + tf.getColumnComment() + "'");
//			sql.append(",");
//		}
//		// 去掉最后一个逗号
//		String sqlStr = sql.toString().substring(0, sql.length() - 1);
//		sqlStr += ")COMMENT ='" + tbInfo.getTableName() + "'";
//		return sqlStr;
//	}
//
//	private String getCreateTableSql(List<TableInfo> tbInfos) {
//		// sbsql用来存放所有需要执行的语句sql
//		StringBuffer sbsql = new StringBuffer();
//		for (TableInfo tbInfo : tbInfos) {
//			// sql用来拼接sql语句
//			StringBuffer sql = new StringBuffer();
//			String tableName = tbInfo.getTableSource();
//			sql.append("`" + tableName + "`" + " (");
//			List<TableFieldInfo> fieldInfos = tbInfo.getTableFieldInfos();
//			for (TableFieldInfo tf : fieldInfos) {
//				String key = tf.getColumnKey().equals("PRI") ? "PRIMARY KEY" : "";
//				String extra = tf.getExtra();
//				String columnDefault = "DEFAULT '" + tf.getColumnDefault() + "'";
//				String isNullAble = tf.getIsNullAble().equals("YES") ? "NULL" : "NOT NULL";
//				// 判断该属性书否为主键，如果是主键，则令extra和default都为"";
//				if (!key.equals("")) {
//					extra = "";
//					columnDefault = "";
//				}
//				sql.append("`" + tf.getColumnName() + "`" + " " + tf.getColumnType() + " ");
//				sql.append(isNullAble + " " + key + " ");
//				sql.append(columnDefault + " " + extra + " ");
//				if (!tf.getColumnComment().equals(""))
//					sql.append("COMMENT '" + tf.getColumnComment() + "'");
//				sql.append(",");
//			}
//			// 去掉最后一个逗号
//			String sqlStr = sql.toString().substring(0, sql.length() - 1);
//			sqlStr += ")COMMENT ='" + tbInfo.getTableName() + "';";
//			sbsql.append(sqlStr);
//		}
//		return sbsql.toString();
//	}
//
//	@Transactional
//	public String update(DataPackageReq dpr, DatasourceInfo dsInfo) throws ConnException, SQLException {
//		deletePackage(dpr.getPackageId());
//		String dpid = addDataPackage(dpr, dsInfo);
//		return dpid;
//	}

}
