package com.wt.demo.state;

public class JiabanState extends IState {

	@Override
	public void doWork(Work w) {
		System.out.println("加班状态,hour="+w.getHour());
	}

}
