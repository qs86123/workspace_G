package com.wt.repro;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.wt.entity.Person;
import com.wt.entity.Personnal;

@Repository
public class PersonRepository implements com.wt.repro.Repository<Person> {

	@Autowired
	private MongoTemplate mongoTemplate;

	// public void setMongoTemplate(MongoTemplate mongoTemplate) {
	// this.mongoTemplate = mongoTemplate;
	// }

//	@Async//延迟查询注解
	public List<Person> getAllObjects() {
		return mongoTemplate.findAll(Person.class);
	}

	public void saveObject(Person object) {
		mongoTemplate.save(object);

	}

	public Person getCollections() {
		Set<String> set=mongoTemplate.getCollectionNames();
		Iterator<String> it=set.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		return null;
	}

	/**
	 * 只会修改查询到的第一条数据
	 */
	public WriteResult updateObjectOne(String id, String name) {
		Criteria c = new Criteria();
		c.and("name").is("wangzhe");
		Query query = new Query(c);
		Update update = new Update();
		update.set("age", "990");
		// 这三种情况都是只修改查询到的结果的第一条
		// 如果数据不存在直接插入
		// mongoTemplate.upsert(query, update, Person.class);
		// 如果数据不存在不插入
		// WriteResult wr = mongoTemplate.updateFirst(query, update,
		// Person.class);
		// 如果数据不存在不插入，数据存在则修改，并返回原始数据
		Person p = mongoTemplate.findAndModify(query, update, Person.class);
		System.out.println(p);
		return null;
	}

	public void deleteObjectAll(String id) {
		Criteria c = new Criteria();
		c.and("name").is("wangtao");
		Query query = new Query(c);
		mongoTemplate.remove(query, Person.class);
	}

	public void createCollection() {
		mongoTemplate.createCollection("personnal");
	}

	public void dropCollection() {
		mongoTemplate.dropCollection("personnal");
	}

	public List<Person> findbyname(String name) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("name").is("wangtao");
		query.addCriteria(criteria);
		return mongoTemplate.find(query, Person.class, "person");
	}

	public void updateObjectAll(String id, String name) {
		Criteria c = new Criteria();
		c.and("name").is("wangtao");
		Query query = new Query(c);
		Update update = new Update();
		update.set("age", "54321");
		mongoTemplate.updateMulti(query, update, Person.class);
	}

	public void selectData() {
		Criteria c=new Criteria();
		c.and("name").is("nima");
		c.orOperator(new Criteria("id").is("123"),new Criteria("age").is("990"));
		//orOperator:对条件为c的查询结果再进一步查询，c如果不加条件c.and("name").is("nima");则为所有记录，
		//代码的意思就是：查询name=nima的所有项中，id=123或者age=990的项
		Query q=new Query();
		q.addCriteria(c);
		List<Person> list = mongoTemplate.find(q, Person.class);
		for (Person person : list) {
			System.out.println(person);
		}
	}

}
