package com.wt.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import com.wt.pojo.Person;

@Repository
public class PersonRepository {

	@Autowired
	private RedisTemplate<String, Person> template;
	private RedisSerializer<String> serializer = new StringRedisSerializer();
	private Jackson2JsonRedisSerializer<Person> serializerjson = new Jackson2JsonRedisSerializer<Person>(Person.class);

	public void savePerson(Person person) {
		template.setKeySerializer(serializerjson);
		template.setValueSerializer(serializerjson);
		template.opsForValue().set(person.getId(), person);
		Person p = template.opsForValue().get(person.getId());
		System.out.println(p);
		// template.delete(person.getId());
	}

	public void getPerson(String id) {
		template.setKeySerializer(serializerjson);
		template.setValueSerializer(serializerjson);
		Person p = template.opsForValue().get(id);
		System.out.println(p);
	}

	public void save(final Person person) {
		template.execute(new RedisCallback<Person>() {

			public Person doInRedis(RedisConnection connection) throws DataAccessException {
				// connection.set(serializer.serialize(person.getId()),
				// serializer.serialize(person.getName()));
				connection.set(serializerjson.serialize(person.getId()), serializerjson.serialize(person));
				return null;
			}
		});
	}

	public void get(final String id) {
		template.execute(new RedisCallback<Person>() {

			public Person doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.get(serializerjson.serialize(id));
				Person p = serializerjson.deserialize(value);
				System.out.println(p);
				return null;
			}
		});
	}

}
