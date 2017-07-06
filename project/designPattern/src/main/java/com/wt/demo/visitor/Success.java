package com.wt.demo.visitor;

public class Success extends Action {

	@Override
	public String getManConclusion(Man p) {
		return "男人成功时状态";
	}

	@Override
	public String getWomanConclusion(Woman p) {
		return "女人成功时状态";
	}

}
