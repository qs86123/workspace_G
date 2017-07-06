package com.wt.demo.visitor.two;

public class Test {
	public static void main(String[] args) {
		ElementManager em = new ElementManager();
		em.addElement(new ElementA());
		em.addElement(new ElementB());
		em.display(new VisitorA());
		em.display(new VisitorB());
		em.display(new VisitorC());
	}
}
