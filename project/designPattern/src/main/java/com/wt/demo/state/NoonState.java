package com.wt.demo.state;

public class NoonState extends IState {

	@Override
	public void doWork(Work w) {
		if (w.getHour() < 12) {
			System.out.println("上午上班状态,hour="+w.getHour());
		} else {
			w.setState(new AfterNoonState());
			w.dosth();
		}

	}

}
