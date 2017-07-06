package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		InetAddress ip;
		ip=InetAddress.getLocalHost();
		String hostName=ip.getHostName();
		String localip=ip.getHostAddress();
		System.out.println("hostName:"+hostName+"\n"+"localip:"+localip);
		
	}

}
