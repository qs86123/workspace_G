package com.wt.app;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;

import com.wt.entity.Person;
import com.wt.repro.Repository;
import com.wt.repro.mongorepro.PersonSourceRepository;

public class APPTestCustom {

	public <T> T getBeanforCustom(Class<T> t) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-impl.xml");
		T tt = (T) context.getBean(t);
		return tt;
	}

	@Test
	public void test01() {
		// 自定义实现方法的时候，实体类可以不用加任何注解
		Repository repository = getBeanforCustom(Repository.class);
		List<Person> list = repository.getAllObjects();
//		repository.saveObject(new Person("004","wangtao","23"));
		for (Person person : list) {
			System.out.println(person);
		}
		System.out.println("-------------------------------------------");
		List<Person> list2 = repository.findbyname("wangtao");

		for (Person person : list2) {
			System.out.println(person);
		}
	}

	@Test
	public void getAllCollecion(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.getCollections();
	}
	
	@Test
	public void testUpdate(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.updateObjectOne("", "");
	}
	
	@Test
	public void testUpdateAll(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.updateObjectAll("", "");
	}
	
	@Test
	public void testRemoveAll(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.deleteObjectAll("");
	}
	
	@Test
	public void testCreateCollection(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.createCollection();
	}
	
	@Test
	public void testdropCollection(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.dropCollection();
	}
	
	@Test
	public void selectData(){
		Repository repository = getBeanforCustom(Repository.class);
		repository.selectData();
	}
	
}
