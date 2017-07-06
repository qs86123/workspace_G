package com.wt.demo.commend;

public class BakeMuttonCommend extends Commend {

	public BakeMuttonCommend(Barbecuer barbecuer) {
		super(barbecuer);
	}

	@Override
	public void execute() {
		super.getBarbecuer().bakeMutton();
	}

}
