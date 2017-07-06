package cn.itcast.ssm;

import cn.itcast.ssm.util.MD5;

public class Test {
	public static void main(String[] args) {
		System.out.println(new MD5().getMD5ofStr("111"));
	}
}
