package com.wt.demo.a_lesson16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author wangtao
 * @date 2017年4月18日下午1:04:19
 */
public class Test {

	public static void main(String[] args) {
		String a = "a.v.c.b";
		String[] as = a.split("\\.");
		System.out.println(a.matches("[a-z].[a-z].[a-z].[a-z]"));
		a = a.replaceAll("\\.", "g");
		System.out.println(a);
		for (String s : as) {
			System.out.println(s);
		}
	}

	@org.junit.Test
	public void testMylist() {
		MyList<String> list = new MyList<String>();
		for (int i = 0; i < 23; i++) {
			list.add("str" + i);
		}
		System.out.println(list.get(20) + ":--------");
		for (int i = 0; i < list.size; i++) {
			System.out.println(list.get(i));
		}

		MyList<Cat> cats = new MyList<Cat>();
		for (int i = 0; i < 23; i++) {
			cats.add(new Cat(i + new Random().nextInt(20), "name" + i));
		}
		System.out.println(cats.get(20) + ":--------");
		for (int i = 0; i < cats.size(); i++) {
			System.out.println(cats.get(i));
		}
	}

	@org.junit.Test
	public void testIteretor() {
		List<String> list = new ArrayList<String>();
		list.add("q");
		list.add("w");
		list.add("e");
		list.add("r");
		list.add("t");
		list.add("y");
		Iterator<String> it = list.iterator();
		for (; it.hasNext();) {
			String str = it.next();
			System.out.println(str);
			list.remove(str);//
		}
		System.out.println(list.size());
	}

	public void t(String a) {
		a = "aaa";
		System.out.println(a);
	}

	@org.junit.Test
	public void erjinzhi() {
		int a = 0x1;
		System.out.println(a);
		int b = 0x8;
		System.out.println(b);
		int c = 0xFFFFFFFF;
		a <<= 2;
		System.out.println("a<<2=" + a);
		a >>= 2;
		System.out.println("a>>2=" + a);
		b >>>= 2;
		System.out.println("b>>>2=" + b);
		b <<= 2;
		System.out.println("b<<2=" + b);
		System.out.println(a & b);
		System.out.println(a ^ b);
		System.out.println(a | b);
		System.out.println(c);
		System.out.println(~c);
		System.out.println("abc\n".length());
		System.out.println("\u0070" + ":" + "\u0062");
		char n = 0x000f;
		int m = n + 1;
		System.out.println(n + ":" + m);
	}

	@org.junit.Test
	public void fanxingArray() {
		Fanxin<String> f = new Fanxin<String>("a");
		Fanxin ff = f;
		// 将f赋值给ff，ff没有指定泛型，所有f的泛型将被全部擦除，包括内部方法已经确定List<String>的String都会被擦除
		String t = (String) ff.getObj();
		for (String s : f.getList()) {
			System.out.println(s);
		}
		Fanxin<String>[] farr = new Fanxin[10];
		farr[0] = new Fanxin("gg");
		String l = farr[0].getObj();
		System.out.println(l);

		List<String>[] lsa = new ArrayList[10];
		List[] oa = lsa;
		List<Integer> li = new ArrayList<Integer>();
		li.add(3);
		oa[1] = li;
		Integer s = (Integer) oa[1].get(0);
		System.out.println(s.getClass() + ":" + oa[1].get(0).getClass());
	}

}

class People {
	String name;

	public People(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "people[" + name + "]";
	}
}

class Fanxin<T> {

	T obj;

	public Fanxin(T obj) {
		this.obj = obj;
	}

	public T getObj() {
		return obj;
	}

	public List<String> getList() {
		List<String> l = new ArrayList<String>();
		l.add("q");
		l.add("w");
		return l;
	}
}

class TT {
	static String str = "str";
	static {
		Thread t = new Thread() {
			public void run() {
				System.out.println("run");
				System.out.println("zi:"+str);
				TT.str = "run str";
				System.out.println("run over");
			};
		};
		t.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("main:"+TT.str);
	}
	
}