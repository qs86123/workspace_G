package com.wt.springboot.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wt.springboot.demo.entity.Item;
import com.wt.springboot.demo.entity.Person;
import com.wt.springboot.demo.mapper.PersonMapper;
import com.wt.springboot.demo.mapper2.ItemMapper;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定启动类
@SpringBootTest(classes = { Example.class })
public class AppTest {

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private ItemMapper customerMapper;

	@Test
	public void getPersons() {
		PageHelper.startPage(1, 2, "age DESC");
		List<Person> persons = personMapper.findPersons();

		PageInfo<Person> pi = new PageInfo<Person>(persons);
		int size = pi.getSize();
		long total = pi.getTotal();
		System.out.println("size=" + size);
		System.out.println("total=" + total);
		for (Person person : persons) {
			System.out.println(person);
		}
		System.out.println("--------------------------");
	}

	////////////////////////////////// 第二个数据源的测试/////////////////////////////////
	@Test
	public void getPersonByName() {
		Person person = personMapper.findPersonByName("王大爷");
		System.out.println(person);
		System.out.println("--------------------------");
	}

	@Test
	public void getCustomers() {
		PageHelper.startPage(1, 2, "id DESC");
		List<Item> items = customerMapper.findItems();

		PageInfo<Item> pi = new PageInfo<Item>(items);
		int size = pi.getSize();
		long total = pi.getTotal();
		System.out.println("size=" + size);
		System.out.println("total=" + total);
		for (Item item : items) {
			System.out.println(item);
		}
		System.out.println("--------------------------");
	}

}
