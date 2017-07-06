package com.wt.demo.digui;

import java.util.Random;

/**
 * 
 * 归并排序
 * 
 * @author wangtao
 * @date 2017年4月24日上午10:55:11
 */
public class DArray {

	private long[] theArray;
	private int nElems;

	public DArray(int max) {
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value) {
		theArray[nElems++] = value;
	}

	public void display() {
		int n = 1;
		for (long l : theArray) {
			System.out.print(l + "  ");
			if (n++ % 10 == 0) {// 每输出6个换一次行
				System.out.println("");
			}
		}
		System.out.println("");
	}

	public void mergeSort() {
		long[] workSpace = new long[nElems];
		recMergeSort(workSpace, 0, nElems - 1);
	}

	private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
		if (lowerBound == upperBound)
			return;
		int mid = (lowerBound + upperBound) / 2;
		System.out.println(lowerBound + "-" + upperBound);
		recMergeSort(workSpace, lowerBound, mid);
		recMergeSort(workSpace, mid + 1, upperBound);
		merge(workSpace, lowerBound, mid + 1, upperBound);
	}

	private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0;
		int lowerBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowerBound + 1;
		while (lowPtr <= mid && highPtr <= upperBound)
			if (theArray[lowPtr] < theArray[highPtr])
				workSpace[j++] = theArray[lowPtr++];
			else
				workSpace[j++] = theArray[highPtr++];
		while (lowPtr <= mid)
			workSpace[j++] = theArray[lowPtr++];
		while (highPtr <= upperBound)
			workSpace[j++] = theArray[highPtr++];
		for (j = 0; j < n; j++)
			theArray[lowerBound + j] = workSpace[j];
	}

	public static void main(String[] args) {
		DArray arr = new DArray(100);
		for (int i = 0; i < 50; i++)
			arr.insert(new Random().nextInt(1000));
		System.out.println("---------------------");
		arr.display();
		System.out.println("---------------------");
		long start = System.currentTimeMillis();
		arr.mergeSort();
		long time = System.currentTimeMillis() - start;
		arr.display();
		System.out.println("排序用时：" + time+"ms");
	}
}
