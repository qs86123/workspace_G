package com.wt.demo.visitor.two;

public class ElementB extends Element{

	@Override
	public void accept(Visitor visitor) {
		visitor.visitElementB(this);
	}

}
