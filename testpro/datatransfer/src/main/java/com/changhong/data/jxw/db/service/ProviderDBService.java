package com.changhong.data.jxw.db.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.changhong.data.jxw.db.dao.DbDao;
import com.changhong.data.jxw.db.entity.DataEntity;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;

/**
 *
 * 用户数据源表信息，结构信息，数据的获取service层
 *
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午10:01:08
 */
@Service
public class ProviderDBService {

	// 获取所有表名
	public String[] getTables(DatasourceInfo dbInfo) throws ConnException {
		String[] tables = null;
		tables = DbDao.getDao(dbInfo.getDbType()).getTables(dbInfo);
		return tables;
	}

	// 获取所有表结构信息
	public List<TableInfo> getTablesInfo(DatasourceInfo dbInfo) throws ConnException {
		List<TableInfo> list = DbDao.getDao(dbInfo.getDbType()).getTablesInfo(dbInfo);
		return list;
	}

	// 获取某张表的数据
	public DataEntity getDataFromTable(DatasourceInfo dbInfo, String tableName, int pageSize, int pageNum)
			throws ConnException {
		DataEntity result = null;
		try {
			DbDao dao = DbDao.getDao(dbInfo.getDbType());
			// 获取用户数据源的数据信息，pageNum从0开始
			result = dao.getData(dbInfo, tableName, pageSize, pageNum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConnException("读取表数据错误:" + e.getMessage());
		}
		return result;
	}

}
