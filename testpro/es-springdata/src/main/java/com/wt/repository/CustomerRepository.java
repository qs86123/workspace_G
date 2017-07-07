package com.wt.repository;

public interface CustomerRepository {

	void findByName(String name);

	void deleteIndex(String indexName);

	void deleteOne(String indexName, String type, String field, String value);
}
