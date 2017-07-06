package com.wt.demo.absfactory.two;

public class SqlDepartment implements IUrid{

	@Override
	public void insert() {
		System.out.println("sql insert department");
	}

	@Override
	public IUrid create() {
		return this;
	}

}
