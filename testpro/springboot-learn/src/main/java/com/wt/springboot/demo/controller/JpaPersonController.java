package com.wt.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.springboot.demo.entity.JPAPerson;
import com.wt.springboot.demo.service.JpaPersonService;

/**
 * 
 * Description: 
 * Company: changhong
 * @author wangtao
 * @date 2016年11月23日下午5:16:06
 */
@Controller
public class JpaPersonController {

	@Autowired
	private JpaPersonService jpService;

	@RequestMapping("/getPersonjpa")
	@ResponseBody
	public JPAPerson getPersonByName() {
		return jpService.findByName("wangtao");
	}

	@RequestMapping("/saveJpaPreson")
	@ResponseBody
	public String saveJpaPreson() {
		JPAPerson entity = new JPAPerson();
		// 保存数据的时候数据库中不存在这个id的数据，这个id不会被保存，保存数据使用自动生成的id
		// 如果设置的id不为null，则会先查询数据库有没有改id的字段，entity.setId(""),这样也会执行先查询
		// 如果没有设置id，或者直接设置成null，则不会执行查询，直接insert
		entity.setId(null);
		entity.setAge("23");
		entity.setName("wangtao");
		return jpService.saveOrUpdate(entity);
	}

	@RequestMapping("/updateJpaPreson/{id}")
	@ResponseBody
	public String updateJpaPreson(@PathVariable String id) {
		JPAPerson entity = new JPAPerson();
		// 数据库中存在该条记录，保存将直接更新记录
		entity.setId(id);
		entity.setAge("24");
		entity.setName("wangtao2");
		return jpService.saveOrUpdate(entity);
	}

}
