package com.changhong.data.jxw.db.entity;

/**
 * 返回用户数据源数据的时候使用的实体
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:42:48
 */
public class DataEntity {

	private int pageNum;
	private int pageSize;
	private long totalCount;
	// 这里用的Object类型，实际上可以直接用JsonObject，查询的数据不管是mongo还是muysql的数据都已经被封装成List<Object>了
	private Object data;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
