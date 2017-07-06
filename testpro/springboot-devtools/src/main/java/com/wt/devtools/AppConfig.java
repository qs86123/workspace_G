package com.wt.devtools;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wt.devtools.interceptor.MyInterceptor;
import com.wt.devtools.interceptor.MyInterceptor2;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/test","/test1");
		registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/test");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webpage/**").addResourceLocations("classpath:/webpage/");
		super.addResourceHandlers(registry);
	}
}
