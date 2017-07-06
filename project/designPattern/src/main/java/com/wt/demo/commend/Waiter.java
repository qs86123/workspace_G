package com.wt.demo.commend;

import java.util.ArrayList;
import java.util.List;

public class Waiter {

	private List<Commend> commends;

	public Waiter() {
		commends = new ArrayList<>();
	}

	public void addCommend(Commend c) {
		this.commends.add(c);
	}

	public void nodify() {
		for (Commend commend : commends) {
			commend.execute();
		}
	}

}
