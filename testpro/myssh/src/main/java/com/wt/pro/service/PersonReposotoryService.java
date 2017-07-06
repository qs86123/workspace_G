package com.wt.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.pro.entity.Person;
import com.wt.pro.repositories.PersonRepository;

@Service
public class PersonReposotoryService {

	@Autowired
	private PersonRepository dao;

	@Transactional
	public void savePerson(Person p1, Person p2) {
		dao.save(p1);
//		int i = 1 / 0;
		dao.save(p2);
	}

	@Transactional
	public void changePerson() {
		dao.changeOne("wt1", "wtt1");
//		int i = 1 / 0;
		dao.changeOne("wt2", "wtt2");
	}
	
	@Transactional
	public void deleteOne(String name){
		System.out.println("deleteoneService");
		dao.deleteOne(name);
	}
	
	public Person findByName(String name){
		return dao.findqqqq(name);
	}
}
