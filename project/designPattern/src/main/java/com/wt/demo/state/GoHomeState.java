package com.wt.demo.state;

public class GoHomeState extends IState {

	@Override
	public void doWork(Work w) {
		System.out.println("下班回家状态,hour="+w.getHour());
	}

}
