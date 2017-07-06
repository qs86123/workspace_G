package com.wt.demo.adapter;

/**
 * 适配器模式，把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 * 
 * 用电器做例子，笔记本电脑的插头一般都是三相的，即除了阳极、阴极外，还有一个地极。而有些地方的电源插座却只有两极，没有地极。
 * 电源插座与笔记本电脑的电源插头不匹配使得笔记本电脑无法使用。 这时候一个三相到两相的转换器（适配器）就能解决此问题，而这正像是本模式所做的事情。
 * 这里AdapteeAdapter就相当于转换器
 * 
 * 该方式有点类似于将adaptee的子类实现target接口，效果一样，可以这样理解
 * 
 * 适配器模式的类适配模式就是子类实现target的方式，这里这种属于对象适配模式
 * 
 * @author wangtao
 * @date 2017年4月14日上午10:26:42
 */
public class Test {
	public static void main(String[] args) {
		AdapteeAdapter aa = new AdapteeAdapter();
		aa.methodCusumerApi1();
		aa.methodCusumerApi();
	}
}
