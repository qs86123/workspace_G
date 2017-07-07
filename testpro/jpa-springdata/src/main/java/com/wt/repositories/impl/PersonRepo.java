package com.wt.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wt.pojo.Person;

@Repository
public class PersonRepo {

	// 这两个注解都可以获得em对象，但是我们应该用PersistenceContext注解，该注解获得与当前session绑定的em
	// @Autowired
	@PersistenceContext
	private EntityManager em;

	public Person getperson(String name) {
		List<Person> list = null;
		Query query = em.createQuery("select p from Person p where p.name=?1 and age=?2", Person.class);
		query.setParameter(2, "23");
		query.setParameter(1, name);
		list = query.getResultList();
		return list.get(0);
	}

	public Person getpersonq(String name) {
		List<Person> list = null;

		// Criteria c=
		// em.getCriteriaBuilder().and(Restrictions.eq("", ""));
		// Query query=em.createQuery(null);
		// list=query.getResultList();
		return list.get(0);
	}

	@Transactional
	public void savePerson(Person p1, Person p2) {
		em.persist(p1);
//		int i = 1 / 0;
		em.persist(p2);
	}
}
