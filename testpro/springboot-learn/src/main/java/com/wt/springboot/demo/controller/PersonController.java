package com.wt.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.springboot.demo.entity.Person;
import com.wt.springboot.demo.mapper.PersonMapper;

@Controller
public class PersonController {

	@Autowired
	private PersonMapper personMapper;

	@RequestMapping("/getPersons")
	@ResponseBody
	public List<Person> getPersons() {
		return personMapper.findPersons();
	}

	@RequestMapping("/getPerson")
	@ResponseBody
	public Person getPersonByName() {
		return personMapper.findPersonByName("wangtao");
	}
}
