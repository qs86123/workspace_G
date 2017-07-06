package com.wt.demo.absfactory.two;

public class SqlUser implements IUrid{

	@Override
	public void insert() {
		System.out.println("sql insert user");
	}

	@Override
	public IUrid create() {
		return this;
	}

}
