package com.wt.jpa.junitest;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.jpa.demo.Customer;

public class JPATest {

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

	// 类似于hiberbate中的session的get方法
	@Test
	public void find() {
		Customer c = em.find(Customer.class, 1);
		System.out.println("-------------------------------");
		System.out.println(c.getLastName());
	}

	// 类似于hiberbate中的session的load方法
	@Test
	public void getReference() {
		Customer c = em.getReference(Customer.class, 1);
		System.out.println("--------------对象没有使用输出横线-----------------");
		System.out.println(c.getLastName());
	}

	// 类似于hiberbate中的session的save方法,对象由临时状态变为持久化状态
	// 如果对象有id，则抛异常，hibernate的save方法则不会
	@Test
	public void Persistence() {
		Customer c = new Customer();
		c.setAge(23);
		c.setBirthday(new Date());
		c.setCreatetime(new Date());
		c.setLastName("wangtao");
		em.persist(c);
		System.out.println(c.getId());
	}

	// 类似于hiberbate中的session的delete方法
	// 注意：该方法只能移除持久化对象，不能移除游离对象，而hibernate可以移除游离对象
	@Test
	public void remove() {
		// Customer youlic = new Customer();
		// youlic.setId(2);
		// em.remove(youlic);//报错

		Customer chijiuc = em.find(Customer.class, 2);
		em.remove(chijiuc);

	}

	// 类似于hibernate的saveOrUpdate方法
	// 1.若传入的是一个临时对象，则会把临时对象赋值给新的对象，对新的对象进行持久化操作（此时新对象就有id了，但是临时对象没有id），返回新的对象，
	@Test
	public void marge1() {
		Customer c = new Customer();
		c.setAge(23);
		c.setBirthday(new Date());
		c.setCreatetime(new Date());
		c.setLastName("hahaha");

		Customer c2 = em.merge(c);
		System.out.println("c:" + c.getId());
		System.out.println("c2:" + c2.getId());
	}

	// 2.若传入的是一个游离对象：即传入的对象有OID
	// 2.1若entityManager缓存中没有改对象
	// 2.2若数据库中有没有该id的对象
	// 2.3jpa创建一个新对象，向marge1一样执行insert操作
	// 如果entityManager缓存中有，则直接将游离对象的值赋值给entityManager缓存对象，jpa对entityManager缓存中的对象执行update操作
	// 如果数据库中有记录，则查询该记录，然后把游离对象赋值给查询到的记录，对查询到的记录执行update操作
	@Test
	public void marge2() {
		Customer c = new Customer();
		c.setAge(8);
		c.setBirthday(new Date());
		c.setCreatetime(new Date());
		c.setLastName("nidie");

		c.setId(100);// 设置id，将对象从临时变为游离

		Customer c2 = em.merge(c);
		System.out.println("c:" + c.getId());
		System.out.println("c2:" + c2.getId());
	}

	@Test
	public void marge3() {
		Customer c = em.find(Customer.class, 7);
		c.setAge(200);

		Customer c2 = em.merge(c);
		System.out.println("c:" + c.getId());
		System.out.println("c2:" + c2.getId());
	}
	
	@Test
	public void save() {
		String s =em.getFlushMode().name();
		System.out.println(s);
	}

}
