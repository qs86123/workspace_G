package com.wt.jpa.junitest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.jpa.demo.Customer;
import com.wt.jpa.demo.Order;

public class ManytoOneTest {
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
	public void persistence() {
		Customer c = new Customer();
		c.setAge(23);
		c.setLastName("wo");

		Order o1 = new Order();
		o1.setOrderName("o1");
		Order o2 = new Order();
		o2.setOrderName("o2");
		// 设置关联关系
		o1.setCustomer(c);
		o2.setCustomer(c);
		em.persist(c);
		em.persist(o1);
		em.persist(o2);
	}

	@Test
	public void find() {
		Order o = em.find(Order.class, 2);
		System.out.println(o.getOrderName());
		// 开了懒加载的话可以看得出来是打印了ordername之后在输出查询customer的sql
		// 不开懒加载的话直接把查询一次性做完，即输出所有sql后再输出结果
		// 可以根据@ManytoOne的fetch属性来设置懒加载
		System.out.println(o.getCustomer().getLastName());
	}

	// 不能直接删除1的一端，因为有外键约束
	@Test
	public void remove() {
		// Order o = em.find(Order.class, 4);
		// em.remove(o);
		// 一对多关联之后就可以删除1的那端，此时默认是不删除孤儿节点，只是把孤儿节点的外键置空，如果要删除，则可以根据onetomany的属fetch属性来设置
		// 或者根据orphanRemoval=true来设置
		Customer c = em.find(Customer.class, 13);
		em.remove(c);
	}

	@Test
	public void update() {
//		Order o = em.find(Order.class, 3);
//		
		Customer c=em.find(Customer.class, 15);
//		Customer c2=em.find(Customer.class, 15);
		Customer c1=new Customer();
		c1.setId(16);
		c.getOrders().iterator().next().setCustomer(c1);
		c.getOrders().iterator().next().setOrderName("asfasdf");
		
	}

}
