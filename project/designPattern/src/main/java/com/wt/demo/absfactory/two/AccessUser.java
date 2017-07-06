package com.wt.demo.absfactory.two;

public class AccessUser implements IUrid {

	@Override
	public void insert() {
		System.out.println("access insert user");
	}

	@Override
	public IUrid create() {
		return this;
	}

}
