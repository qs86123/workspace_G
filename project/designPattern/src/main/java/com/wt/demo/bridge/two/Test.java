package com.wt.demo.bridge.two;

/**
 * 桥接模式
 * @author wangtao
 * @date 2017年5月4日下午2:39:55
 */
public class Test {
	public static void main(String[] args) {
		ISoft softA = new SoftA();
		ISoft softB = new SoftB();
		IPhone one = new PhoneOne();
		IPhone two = new PhoneTwo();
		String str = "";
		str = one.phoneRunSoft(softA);
		System.out.println(str);
		str = one.phoneRunSoft(softB);
		System.out.println(str);
		str = two.phoneRunSoft(softA);
		System.out.println(str);
		str = two.phoneRunSoft(softB);
		System.out.println(str);
	}
}
