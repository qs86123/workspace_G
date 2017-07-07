package com.wt.app;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.convert.TypeInformationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.wt.entity.Person;
import com.wt.repro.Repository;
import com.wt.repro.mongorepro.PersonSourceRepository;

public class APPTest {

	public <T> T getBeanforSource(Class<T> t) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		T tt = (T) context.getBean(t);
		return tt;
	}

	@Test
	public void test02() {
		// 实体类可以不用加任何注解，不用写实现类，但是方法名需要按照规范来写
		PersonSourceRepository psr = getBeanforSource(PersonSourceRepository.class);
//		 psr.save(new Person("1123","nimaa","45"));
		List<Person> list = psr.findAll();
		for (Person person : list) {
			System.out.println(person);
		}
		System.out.println("--------------------------------");
		List<Person> list2 = psr.findByName("wangtao");
		for (Person person : list2) {
			System.out.println(person);
		}
	}

	@Test
	public void testsearch() {
		PersonSourceRepository psr = getBeanforSource(PersonSourceRepository.class);
//		psr.save(new Person("nima", "45"));
		System.out.println("--------------------------------");
		Sort sort = new Sort("age");
		// Page<Person> page = psr.findAll(new PageRequest(0, 100, sort));
		// List<Person> list = page.getContent();
		List<Person> list = psr.findAll(sort);

		for (Person person : list) {
			System.out.println(person);
		}
	}
}
