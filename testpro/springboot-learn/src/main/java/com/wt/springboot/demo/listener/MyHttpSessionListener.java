package com.wt.springboot.demo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session创建");
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session创建");
	}
}
