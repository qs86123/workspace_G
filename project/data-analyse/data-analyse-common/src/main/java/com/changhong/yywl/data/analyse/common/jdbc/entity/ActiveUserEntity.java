package com.changhong.yywl.data.analyse.common.jdbc.entity;

import java.util.List;

/**
 * 用作返回json数据data的实体类
 * @author wangtao
 * @date 2016年9月8日下午3:34:31
 */
public class ActiveUserEntity {

	private Object timeList;
	private Integer status;
	private List<ItemData> itemData;

	public Object getTimeList() {
		return timeList;
	}

	public void setTimeList(Object timeList) {
		this.timeList = timeList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ItemData> getItemData() {
		return itemData;
	}

	public void setItemData(List<ItemData> itemData) {
		this.itemData = itemData;
	}

}
