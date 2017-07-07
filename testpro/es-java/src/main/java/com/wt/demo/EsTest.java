package com.wt.demo;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

public class EsTest {

	private Client getClient() throws UnknownHostException {
		TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("192.168.2.13"), 9300);
		Client client = TransportClient.builder().build().addTransportAddress(address);
		return client;
	}

	@Test
	public void test1() throws IOException {
		// XContentBuilder builder = jsonBuilder().field("user",
		// "wangtao2").field("sex", "male2").field("age", "23")
		// .endObject();
		// String json = builder.string();
		// System.out.println(json);
		String json = "{" + "\"user\":\"wangdaye 2\"," + "\"sex\":\"male 2\"," + "\"age\":\"23\"" + "}";
		Client client = getClient();
		IndexResponse response = client.prepareIndex("twitter", "tweet").setSource(json).get();
		if (response.isCreated())
			System.out.println("添加记录成功");
	}

	@Test
	public void testSearch() throws UnknownHostException {
		Client client = getClient();
		// QueryBuilder qb = QueryBuilders.termQuery("user", "wang");
		QueryBuilder qb = QueryBuilders.multiMatchQuery("2", "male", "user");
		SearchResponse response = client.prepareSearch("twitter").setTypes("tweet").setQuery(qb).execute().actionGet();

		SearchHits hits = response.getHits();
		if (hits.totalHits() > 0) {
			for (SearchHit hit : hits) {
				System.out.println(hit.getSource());
			}
		} else {
			System.out.println("result=0");
		}
	}

	// 通过json查询
	@Test
	public void testSearchByJson() throws UnknownHostException {
		Client client = getClient();

		String json = "{\"query\":{\"match_all\":{}}}";
		// String json = "{\"query\":{\"term\":{\"user\":\"2\"}}}";
		SearchResponse response = client.prepareSearch("abb1").setTypes("abc1").setQuery(json).execute().actionGet();
		SearchHits hits = response.getHits();
		if (hits.totalHits() > 0) {
			for (SearchHit hit : hits) {
				System.out.println(hit.getSource() + hit.getId());
			}
		} else {
			System.out.println("result=0");
		}
	}

	// 使用UpdateRequest方式，如果文档不存在不会创建，字段不存在将自动创建
	@Test
	public void testUpdate() throws Exception {
		Client client = getClient();
		UpdateRequest updtReq = new UpdateRequest();
		updtReq.index("twitter");
		updtReq.type("tweet");
		updtReq.id("AVbg-lw16a_OSBrmjESQ");
		updtReq.doc(jsonBuilder().startObject().field("content2", "abcdefg").endObject());
		client.update(updtReq).get();
	}

	// 如果没有content这个字段将自动创建
	@Test
	public void testUpdate2() throws Exception {
		Client client = getClient();
		client.prepareUpdate("twitter", "tweet", "AVbg-lw16a_OSBrmjESQ")
				.setDoc(jsonBuilder().startObject().field("content", "gfedbca").endObject()).get();
	}

	// 如果索引不存在则创建新的索引，通过indexRequest定位到需要更新的文档，执行更新
	@Test
	public void testUpdate3() throws Exception {
		Client client = getClient();
		String indexname = "abb";
		String typename = "abc";
		String id = "2";
		// 需要被更新的文档的定位
		IndexRequest indexRequest = new IndexRequest(indexname, typename, id);
		indexRequest
				.source(jsonBuilder().startObject().field("user", "hehe 2").field("content2", "abcdefg").endObject());

		// 文档将要被更新成生么样子，content5字段如果没有将自动创建，如果这里使用新的不存在的索引，将自动创建新
		UpdateRequest updateRequest = new UpdateRequest(indexname, typename, id);
		updateRequest
				.doc(jsonBuilder().startObject().field("user", "hehe 3").field("content5", "12134567").endObject());
		updateRequest.upsert(indexRequest);
		client.update(updateRequest).get();
	}

	// 测试不通过
	@Test
	public void testUpdate4() throws Exception {
		Client client = getClient();
		String indexname = "abb";
		String typename = "abc";
		String id = "2";
		String json1 = "{\"user\":\"hehe 3\";\"content\":\"abcdefg\"}";
		String json2 = "{\"user\":\"hehe 4\";\"content\":\"abcdefgggg\"}";
		IndexRequest indexRequest = new IndexRequest(indexname, typename, id);
		indexRequest.source(json1);

		UpdateRequest updateRequest = new UpdateRequest(indexname, typename, id);
		updateRequest.doc(json2);
		updateRequest.upsert(indexRequest);
		client.update(updateRequest).get();
	}

}
