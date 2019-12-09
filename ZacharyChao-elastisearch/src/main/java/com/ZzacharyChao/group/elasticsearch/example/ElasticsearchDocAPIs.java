package com.ZzacharyChao.group.elasticsearch.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class ElasticsearchDocAPIs {
	private static RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("47.98.163.175",9200,"http")));
	@Test
	public void createIndex() throws IOException {
		IndexRequest request = new IndexRequest("test", "user", "5");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "createIndex");
		map.put("age",26);
		request.source(map);
		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
		System.out.println(response.getId());
		client.close();
	}
	
	@Test
	public void getRequest() throws IOException {
		GetRequest request = new GetRequest("test","user","5");
		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		System.out.println(response.getSource());
		client.close();
	}
}
