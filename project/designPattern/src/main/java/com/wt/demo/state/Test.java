package com.wt.demo.state;

public class Test {
	public static void main(String[] args) {
		Work work = new Work();
		for (int i = 16; i < 22; i++) {
			work.setHour(i);
			work.setIsfinish(true);
			work.dosth();
		}
	}
}
