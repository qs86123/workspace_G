package com.wt.demo.visitor.two;

public class ElementA extends Element {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitElementA(this);
	}

}
