package com.wt.demo.state;

public class MorningState extends IState {

	@Override
	public void doWork(Work w) {
		if (w.getHour() < 10) {
			System.out.println("早上上班状态,hour="+w.getHour());
		} else {
			w.setState(new NoonState());
			w.dosth();
		}
	}

}
