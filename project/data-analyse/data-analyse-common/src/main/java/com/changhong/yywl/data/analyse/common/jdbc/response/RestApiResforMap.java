package com.changhong.yywl.data.analyse.common.jdbc.response;

import com.changhong.yywl.data.analyse.common.RestApiResponse;

public class RestApiResforMap extends RestApiResponse {

	// 某个省份最多的用户数量
	private Integer maxData;
	private Object countryData;
	private Object cityData;

	public Object getCountryData() {
		return countryData;
	}

	public Object getCityData() {
		return cityData;
	}

	public void setCountryData(Object countryData) {
		this.countryData = countryData;
	}

	public void setCityData(Object cityData) {
		this.cityData = cityData;
	}

	public Integer getMaxData() {
		return maxData;
	}

	public void setMaxData(Integer maxData) {
		this.maxData = maxData;
	}

}
