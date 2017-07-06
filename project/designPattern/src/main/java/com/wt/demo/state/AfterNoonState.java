package com.wt.demo.state;

public class AfterNoonState extends IState {

	@Override
	public void doWork(Work w) {
		if (w.getHour() < 17) {
			System.out.println("下午上班状态,hour="+w.getHour());
		} else {
			w.setState(new EveningState());
			w.dosth();
		}

	}

}
