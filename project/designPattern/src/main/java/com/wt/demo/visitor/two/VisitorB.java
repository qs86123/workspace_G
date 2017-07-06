package com.wt.demo.visitor.two;

public class VisitorB extends Visitor {

	@Override
	public void visitElementA(ElementA a) {
		System.out.println("ElementA被visitorB访问了");
	}

	@Override
	public void visitElementB(ElementB b) {
		System.out.println("ElementB被visitorB访问了");
	}

}
