package com.wt.demo.digui;

import org.junit.Test;

public class TriangleData {

	static int size;
	static int count;
	static char arrChar[];

	// 三角数字测试
	@Test
	public void triangleTest() {
		System.out.println(triangle(4));
	}

	// 变位字测试
	@Test
	public void doAnagramTest() {
		String abcd = "abcd";
		size = abcd.length();
		count = 0;
		arrChar = new char[] { 'a', 'b', 'c', 'd' };
		doAnagram(size);
	}

	// 二分查找测试
	public static void erfenchazhaoTest(String[] args) {
		int[] datas = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20 };
		// 普通二分查找
		String str = find(20, datas);
		System.out.println(str);
		// 递归二分查找
		String str2 = refind(19, datas, 0, datas.length);
		System.out.println(str2);
	}

	// 汉诺塔测试
	@Test
	public void towerAppTest() {
		towerApp(3, "A", "B", "C");
	}

	// 归并排序测试
	@Test
	public void guibiinPaixuTest() {
		int[] a = new int[] { 2, 3, 5, 7, 9, 12, 14 };
		int[] b = new int[] { 0, 1, 6, 8, 10 };
		for (int i : guibinPaixu(a, b))
			System.out.print(i + " ");
	}

	////////////////////////////// 上边测试，下边方法//////////////////////////////////////
	// 三角数字
	private static int triangle(int n) {
		if (n == 1)
			return 1;
		return n + triangle(n - 1);
	}

	// 变位字
	private static void doAnagram(int newSize) {
		if (newSize == 1)
			return;
		for (int i = 0; i < newSize; i++) {
			doAnagram(newSize - 1);
			if (newSize == 2)
				displayword();
			rotate(newSize);
		}
	}

	private static void displayword() {
		if (count < 99)
			System.out.print(" ");
		if (count < 9)
			System.out.print(" ");
		System.out.print(++count + "");
		for (int i = 0; i < size; i++)
			System.out.print(arrChar[i]);
		System.out.print("      ");
		System.out.flush();
		if (count % 6 == 0)
			System.out.println("");
	}

	private static void rotate(int newSize) {
		int j;
		int position = size - newSize;
		char temp = arrChar[position];
		for (j = position + 1; j < size; j++)
			arrChar[j - 1] = arrChar[j];
		arrChar[j - 1] = temp;
		// System.out.println("newSize=" + newSize + ":" + arrChar[0] +
		// arrChar[1] + arrChar[2] + arrChar[3]);
	}

	// 二分查找
	private static String find(int serchKey, int[] datas) {
		int lower = 0;
		int upper = datas.length - 1;
		int curIn;
		while (true) {
			curIn = (lower + upper) / 2;
			if (datas[curIn] == serchKey)
				return "找到数据：datas[" + curIn + "]=" + datas[curIn];
			else if (lower > upper)
				return "没有找到数据：" + serchKey;
			else {
				if (datas[curIn] > serchKey)
					upper = curIn - 1;
				else
					lower = curIn + 1;
			}
		}
	}

	// 递归二分查找
	public static String refind(int searchkey, int[] datas, int lower, int upper) {
		int curIn = (lower + upper) / 2;
		if (datas[curIn] == searchkey)
			return "找到数据：datas[" + curIn + "]=" + datas[curIn];
		if (lower > upper)
			return "未找到数据：" + searchkey;
		if (datas[curIn] > searchkey)
			upper = curIn - 1;
		else
			lower = curIn + 1;
		return refind(searchkey, datas, lower, upper);
	}

	// 汉诺塔
	public static void towerApp(int topN, String from, String inner, String to) {
		if (topN == 1) {
			count++;
			System.out.println("第" + count + "步：disk 1 from " + from + " to " + to);
		} else {
			towerApp(topN - 1, from, to, inner);
			count++;
			System.out.println("第" + count + "步：disk " + topN + " from " + from + " to " + to);
			towerApp(topN - 1, inner, from, to);
		}
	}

	// 归并排序
	public static int[] guibinPaixu(int[] a, int[] b) {
		int aSize = a.length;
		int bSize = b.length;
		int curA = 0, curB = 0, curC = 0;
		int[] c = new int[aSize + bSize];
		while (curA < aSize && curB < bSize) {
			if (a[curA] < b[curB])
				c[curC++] = a[curA++];
			else
				c[curC++] = b[curB++];
		}
		while (curB < bSize)
			c[curC++] = b[curB++];
		while (curA < aSize)
			c[curC++] = a[curA++];
		return c;
	}

	
	
	
}
