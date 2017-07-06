package com.wt.springboot.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wt.springboot.demo.interceptor.MyInterceptor;
import com.wt.springboot.demo.interceptor.MyInterceptor2;

@Configuration
public class MyWebMVCConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
//		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/test", "/test1");
//		registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/test");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 默认是static文件夹，如果加了这个之后，staic文件夹的就访问不到了，只能访问到webpage文件夹的
		// registry.addResourceHandler("/**").addResourceLocations("classpath:/webpage/");
		super.addResourceHandlers(registry);
	}

}
