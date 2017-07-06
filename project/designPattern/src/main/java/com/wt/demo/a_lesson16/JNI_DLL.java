package com.wt.demo.a_lesson16;

import java.util.ArrayList;
import java.util.List;

public class JNI_DLL {

	public static void main(String[] args) {
		System.loadLibrary("libmycdll");
		JNI_DLL o = new JNI_DLL();
		// o.hehe();//此行用demo.dll
		System.out.println(o.hehe("笑一个：", new ArrayList<String>()));
		System.out.println(o.heheObj("笑一个："));
	}

	public native void hehe();

	public native String hehe(String str, List<String> list);

	public native Object heheObj(Object obj);

}
