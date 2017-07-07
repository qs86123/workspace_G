package com.wt.apptest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.pojo.Person;
import com.wt.repositories.impl.PersonRepo;

public class APPTestImpl {

	private ApplicationContext context = null;
	private PersonRepo p = null;

	{
		context = new ClassPathXmlApplicationContext("classpath:application.xml");
		p = context.getBean(PersonRepo.class);
	}

	@Test
	public void test() {
		Person p1 = new Person("wangtao", "22", "nan");
		Person p2 = new Person("wangtao2", "23", "nan");
		p.savePerson(p1, p2);
	}
}
