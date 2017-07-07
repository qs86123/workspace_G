package com.wt.demo;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.wt.pojo.Person;
import com.wt.repository.PersonRepository;

public class TestEsSpring {

	// 如果索引不存在则自动创建
	@Test
	public void testAdd() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		Person p = new Person("0001", "wang tao", "male", "23");
		pr.save(p);
	}

	@Test
	public void testFindAll() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		Iterable<Person> it = pr.findAll();
		Iterator<Person> itt = it.iterator();
		while (itt.hasNext()) {
			System.out.println(itt.next());
		}
	}

	@Test
	public void testfindByUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		System.out.println("--------------------findByUser----------------------");
		List<Person> lists = pr.findByUser("wang");
		for (Person person : lists) {
			System.out.println(person);
		}
		System.out.println("--------------------findByUser  page----------------------");
		List<Person> lists2 = pr.findByUser("wang tao 2", new PageRequest(0, 1));
		for (Person person : lists2) {
			System.out.println(person);
		}
	}

	@Test
	public void testFindDistinctAndOR() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);

		System.out.println("-----------------------findDistinctByUser----------------------------");
		List<Person> lists = pr.findDistinctByUser("wang tao 2");
		for (Person person : lists) {
			System.out.println(person);
		}
		System.out.println("-----------------------findByUserAndSex----------------------------");
		List<Person> lists2 = pr.findByUserAndSex("wang", "male 2");
		for (Person person : lists2) {
			System.out.println(person);
		}

		System.out.println("-----------------------findByUserOrSex----------------------------");
		List<Person> lists3 = pr.findByUserOrSex("abcd", "male");
		for (Person person : lists3) {
			System.out.println(person);
		}
	}

	@Test
	public void testDeleteOnePerson() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		// 只需要给一个id就可以删除，即使后边的sex，age属性对不上也会删除
		pr.delete(new Person("AVbg-lw16a_OSBrmjESQ", "wang tao 2", "male 2", "23"));
	}

}
