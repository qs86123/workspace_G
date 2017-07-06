package com.wt.springboot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.springboot.demo.entity.JPAPerson;
import com.wt.springboot.demo.jpadao.JPAPersonDao;

@Service
public class JpaPersonService {

	@Autowired
	private JPAPersonDao personDao;

	public JPAPerson findByName(String name) {
		return personDao.findByName(name);
	}

	public String saveOrUpdate(JPAPerson entity) {
		JPAPerson jp = personDao.save(entity);
		return jp.getId();
	}

}
