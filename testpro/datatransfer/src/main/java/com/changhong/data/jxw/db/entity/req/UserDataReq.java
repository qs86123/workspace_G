package com.changhong.data.jxw.db.entity.req;

/**
 * 用户获取自己数据源数据请求实体
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月15日上午10:30:48
 */
public class UserDataReq {

	// 用户id
	private String userId;
	// 用户数据源id
	private String datasourceId;
	// 查询的表名
	private String tableSource;
	// 每页数据条数,默认20
	private int pageSize = 20;
	// 第几页
	private int pageNum = 0;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
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

}
