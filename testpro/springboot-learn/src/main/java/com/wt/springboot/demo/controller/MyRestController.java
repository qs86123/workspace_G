package com.wt.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	/**
	 * @RestController 注解使用，默认是返回体。相当于@ResponseBody
	 * @return
	 */
	@RequestMapping("/a")
	String hehe() {
		return "index.html";
	}

}
