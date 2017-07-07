package com.wt.jpa.junitest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.jpa.secondlevelcache.SecondLevelCache;

public class SecondLevelCacheTest {

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
	public void secondLevelCacheTest() {
		SecondLevelCache s = em.find(SecondLevelCache.class, 1);
		System.out.println(s.getName());
		// 提交事务，此时将清空一级缓存
		tx.commit();
		em.close();
		// 重新开启事务
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		// 一级缓存已经不存在，重新查询将发送sql
		SecondLevelCache s1 = em.find(SecondLevelCache.class, 1);
		System.out.println(s1.getName());
	}

}
