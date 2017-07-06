package com.changhong.data.jxw.db.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取临时表数据的时候使用的实体，白喊表名和字段名信息
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:50:13
 */
public class SumaryTableInfo {

	private String tableSource;
	private String sumaryTable;
	// 字段名
	private List<String> columns = new ArrayList<>();

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}

	public String getSumaryTable() {
		return sumaryTable;
	}

	public void setSumaryTable(String sumaryTable) {
		this.sumaryTable = sumaryTable;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

}
