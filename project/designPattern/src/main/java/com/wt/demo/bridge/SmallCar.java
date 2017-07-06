package com.wt.demo.bridge;

public class SmallCar extends AbsCar {

	@Override
	public String runOnRoad(AbsRoad road) {
		return "smallcar 在"+road.roadType()+"上行驶";
	}

}
