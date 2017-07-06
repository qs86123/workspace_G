package com.wt.demo.adapter;

/**
 * 源角色适配器，将角色接口转换成用户需要的另一类接口，即Target接口
 * 
 * @author wangtao
 * @date 2017年4月14日上午10:20:17
 */
public class AdapteeAdapter implements Target {

	Adaptee adaptee = new Adaptee();

	public void methodCusumerApi1() {
		System.out.println("----------这是适配器提供的方法，该方法会执行源角色自己的方法");
		adaptee.method1();
	}

	public void methodCusumerApi() {
		System.out.println("这是源角色适配器适配过后拥有的用户需要的接口api");
	}

}
