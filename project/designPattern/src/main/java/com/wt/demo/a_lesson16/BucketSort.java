package com.wt.demo.a_lesson16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {

	public static void bucketSort(DataWrap[] dw, int min, int max) {
		int arrayLenth = dw.length;
		DataWrap[] tmp = new DataWrap[arrayLenth];
		int[] buckets = new int[max - min];
		for (int i = 0; i < arrayLenth; i++)
			// 元素的值就是buckets的下标,buckets[k]=n就表示值为k的元素有n个，
			// 例如排序2，2，4，4，6,min=0,那么buckets为0，0，2，0，2，0，1
			buckets[dw[i].data - min]++;
		System.out.println(Arrays.toString(buckets));
		for (int j = 1; j < buckets.length; j++)
			// 每个元素在排序后位于数组的位置，例如排序2，2，4，4，6,那么buckets为0，0，2，2，4，4，5
			// 再举个简单的例子，比如排序1112345这7个数，buchets为0,3,1,1,1,1，分别表示0有0个，1有3ge，2有1个....
			// name元素3排第几？？？肯定是排在0个0，3个1和一个2之后，即3+1=4，排在第四，所以这次循环之后buckets的值就是对应每个下标所拍第几的位置
			buckets[j] = buckets[j] + buckets[j - 1];
		System.out.println(Arrays.toString(buckets));
		System.arraycopy(dw, 0, tmp, 0, arrayLenth);
		for (int k = 0; k < arrayLenth; k++) {
			// 通过buckets来确定元素tmp[k]排在第几位，就如上的排序来讲buckets为0，0，2，2，4，4，5
			dw[--buckets[tmp[k].data - min]] = tmp[k];
		}

//		List<DataWrap> list = new ArrayList<>();
//		for (int m = 0; m < buckets.length; m++) {
//			while (buckets[m]-- > 0) {
//				list.add(new DataWrap(m + min, ""));
//			}
//		}
	}

	public static void main(String[] args) {
		DataWrap[] data = new DataWrap[] { new DataWrap(9, ""), new DataWrap(6, ""), new DataWrap(10, ""),
				new DataWrap(8, ""), new DataWrap(5, "*"), new DataWrap(7, ""), new DataWrap(3, ""),
				new DataWrap(1, "*"), new DataWrap(5, ""), new DataWrap(2, "*"), new DataWrap(4, "*"), };
		bucketSort(data, 0, 11);
		System.out.println(Arrays.toString(data));
	}

}

class DataWrap {
	int data;
	String str;

	public DataWrap(int data, String str) {
		this.data = data;
		this.str = str;
	}

	@Override
	public String toString() {
		return data + str;
	}
}
