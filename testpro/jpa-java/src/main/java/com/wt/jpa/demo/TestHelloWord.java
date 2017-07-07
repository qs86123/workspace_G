package com.wt.jpa.demo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestHelloWord {
	public static void main(String[] args) {
		//在persistence.xml配置文件中找
		String persistenceUnitName="jpa1";
		//创建EntityManagerFactory
		EntityManagerFactory emf=Persistence.createEntityManagerFactory(persistenceUnitName);
		//创建EntiytManager
		EntityManager em=emf.createEntityManager();
		//开启事物
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//进行持久化操作
		Customer c=new Customer();
		c.setAge(23);
		c.setEmail("111d@qq.com");
		c.setLastName("中文");
		c.setBirthday(new Date());
		c.setCreatetime(new Date());
		em.persist(c);
		//提交事物
		tx.commit();
		//关闭EntityManager
		em.close();
	}
}
