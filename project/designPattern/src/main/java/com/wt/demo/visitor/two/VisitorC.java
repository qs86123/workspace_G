package com.wt.demo.visitor.two;

public class VisitorC extends Visitor {

	@Override
	public void visitElementA(ElementA a) {
		System.out.println("ElementA被visitorC访问了");
	}

	@Override
	public void visitElementB(ElementB b) {
		System.out.println("ElementB被visitorC访问了");
	}

}
