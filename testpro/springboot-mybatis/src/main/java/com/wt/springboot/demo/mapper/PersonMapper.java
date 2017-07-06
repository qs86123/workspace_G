package com.wt.springboot.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wt.springboot.demo.entity.Person;

//@Mapper
public interface PersonMapper {

	@Select("select * from person")
	public List<Person> findPersons();

	public Person findPersonByName(String name);

}
