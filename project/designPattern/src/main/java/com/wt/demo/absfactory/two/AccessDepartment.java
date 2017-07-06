package com.wt.demo.absfactory.two;

public class AccessDepartment implements IUrid {

	@Override
	public void insert() {
		System.out.println("access insert department");
	}

	@Override
	public IUrid create() {
		return this;
	}

}
