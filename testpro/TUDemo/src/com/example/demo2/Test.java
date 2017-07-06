package com.example.demo2;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Testclass t=new Testclass();
		C c=t;
		A a=t;
		B b=t;
		System.out.println("c.funX()");
		c.funA();
		c.funB();
		c.funC();
		System.out.println("t.funX()");
		t.funA();
		t.funB();
		t.funC();
		System.out.println("a.funX()");
		a.funA();
		//a.funB();//报错
		
		Scanner sc=new Scanner(System.in);
		String in=sc.next();
		int num;
		try {
			num=Integer.parseInt(in);
			System.out.println(num);
		} catch (Exception e) {
			System.out.println("你输入的不是数字");
		}
		
	}

}
