package com.wt.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.repository.CustomerPersonRepository;

public class TestCustomerSpring {

	// 根据名字查询
	@Test
	public void testfindByName() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-impl.xml");
		CustomerPersonRepository pr = (CustomerPersonRepository) context.getBean(CustomerPersonRepository.class);
		pr.findByName("wang");
	}

	// 删除一个索引
	@Test
	public void testDeleteIndex() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-impl.xml");
		CustomerPersonRepository pr = (CustomerPersonRepository) context.getBean(CustomerPersonRepository.class);
		pr.deleteIndex("films");
	}

	// 删除一条记录
	@Test
	public void testDeleteOne() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-impl.xml");
		CustomerPersonRepository pr = (CustomerPersonRepository) context.getBean(CustomerPersonRepository.class);
		pr.deleteOne("users", "uu", "user", "wang");
	}

}
