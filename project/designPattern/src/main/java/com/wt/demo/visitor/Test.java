package com.wt.demo.visitor;

/**
 * 
 * 访问者模式，用于分类比较确定，比如只有男人和女人两个种类，失败和成功两个状态，就可以使用访问者模式，Action中定义男人和女人状态的接口,
 * 定义某一种行为后的状态（比如成功，失败，恋爱）的类，实现Action接口，即实现男人和女人在不同状态下方法的行为，
 * 男人和女人类中有一个方法，方法中有一个Action，使用不同的状态的类，来执行该状态下的操作
 * 访问者模式适用于种类不会轻易改变的时候，比如男人和女人这两个种类不会改变。该模式在类的扩展下会产生大量的修改
 * 
 * @author wangtao
 * @date 2017年5月8日下午2:22:22
 */
public class Test {
	public static void main(String[] args) {
		Manager m = new Manager();
		m.addPerson(new Man());
		m.addPerson(new Woman());
		Success s = new Success();
		m.display(s);
		Fail f = new Fail();
		m.display(f);
		Love l = new Love();
		m.display(l);
	}
}
