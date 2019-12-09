package com.ZzacharyChao.group.elasticsearch.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
	
	@Test
	public void updateRequest() throws IOException {
		UpdateRequest request = new UpdateRequest("test","user","4");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name","updateRequest_2");
		map.put("age", 123);
		request.doc(map);
		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
		System.out.println(response.getResult());
		client.close();
	}
	@Test
	public void deleteRequest() throws IOException {
		DeleteRequest request = new DeleteRequest("test","user","2");
		boolean exists = client.exists(new GetRequest("test","user","2"),RequestOptions.DEFAULT);
		if(exists) {
			DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
			System.out.println(response.getResult());
		}else {
			System.out.println("²»´æÔÚ");
		}
	}
}











