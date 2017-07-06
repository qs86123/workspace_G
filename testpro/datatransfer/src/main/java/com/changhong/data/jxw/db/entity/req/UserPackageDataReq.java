package com.changhong.data.jxw.db.entity.req;

import java.util.List;

/**
 * 根据数据包，查询提供方数据的时使用的请求实体
 * 
 * @author wangtao
 * @date 2017年3月17日下午2:15:32
 */
public class UserPackageDataReq {

	// 数据库类型
	private String dbType;
	// 表名
	private String tableSource;
	// 列名
	private List<String> columns;
	// 每页数据量，默认20
	private int pageSize = 20;
	// 页数
	private int pageNum = 0;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

}
