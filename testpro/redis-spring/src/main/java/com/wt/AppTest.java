package com.wt;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.pojo.Address;
import com.wt.pojo.Person;
import com.wt.repo.MyRepository;
import com.wt.repo.PersonRepository;


@SuppressWarnings("resource")
public class AppTest {

	@Test
	public void test0() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		MyRepository myRepository = context.getBean(MyRepository.class);
		// myRepository.save("");
		myRepository.save22("setkey2", "gege");
	}

	@Test
	public void saveWithLiveTime() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		MyRepository myRepository = context.getBean(MyRepository.class);
		myRepository.saveWithLiveTime();
	}

	@Test
	public void get() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		MyRepository myRepository = context.getBean(MyRepository.class);
		myRepository.get("timekey");
	}

	/*************************************************************************/

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		// pr.savePerson(new Person("007", "wangtao", "23"));
		pr.savePerson(new Person("009", "wangtao", "23", new Address("chengdu", "邛崃")));
	}

	@Test
	public void test2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		// pr.save(new Person("007", "wangtaoaaa", "23"));
		pr.save(new Person("008", "wangtaoaaa", "23", new Address("chengdu", "邛崃")));

	}

	@Test
	public void test3() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		PersonRepository pr = context.getBean(PersonRepository.class);
		// pr.getPerson("007");
		pr.get("007");
		pr.get("008");
		pr.get("009");
	}

}
