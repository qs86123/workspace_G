package com.wt.repro.mongorepro;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.wt.entity.Person;

/**
 * 不用写实现类，但是方法名需要按照规范来写
 * @author The_kid
 * @param <T>
 */
public interface PersonSourceRepository extends MongoRepository<Person, String> {

	List<Person> findByName(String name);
	
	List<Person> findByName(String name,Pageable pageable);
	
	
}
