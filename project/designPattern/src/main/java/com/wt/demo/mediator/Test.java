package com.wt.demo.mediator;

/**
 * 中介者模式，有点类似于A要想访问B的方法，通知中介者去访问，自己不访问
 * 
 * @author wangtao
 * @date 2017年5月8日下午12:58:49
 */
public class Test {
	public static void main(String[] args) {
		UnitedMediator mediator = new UnitedMediator();
		CountryA a = new CountryA("A", mediator);
		CountryB b = new CountryB("B", mediator);
		System.out.println(a.declear("aaaaaaaa", b));
		System.out.println(b.declear("bbbbbbbb", a));
	}
}
