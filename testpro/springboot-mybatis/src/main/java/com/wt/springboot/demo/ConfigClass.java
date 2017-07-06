package com.wt.springboot.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath:application-bean.xml" })
public class ConfigClass {

	public ConfigClass() {
		System.out.println("config");
	}

}
