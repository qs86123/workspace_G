package com.wt.demo.visitor;

public class Man extends Person {

	@Override
	public String accept(Action visitor) {
		return visitor.getManConclusion(this);
	}

}
