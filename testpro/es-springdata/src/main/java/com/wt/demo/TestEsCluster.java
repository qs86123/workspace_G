package com.wt.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.pojo.Person;
import com.wt.repository.PersonRepository;

public class TestEsCluster {

	// 保存的时候如果id相同则直接覆盖，相当于更新
	@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-cluster.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		Person p = new Person("0002", "wang 100", "male", "23");
		pr.save(p);
	}

}
