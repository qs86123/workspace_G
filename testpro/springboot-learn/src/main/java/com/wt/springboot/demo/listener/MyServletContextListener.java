package com.wt.springboot.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@WebListener
public class MyServletContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContex初始化");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContex初始化");
	}
}
