package com.wt.demo.visitor;

public class Woman extends Person {

	@Override
	public String accept(Action visitor) {
		return visitor.getWomanConclusion(this);
	}

}
