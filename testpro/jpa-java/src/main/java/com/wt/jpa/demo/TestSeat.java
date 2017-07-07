package com.wt.jpa.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Test;

import org.junit.After;
import org.junit.Before;

public class TestSeat {
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
	public void test() {
		SeatInfo s = new SeatInfo();
		s.setSeat(25);
		Customer c = new Customer();
		c.setId(7);
		s.setC(c);
		em.persist(s);
	}

	@Test
	public void test2(){
		Customer c=em.find(Customer.class, 5);
		System.out.println(c.getId());
		List<SeatInfo> list=c.getSis();
		for (SeatInfo o : list) {
			o.getSeat();
		}
		System.out.println(list.size());
	}
	
	@Test
	public void test3(){
		SeatInfo s=em.find(SeatInfo.class, 3);
		System.out.println(s.getId());
		Customer c=s.getC();
		System.out.println(c.getId());
	}
	
	private int digui(int n) {
		if (n > 1)
			return n + digui(n - 1);
		if (n == 1)
			return 1;
		else
			return 0;
	}

	private int f(int day) {
		if (day == 1)
			return 1;
		return f(day - 1) * 2;
	}

	@Test
	public void testdigui() {
		System.out.println(digui(9));
		System.out.println(f(2));
		System.out.println(f(4));
		System.out.println(((double)f(23)/f(30)));
	}
}
