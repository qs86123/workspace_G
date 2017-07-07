package com.wt.authentication;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Test {
	public static void main(String[] args) {

		String source = "111111";
		String salt = "asdf";
		int hashIterations = 2;
		// 参数1：明文，原密码
		// 参数2：盐，用来加载原密码后边之后再进行加密，增加密码破解难度
		// 参数3：迭代次数（散列次数），比如散列两此次就是md5(md5(""))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		String password = md5Hash.toString();
		System.out.println("md5:" + password);

		// 参数1：使用的散列算法
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println("simpleHash:" + simpleHash);
	}
}
