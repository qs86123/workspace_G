package com.changhong.yywl.data.analyse.common;

public class Test {

	public static void main(String[] args) {
		Integer maxData = 0;
		int i = maxData % 100;
		if (i > 0)
			maxData = (maxData / 100) * 100 + 100;
		System.out.println(maxData);
	}
	
}
