package com.changhong.yywl.data.analyse.common.jdbc.entity;

/**
 * mapper文件返回实体对象，用于将时间和数据绑定起来
 * @author wangtao
 * @date 2016年9月7日下午6:49:26
 */
public class ActiveUserResultMap {

	private String time;
	private Integer macCnt;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getMacCnt() {
		return macCnt;
	}

	public void setMacCnt(Integer macCnt) {
		this.macCnt = macCnt;
	}

}
