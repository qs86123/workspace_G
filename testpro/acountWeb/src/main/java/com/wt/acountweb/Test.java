package com.wt.acountweb;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		method(a, b);// 在执行完这个方法之后，使得最后的输出为a=100;b=200;
//		try {
//			methodTwo(a, b);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}// 在执行完这个方法之后，使得最后的输出为a=100;b=200;
//		methodThree(a, b);// 在执行完这个方法之后，使得最后的输出为a=100;b=200;
		System.out.println("a=" + a);
		System.out.println("b=" + b);
	}

	private static void method(Integer a, Integer b) {
		System.out.println("a=" + 100);
		System.out.println("b=" + 200);
		System.exit(0);
	}

//	public static void methodTwo(Integer a, Integer b) throws Exception {
//		Field field = a.getClass().getDeclaredField("value");
//		field.setAccessible(true);
//		field.set(a, 100);
//
//		Field fieldb = b.getClass().getDeclaredField("value");
//		fieldb.setAccessible(true);
//		fieldb.set(b, 200);
//	}

	public static void methodThree(int a, int b) {
		PrintStream ps = new PrintStream(System.out) {
			@Override
			public void println(String x) {
				if ("a=10".equals(x)) {
					x = "a=100";
				} else {
					x = "b=200";
				}
				super.println(x);
			}
		};
		System.setOut(ps);
	}
}
