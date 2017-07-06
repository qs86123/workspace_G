package com.changhong.data.jxw.db;

import java.util.List;

import com.changhong.data.jxw.db.dao.MongoDao;
import com.changhong.data.jxw.db.entity.DatasourceInfo;
import com.changhong.data.jxw.db.entity.TableFieldInfo;
import com.changhong.data.jxw.db.entity.TableInfo;
import com.changhong.data.jxw.db.exception.ConnException;

public class Test {
	public static void mai2n(String[] args) {
		MongoDao dao = new MongoDao();
		DatasourceInfo dbInfo = new DatasourceInfo();
		dbInfo.setUrl("127.0.0.1");
		dbInfo.setPort(27517);
		dbInfo.setUsername("perfectuser");
		dbInfo.setPwd("perfectuser");
		dbInfo.setSchemaName("perfect");
		try {
			List<TableInfo> tablesInfo = dao.getTablesInfo(dbInfo);
			for (TableInfo tableInfo : tablesInfo) {
				System.out.println(tableInfo.getTableSource());
				for (TableFieldInfo tf : tableInfo.getTableFieldInfos()) {
					System.out.println(tf.getColumnName());
				}
				System.out.println("------------------------------");
			}
		} catch (ConnException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(1<<30);
	}
}
