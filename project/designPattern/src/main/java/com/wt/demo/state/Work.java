package com.wt.demo.state;

public class Work {
	private IState state;

	private int hour;
	private boolean isfinish;

	public Work() {
		//初始化为早上工作状态
		state = new MorningState();
	}

	public void dosth() {
		state.doWork(this);
	}

	public int getHour() {
		return hour;
	}

	public boolean isIsfinish() {
		return isfinish;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setIsfinish(boolean isfinish) {
		this.isfinish = isfinish;
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}

}
