package com.wt.jpa.junitest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.jpa.manytomany.Category;
import com.wt.jpa.manytomany.Item;

public class ManytoManyTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;

	@Before
	public void init() {
		String persistenceUnitName = "jpa1";
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
	}

	@After
	public void destory() {
		tx.commit();
		em.close();
	}

	@Test
	public void find() {
		// 无论是先查询维护外键的表还是不维护外键的表，输出的sql语句都一样
		// Item i = em.find(Item.class, 1);
		// System.out.println(i.getItemName());
		// System.out.println(i.getCs().size());

		Category c = em.find(Category.class, 1);
		System.out.println(c.getCategoryName());
		System.out.println(c.getItems().size());
	}

	@Test
	public void persistence() {
		Item i1 = new Item();
		i1.setItemName("i-1");
		Item i2 = new Item();
		i2.setItemName("i-2");

		Category c1 = new Category();
		c1.setCategoryName("c-1");
		Category c2 = new Category();
		c2.setCategoryName("c-2");

		// 使用维护外键的一端关联其他对象，如果使用Category来关联的话，保存的时候中间表中不会保存关联的数据
		i1.getCs().add(c1);
		i1.getCs().add(c2);
		i2.getCs().add(c1);
		i2.getCs().add(c2);

		em.persist(c1);
		em.persist(c2);
		em.persist(i1);
		em.persist(i2);
	}
}
