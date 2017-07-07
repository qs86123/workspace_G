package com.wt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.demo.entity.UserInfo;
import com.wt.demo.service.UserService;

@Controller
public class UserController {

	@Value("${pro.isread}")
	private String proIsRead;
	
	@Value("${application.isread}")
	private String applicationIsread;
	
	@Value("${application_dev.isread}")
	private String application_devIsread;
	
	@Value("${all.key}")
	private String allKey;
	
	@Value("${pro.app.key}")
	private String proAppKey;

	@Autowired
	private UserService us;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public UserInfo getUser(){
		return us.findByName("wt1");
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public void get(){
		System.out.println(proIsRead);
		System.out.println(applicationIsread);
		System.out.println(application_devIsread);
		System.out.println(allKey);
		System.out.println("proApp:"+proAppKey);
	}

}
