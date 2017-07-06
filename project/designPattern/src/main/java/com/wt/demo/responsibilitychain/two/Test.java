package com.wt.demo.responsibilitychain.two;

public class Test {
	public static void main(String[] args) {
		Request req1 = new Request("leader", "");
		Request req2 = new Request("projectManager", "");
		Request req3 = new Request("boss", "");
		// 责任链模式，把请求提交给处理能力最弱的那个人，如果她不能处理，依次往后提交责任，
		// 此种方式不能动态设置最小leader的下一个领导者是谁，请看包two里面的
		Leader l = new Leader();
		// 默认情况下处理请求123
		System.out.println(l.handleResult(req1));
		System.out.println(l.handleResult(req2));
		System.out.println(l.handleResult(req3));
		// 为leader设置下一任处理者之后
		System.out.println("--------------为leader设置下一任处理者为boss之后--------------------");
		l.setNextHandler(new Boss());
		System.out.println(l.handleResult(req1));
		System.out.println(l.handleResult(req2));
//		l.setNextHandler(null);
		System.out.println(l.handleResult(req3));
	}
}
