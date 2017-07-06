package com.wt.devtools.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.devtools.entity.Abcd;
import com.wt.devtools.entity.Item;
import com.wt.devtools.entity.Person;

@Controller
public class MyController {

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
		Person p = new Person();
		p.setAge("23");
		p.setName("wt");
		return p;
	}

	@RequestMapping("/test3")
	@ResponseBody
	public Abcd<Map<String,Item>> test3() {
		Person p=new Person();
		p.setAge("23");
		p.setName("wt");
		Map<String,Item> map=new HashMap<String, Item>();
		Item i=new Item("item", 1);
		i.setP(p);
		map.put("haha", i);
		Abcd<Map<String,Item>> abcd=new Abcd<Map<String,Item>>("name",map);
		abcd.setP(p);
		return abcd;
	}
	
	
}
