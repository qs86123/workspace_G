package com.example.demo1;



public class demo1 {

	public static void main(String[] args) {
		final Aclass a = new Aclass() {

			@Override
			void getint(int i) {
				System.out.println(++i);
			}
		};
		System.out.println(a.i);
		final Aclass a2 = new Aclass() {

			@Override
			void getint(int i) {
				i += 100;
				System.out.println(i);
			}
		};
		System.out.println(a2.i);

		System.out.println("3的阶乘：" + getnum(3));
		System.out.println("1,2,3,5,8,13,.....第10个数为：" + getsum(10) + "");
		System.out.println("5的3次幂为：" + getmi(5, 3));
		interfacedemo("name");
		interfacedemo("newname");
	}

	public static void interfacedemo(final String rStr) {
		Aclass b = new Aclass() {

			@Override
			void getint(int i) {
				
			}
			
			@Override
			public void out(String name) {
				System.out.println(name);
			}

		};
		b.setOnInstence(new OnInstence() {

			@Override
			public boolean getstrfromotherclass(String str) {
				return str == rStr;
			}

			@Override
			public String getstr() {
				return rStr;
			}
		});
		b.getstrisright();
	}

	public static int getnum(int n) {
		if (n == 1)
			return n;
		else
			return n * getnum(n - 1);
	}

	public static int getsum(int n) {
		if (n == 1)
			return 1;
		else if (n == 2)
			return 2;
		else
			return getsum(n - 1) + getsum(n - 2);
	}

	public static int getmi(int x, int y) {// 求x的y次幂
		if (y == 0)
			return 1;
		else if (y == 1)
			return x;
		else
			return x * getmi(x, y - 1);
	}
}
