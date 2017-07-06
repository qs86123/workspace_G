package com.wt.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.pro.entity.Person;
import com.wt.pro.service.PersonReposotoryService;

@Controller
public class TestController {

	@Autowired
	private PersonReposotoryService prs;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "test success";
	}
	
	@RequestMapping("/testjsp")
	public String testjsp(){
		return "../jsp/success.jsp";
	}
	
	@RequestMapping("/testhtml")
	public String testhtml(){
		return "success.html";
	}
	
	@RequestMapping("/testjpa")
	@ResponseBody
	public String testjpa(){
		try {
			prs.savePerson(new Person("wt1", "23", "nan"), new Person("wt2", "22", "nan"));
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/testjpa2")
	@ResponseBody
	public String testjpa2(){
		try {
			prs.changePerson();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/testjpa3")
	@ResponseBody
	public String testjpa3(){
		try {
			prs.deleteOne("wt1");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public Object find(){
		Person p=null;
		try {
			p=prs.findByName("wt1");
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return p;
	}
	
}
