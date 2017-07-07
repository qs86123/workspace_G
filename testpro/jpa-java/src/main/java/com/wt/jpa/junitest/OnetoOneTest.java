package com.wt.jpa.junitest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wt.jpa.onetoone.Department;
import com.wt.jpa.onetoone.Maneger;

public class OnetoOneTest {
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
		Maneger m = new Maneger();
		m.setMgrName("M-BB");
		Department d = new Department();
		d.setDeptName("D-BB");
		d.setMgr(m);
		// m.setDpt(d);//不维护外键的一端可以不用关联
		em.persist(m);// 建议先保存不维护外键的一端，这样少update语句
		em.persist(d);
	}

	// 默认采用左外链接查询，可以通过设置fetch属性为lazy来改变查询策略,使用lazy，将会使用代理类
	// 在一对一的时候fetch属性的lazy对于懒加载是无效的，需要使用其他方式来进行懒加载
	@Test
	public void find() {
		Department d = em.find(Department.class, 2);
		System.out.println(d.getDeptName());

		System.out.println(d.getMgr().getClass().getName());
	}

	// 由不维护外键的一段查询的时候，也是使用的左外链接进行的查询，也可以通过fetch属性来更改查询策略
	// 但是不建议修改，因为修改之后，会发送两条查询语句。
	// 原因是不维护外键的一端没有外键这个字段，在进行查询的时候，系统并不知道有没有外键，如果使用代理查询，没有外间则会报错;
	// 如果不使用代理查询，那万一有外键怎么办，必须要进行一次查询，所以就只能不适用代理，直接多查询一次
	@Test
	public void find2() {
		Maneger m = em.find(Maneger.class, 2);
		System.out.println(m.getMgrName());

		System.out.println(m.getDpt().getClass().getName());
	}

}
