package com.wt.demo.visitor;

public class Love extends Action {

	@Override
	public String getManConclusion(Man p) {
		return "男人恋爱时状态";
	}

	@Override
	public String getWomanConclusion(Woman p) {
		return "女人恋爱时状态";
	}
}
