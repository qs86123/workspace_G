package com.example.demo1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D:/test/a.jpg");
		FileOutputStream fos = new FileOutputStream("D:/test/b.jpg");
		int len=0;
		int i=1;
		byte[] b = new byte[1024];
		while ((len=fis.read(b)) > 0) {
			fos.write(b,0,len);//这样也可以直接追加在文件末尾写入
			System.out.println("第"+i+++"次写入长度为："+len);
			fos.flush();
		}
		fis.close();
		fos.close();
		System.out.println("文件复制完成");
	}

}
