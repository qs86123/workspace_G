package com.wt.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.engine.Engine.DeleteByQuery;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService.ScriptType;
import org.junit.Test;

public class EsDelete {

	private Client getClient() throws Exception {
		TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("192.168.2.13"), 9300);
		Client client = TransportClient.builder().build().addTransportAddress(address);
		return client;
	}

	private boolean isIndexExists(String indexName) throws Exception {
		Client client = getClient();
		IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);
		IndicesExistsResponse responde = client.admin().indices().exists(inExistsRequest).actionGet();
		if (responde.isExists())
			return true;
		return false;
	}

	@Test
	public void test1() throws Exception {
		Client client = getClient();

		String indexName = "abb1";
		if (!isIndexExists(indexName)) {
			System.out.println(indexName + " is not exit");
		} else {
			DeleteIndexResponse response = client.admin().indices().prepareDelete(indexName).execute().actionGet();
			if (response.isAcknowledged())
				System.out.println("delete " + indexName + " success");
		}
	}

	// 指定文档删除
	@Test
	public void test2() throws Exception {
		Client client = getClient();

		String index = "twitter";
		String type = "tweet";
		String id = "AVbkHrgzf3WxtVFDPjoq";

		DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
		if (response.isFound())
			System.out.println("delete success");
		else
			System.out.println("document not found");

	}

	// 报错：ActionNotFoundTransportException[No handler for action
	// [indices:data/write/delete/by_query]]
	@Test
	public void testDeleteByQuery() throws Exception {
		TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("192.168.2.13"), 9300);
		Client client = TransportClient.builder().addPlugin(DeleteByQueryPlugin.class).build()
				.addTransportAddress(address);

		String source = "{\"query\": {\"match_all\": {}}}";
		DeleteByQueryRequestBuilder builder = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE);
		builder.setIndices("film");
		builder.setTypes("mdd");
		builder.setSource(source);
		DeleteByQueryResponse response = builder.execute().actionGet();
		if (response.isContextEmpty())
			System.out.println("ContextEmpty");
		else
			System.out.println("delete success");
	}

	//脚本解析错误。。。。。
	@Test
	public void testDeleteField() throws Exception {
		Client client = getClient();
		client.prepareUpdate("abb", "abc", "2")
				.setScript(new Script("ctx._source.remove(\"content5\")", ScriptType.INLINE, null, null))
				.get();
	}

}
