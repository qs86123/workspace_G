package com.wt.repro;

import java.util.List;

import com.mongodb.WriteResult;
import com.wt.entity.Person;

public interface Repository<T> {

	public List<T> getAllObjects();

	public void saveObject(T object);

	public T getCollections();

	public WriteResult updateObjectOne(String id, String name);

	public void deleteObjectAll(String id);

	public void createCollection();

	public void dropCollection();

	public List<Person> findbyname(String name);

	public void updateObjectAll(String id, String name);
	
	public void selectData();
	
}
