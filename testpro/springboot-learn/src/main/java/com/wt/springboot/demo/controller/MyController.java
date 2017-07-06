package com.wt.springboot.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.springboot.demo.entity.Person;

@Controller
public class MyController {

	/**
	 * @Controller 注解使用下，返回的字符串默认是一张页面的名字。
	 * @return
	 */
	@RequestMapping("/ab")
	public String ab() {
		return "index.html";
	}

	@RequestMapping("/errorPage")
	public String errorPage() {
		return "index.html";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		System.out.println("11111");
		System.out.println("22222");
		System.out.println("33333");
		return "springboot-devtools";
	}

	@RequestMapping("/test1")
	@ResponseBody
	public Person test1() {
		System.out.println("11111");
		Person p = new Person();
		p.setAge("23");
		p.setName("wt");
		return p;
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2(HttpServletRequest req) {
		System.out.println("11111");
		req.getSession();
		return "test2";
	}
}
