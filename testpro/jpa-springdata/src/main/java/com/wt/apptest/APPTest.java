package com.wt.apptest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.wt.repositories.IPerson;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.pojo.Person;
import com.wt.pojo.PersonAndItem;
import com.wt.repositories.PersonAndItemDao;
import com.wt.repositories.PersonRepository;
import com.wt.repositories.impl.PersonReposotoryService;

public class APPTest {

    private ApplicationContext context = null;
    private PersonRepository p = null;

    {
        context = new ClassPathXmlApplicationContext("classpath:application.xml");
        p = context.getBean(PersonRepository.class);
    }

    /**
     * 保存
     */
    @Test
    public void save() {
        p.save(new Person("wangtao", "23", "男"));
        List<Person> persons = p.findByName("wangtao");
        System.out.println(persons.get(0));
    }

    /**
     * 查询并且更新
     */
    @Test
    public void selectAndUpdate() {
        Person person = p.findqqqq("wangtao");
        System.out.println(person);
        person.setAge("10000");
        p.save(person);// 改操作会更新原始数据
    }

    /**
     * 给对象设置id，直接更新
     */
    @Test
    public void update() {
        Person person = new Person("name", "age", "sex");
        person.setId("abcd");// id为1的存在，则更新,如果不存在则insert，但是insert的数据的id是自动生成的
        p.save(person);
    }

    /**
     * 事务
     */
    @Test
    public void transacation() {
        PersonReposotoryService prService = context.getBean(PersonReposotoryService.class);
        Person p1 = new Person("taotao", "age", "nan");
        Person p2 = new Person("taotao2", "age", "nan");
        prService.savePerson(p1, p2);
    }

    @Test
    public void testaaa() {
//		PersonAndItem pi = p.findpersonAnditem();
//		System.out.println(pi);

//		PersonAndItemDao p1=context.getBean(PersonAndItemDao.class);
//		p1.save(new PersonAndItem());
//		long i=p.countByName("wt1");
        long i = p.countByNameAndSex("wt", "nan");
        System.out.println("i=" + i);
    }

    @Test
    public void testNativeQuery() {
        Person perosn = p.findNativeQuery("wangtao");
        System.out.println(perosn);
    }

    @Test
    public void testNativeQuery2() {
        List<Person> perosns= p.findNativeQuery(0, 3);
        for (Person person:perosns)
        System.out.println(person);
    }



}
