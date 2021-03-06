package com.wt.springboot.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@WebFilter(value="/test")
//@Component
//@Order(1)
public class MyFilter2 implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter初始化2");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter过滤操作2");
		chain.doFilter(request, response);
		System.out.println("filter过滤操作完成2");
	}
	@Override
	public void destroy() {
		System.out.println("Filter销毁2");
	}
}
