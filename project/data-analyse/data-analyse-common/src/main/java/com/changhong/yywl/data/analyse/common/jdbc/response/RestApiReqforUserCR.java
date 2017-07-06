package com.changhong.yywl.data.analyse.common.jdbc.response;

import com.changhong.yywl.data.analyse.common.RestApiResponse;

public class RestApiReqforUserCR extends RestApiResponse {

	private Object data1;
	private Object data2;
	private Object data3;
	private Object data4;
	private Object legend;

	public Object getLegend() {
		return legend;
	}

	public void setLegend(Object legend) {
		this.legend = legend;
	}

	public Object getData1() {
		return data1;
	}

	public Object getData2() {
		return data2;
	}

	public Object getData3() {
		return data3;
	}

	public Object getData4() {
		return data4;
	}

	public void setData1(Object data1) {
		this.data1 = data1;
	}

	public void setData2(Object data2) {
		this.data2 = data2;
	}

	public void setData3(Object data3) {
		this.data3 = data3;
	}

	public void setData4(Object data4) {
		this.data4 = data4;
	}

}
