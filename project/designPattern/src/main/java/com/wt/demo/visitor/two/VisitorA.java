package com.wt.demo.visitor.two;

public class VisitorA extends Visitor {

	@Override
	public void visitElementA(ElementA a) {
		System.out.println("ElementA被visitorA访问了");
	}

	@Override
	public void visitElementB(ElementB b) {
		System.out.println("ElementB被visitorA访问了");
	}

}
