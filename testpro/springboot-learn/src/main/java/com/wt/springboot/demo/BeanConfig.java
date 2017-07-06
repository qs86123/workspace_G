package com.wt.springboot.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wt.springboot.demo.filter.MyFilter;
import com.wt.springboot.demo.filter.MyFilter2;
import com.wt.springboot.demo.listener.MyHttpSessionListener;
import com.wt.springboot.demo.listener.MyServletContextListener;

@Configuration
public class BeanConfig {
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("myFilter");
		MyFilter myFilter = new MyFilter();
		registrationBean.setFilter(myFilter);
		registrationBean.setOrder(2);
		List<String> urlList = new ArrayList<String>();
		urlList.add("/*");
		registrationBean.setUrlPatterns(urlList);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean get2FilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("myFilter2");
		MyFilter2 myFilter = new MyFilter2();
		registrationBean.setFilter(myFilter);
		registrationBean.setOrder(1);
		List<String> urlList = new ArrayList<String>();
		urlList.add("/test");
		registrationBean.setUrlPatterns(urlList);
		return registrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<MyHttpSessionListener> getServletListenerRegistrationBean() {
		ServletListenerRegistrationBean<MyHttpSessionListener> registrationBean = new ServletListenerRegistrationBean<MyHttpSessionListener>();
		registrationBean.setListener(new MyHttpSessionListener());
		return registrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<MyServletContextListener> getServletContextListener() {
		ServletListenerRegistrationBean<MyServletContextListener> registrationBean = new ServletListenerRegistrationBean<MyServletContextListener>();
		registrationBean.setListener(new MyServletContextListener());
		return registrationBean;
	}
}
