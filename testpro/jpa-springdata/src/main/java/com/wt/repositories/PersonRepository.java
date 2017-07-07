package com.wt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wt.pojo.Person;
import com.wt.pojo.PersonAndItem;

public interface PersonRepository extends JpaRepository<Person, String> {

	List<Person> findByName(String name);

	@Query("select p from Person p where p.name = ?1")
	public Person findqqqq(String name);

	@Query("select new PersonAndItem(p.name,i.price) from Person p, Item i where p.name='wt' and i.name='itemwt' ")
	public PersonAndItem findpersonAnditem();

	public long countByName(String name);

	public long countByNameAndSex(String name, String sex);

}
