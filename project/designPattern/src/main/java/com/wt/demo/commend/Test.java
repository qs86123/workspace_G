package com.wt.demo.commend;

public class Test {
	public static void main(String[] args) {
		Barbecuer b = new Barbecuer();
		BakeChickenCommend bcc = new BakeChickenCommend(b);
		BakeMuttonCommend bmc = new BakeMuttonCommend(b);
		Waiter w = new Waiter();
		w.addCommend(bcc);
		w.addCommend(bcc);
		w.addCommend(bcc);
		w.addCommend(bmc);
		w.nodify();
	}
}
