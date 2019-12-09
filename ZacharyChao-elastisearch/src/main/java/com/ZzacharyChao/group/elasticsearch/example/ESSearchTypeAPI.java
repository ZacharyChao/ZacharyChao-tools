package com.ZzacharyChao.group.elasticsearch.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

public class ESSearchTypeAPI {
	private RestHighLevelClient client ;
	@Before
	public void init() {
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("47.98.163.175",9200,"http")));
	}
	@Test
	public void search() throws IOException {
		SearchRequest request = new SearchRequest();
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.query(QueryBuilders.matchQuery("age", 123));
		request.source(builder);
		request.indices("test");
		request.types("user");
		SearchResponse response = client.search(request, RequestOptions.DEFAULT);
		SearchHits hits = response.getHits();
		for(SearchHit hit : hits.getHits()) {
			System.out.println(hit.getSourceAsString());
			System.out.println("******************");
		}
		client.close();
	}
	@Test
	public void indexRequest() throws IOException {
		IndexRequest request = new IndexRequest("test","user","1");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "jack");
		map.put("age", 123);
		request.source(map);
		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
		System.out.println(response.getResult());
		client.close();
	}
}
