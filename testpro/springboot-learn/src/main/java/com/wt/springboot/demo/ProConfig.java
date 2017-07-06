package com.wt.springboot.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// 默认加载application.properties文件，这里使用注解指定资源文件名字
// 虽然这里指定了读取pro.properties文件，但是默认读取的文件也是会读取的，所以默认文件里面的内容也可以注入
@PropertySource("classpath:pro.properties")
public class ProConfig {

	@Value("${pro.my.name}")
	private String name;

	@Value("${pro.my.value}")
	private String value;

	@Value("${moren.config.name}")
	private String morenName;

	@Value("${moren.config.value}")
	private String morenValue;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getMorenName() {
		return morenName;
	}

	public String getMorenValue() {
		return morenValue;
	}

}
