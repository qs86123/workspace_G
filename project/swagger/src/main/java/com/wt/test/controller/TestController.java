package com.wt.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wt.test.ex.MyException;
import com.wt.test.pojo.Person;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/myerror")
	@ResponseBody
	public String myError() throws Exception {
		try {
			@SuppressWarnings("unused")
			int i = 1 / 0;
		} catch (Exception e) {
			throw new MyException("myException:" + e.getMessage());
		}
		return "myerror";
	}

	@RequestMapping(value = "/getSth/{id}", method = RequestMethod.GET)
	public String getGet(@PathVariable String id) {
		System.out.println("id+getGet");
		return id + "getGet";
	}

	@RequestMapping(value = "/getSth1", method = RequestMethod.GET)
	public String getGet() {
		System.out.println("getGet");
		return "getGet";
	}

	@RequestMapping(value = "/getSth", method = RequestMethod.POST)
	public String getPost(@RequestParam String key) {
		return key + "getPost";
	}

	@RequestMapping(value = "/getSth", method = RequestMethod.DELETE)
	public Person getDelete(@RequestBody Person person) {
		return person;
	}

}
