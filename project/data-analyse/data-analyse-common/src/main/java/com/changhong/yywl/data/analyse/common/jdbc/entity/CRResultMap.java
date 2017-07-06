package com.changhong.yywl.data.analyse.common.jdbc.entity;

public class CRResultMap {

	private Integer buyerCnt;
	private Integer userCnt;
	private double cr;
	private String time;
	private String userType;
	private Integer soldCnt;
	private Integer viewCnt;

	public Integer getSoldCnt() {
		return soldCnt;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setSoldCnt(Integer soldCnt) {
		this.soldCnt = soldCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public double getCr() {
		return cr;
	}

	public void setCr(double cr) {
		this.cr = cr;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getBuyerCnt() {
		return buyerCnt;
	}

	public void setBuyerCnt(Integer buyerCnt) {
		this.buyerCnt = buyerCnt;
	}

	public Integer getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}

}
