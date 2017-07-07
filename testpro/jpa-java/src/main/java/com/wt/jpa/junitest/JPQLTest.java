package com.wt.jpa.junitest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.jpa.jpql.JPQLEntity;

public class JPQLTest {

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
	public void testJpql() {
		String jpql = "from JPQLEntity e where id =?";
		Query query = em.createQuery(jpql);
		query.setParameter(1, 1);
		List<JPQLEntity> es = query.getResultList();
		System.out.println(es.get(0));
	}

	/**
	 * 默认情况下，如果只查询部分属性，则返回的是Object[],或者Object[]组成的List
	 */
	@Test
	public void testJpqlPartProperties1() {
		String jpql = "select e.name,e.age from JPQLEntity e where id =?";
		Query query = em.createQuery(jpql);
		query.setParameter(1, 1);
		List<Object[]> es = query.getResultList();
		System.out.println(es.get(0));
		for (Object object : es.get(0)) {
			System.out.println(object);
		}
	}

	/**
	 * 如果只查询部分属性,又想返回实体的话，我们可以在实体类中创建相应的构造器，并在JPQL语句中利用该构造器来返回实体类对象
	 */
	@Test
	public void testJpqlPartProperties2() {
		String jpql = "select new JPQLEntity(e.name,e.age) from JPQLEntity e where id =?";
		Query query = em.createQuery(jpql);
		query.setParameter(1, 1);
		List<JPQLEntity> es = query.getResultList();
		System.out.println(es.get(0));
	}

	/**
	 * createNamedQuery 实用于在实体类上面被@NamedQuery标记的JPQL语句
	 */
	@Test
	public void testNamedQuery() {
		Query query = em.createNamedQuery("testNamedQuery");
		query.setParameter(1, 1);
		JPQLEntity j = (JPQLEntity) query.getSingleResult();
		System.out.println(j);
	}

	/**
	 * createNativeQuery 实用于本地sql
	 */
	@Test
	public void testNativeQuery() {
		String sql = "select * from jpa_jpql where id=?";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, 1);
		List<Object[]> o = query.getResultList();
		System.out.println(o.get(0));
		for (Object object : o.get(0)) {
			System.out.println(object);
		}
	}

	@Test
	public void testUpdate() {
		String jpql = "update from JPQLEntity e set e.name=? where id= ?";
		Query query = em.createQuery(jpql).setParameter(1, "wangdaye").setParameter(2, 1);
		query.executeUpdate();
	}

	@Test
	public void testDelete() {
		String jpql = "delete from JPQLEntity where id= ?";
		Query query = em.createQuery(jpql).setParameter(1, 1);
		query.executeUpdate();
	}
}
