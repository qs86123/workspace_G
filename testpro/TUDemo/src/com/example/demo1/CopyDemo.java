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
			fos.write(b,0,len);//����Ҳ����ֱ��׷�����ļ�ĩβд��
			System.out.println("��"+i+++"��д�볤��Ϊ��"+len);
			fos.flush();
		}
		fis.close();
		fos.close();
		System.out.println("�ļ��������");
	}

}
