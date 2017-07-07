package com.wt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wt.pojo.Person;

//直接集成ElasElasticsearchRepository，再写接口的话就不用写实现类，在spring配置文件中配置
//<elasticsearch:repositories base-package="com.wt.repository" />就可以了，spring会自动创建代理类，
//注意这里的扫描包不是用的spring的<content:compnent-scan>
//并将将template注入,见springContext.xml
//注意：此种方法查询的时候由代理方式生成的查询语句与方法的名字有关系，详见官方文档，或者es学习资料里面的文档
public interface PersonRepository extends ElasticsearchRepository<Person, String> {

	List<Person> findByUser(String user);

	List<Person> findByUser(String user, Pageable pageable);

	List<Person> findDistinctByUser(String user);

	List<Person> findByUserAndSex(String user, String sex);

	List<Person> findByUserOrSex(String user, String sex);

}
