package com.wt.pro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wt.pro.entity.Person;

public interface PersonRepository extends BaseJpaDao<Person> {

	List<Person> findByName(String name);

	@Query("select p from Person p where p.name = :name")
	public Person findqqqq(@Param("name")String name);
	
	@Query("update Person set name= ?2 where name= ?1 ")
	@Modifying
	public void changeOne(String name,String newName);
	
	@Query("delete from Person where name= ?1")
	@Modifying
	public void deleteOne(String name);
	
}
