package com.changhong.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.pro.entity.User;
import com.changhong.pro.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;
	
	@RequestMapping("/abc")
	public String indexPage() {
		return "success";
	}

	@RequestMapping("/user/{id}")
	@ResponseBody
	public String returnStr(@PathVariable Integer id) {
		return "id=" + id;
	}

	@RequestMapping("/user/user")
	@ResponseBody
	public User returnJson() {
		return new User("测试name", "测试sex", 23);
	}

	@RequestMapping("/test")
	@ResponseBody
	public User test() {
		return us.findUserByName("wt1");
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public String test2() {
		try {
			us.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
}
