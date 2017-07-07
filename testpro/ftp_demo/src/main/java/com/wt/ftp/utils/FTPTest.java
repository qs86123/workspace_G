package com.wt.ftp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

public class FTPTest {

	public static void main(String[] args) {
		test();
	}

	public static boolean test() {
		boolean result = false;
		FTPClient client = new FTPClient();
		try {
			client.connect("192.168.2.18", 21);
			client.login("wangtao", "root");
			// int reply = client.getReply();//卡死
			int reply = client.getReplyCode();// 不卡死
			System.out.println("reply:" + reply);
			System.out.println(FTPReply.isPositiveCompletion(reply));
			if (!FTPReply.isPositiveCompletion(reply)) {
				client.disconnect();// 判断如果不是主动连接，则关闭连接
				System.out.println("positive connect");
			}
			String aa = client.printWorkingDirectory();
			System.out.println("aa:" + aa);
			String path = "/www";
			String temppath = aa;
			if (!client.changeWorkingDirectory(aa + path)) {
				String[] dirs = path.split("/");
				for (String dir : dirs) {
					if (dir == null || dir.equals(""))
						continue;
					temppath += "/"+dir;
					if (!client.changeWorkingDirectory(temppath)) {
						if (!client.makeDirectory(temppath)) {
							return result;
						} else {
							client.changeWorkingDirectory(temppath);
						}
					}
				}
			}
			System.out.println(client.printWorkingDirectory());
			InputStream local=new FileInputStream("D:/test/1.jpg");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			//上传同名直接覆盖
			client.storeFile("kgk.jpg", local);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
