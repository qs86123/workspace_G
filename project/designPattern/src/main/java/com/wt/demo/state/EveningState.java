package com.wt.demo.state;

public class EveningState extends IState {

	@Override
	public void doWork(Work w) {
		if (w.isIsfinish()) {
			w.setState(new GoHomeState());
			w.dosth();
		} else {
			if(w.getHour()<21)
			w.setState(new JiabanState());
			w.dosth();
		}

	}

}
