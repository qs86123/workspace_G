package com.wt.demo.commend;

public class BakeChickenCommend extends Commend {

	public BakeChickenCommend(Barbecuer barbecuer) {
		super(barbecuer);
	}

	@Override
	public void execute() {
		super.getBarbecuer().bakeChicken();
	}

}
