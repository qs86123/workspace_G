package com.wt.repository;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.ToXContent.Params;
import org.elasticsearch.index.query.AndQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.stereotype.Repository;

import com.wt.pojo.CustomerPerson;

@Repository
public class CustomerPersonRepository implements CustomerRepository {

	@Autowired
	private ElasticsearchTemplate template;

	public void findByName(String name) {
		Criteria criteria = new Criteria();
		criteria.and("user").is("wang");
		CriteriaQuery query = new CriteriaQuery(criteria);
		// 在person实体类中指定了之后就不用再次在这里指定
		// query.addIndices("twitter");
		// query.addTypes("tweet");
		List<CustomerPerson> person = template.queryForList(query, CustomerPerson.class);
		for (CustomerPerson p : person) {
			System.out.println(p);
		}
	}

	@Override
	public void deleteIndex(String indexName) {
		 template.deleteIndex(indexName);

	}

	@Override
	public void deleteOne(String index, String type,String field,String value) {
		QueryBuilder builder = new TermsQueryBuilder(field, value);
		DeleteQuery deleteQuery = new DeleteQuery();
		deleteQuery.setQuery(builder);
		deleteQuery.setIndex(index);
		deleteQuery.setType(type);
		template.delete(deleteQuery);
	}

}
