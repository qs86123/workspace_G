package com.wt.demo.visitor;

public class Fail extends Action {

	@Override
	public String getManConclusion(Man p) {
		return "男人失败时状态";
	}

	@Override
	public String getWomanConclusion(Woman p) {
		return "女人失败时状态";
	}

}
