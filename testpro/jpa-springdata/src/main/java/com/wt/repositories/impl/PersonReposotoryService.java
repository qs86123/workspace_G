package com.wt.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.pojo.Person;
import com.wt.repositories.PersonRepository;

@Service
public class PersonReposotoryService {

	@Autowired
	private PersonRepository dao;

	@Transactional
	public void savePerson(Person p1, Person p2) {
		dao.save(p1);
		// int i = 1 / 0;
		dao.save(p2);
	}
}
