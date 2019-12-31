package com.zacharychao.elasticsearch.transportclient.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransportExample {
	private static String HOST = "47.98.163.175";
	private static TransportClient client;
	
	public static void main(String[] args) {
//		List<String> list = searchAll();
//		System.out.println(list);
//		insert();
		printSearchListByParam();
	}
	
	
	private static void init() throws UnknownHostException {
		if(client == null) {
			Settings setting = Settings.builder().put("cluster.name", "elasticsearch").build();
			client = new PreBuiltTransportClient(setting);
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(HOST),9300));
		}
	}
	
	public static void close() {
		client.close();
	}
	
	public static void printSearchListByParam() {
		Settings setting = Settings.builder().put("cluster.name","elasticsearch").build();
		TransportClient c = new PreBuiltTransportClient(setting);
		try {
			c.addTransportAddress(new TransportAddress(InetAddress.getByName("47.98.163.175"),9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		MatchPhraseQueryBuilder matchPhraseQuery = QueryBuilders.matchPhraseQuery("speccode", "123456");
		boolQuery.must(matchPhraseQuery);
		SearchResponse searchResponse = c.prepareSearch("test1").setTypes("doc").setQuery(boolQuery).get();
		SearchHits hits = searchResponse.getHits();
		hits.forEach(item -> System.out.println(item.getSourceAsString()));
		c.close();
	}
	
	public static List<String> searchAll(){
		try {
			init();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
		SearchResponse response = client.prepareSearch("test1").setTypes("doc").setQuery(matchAllQuery).get();
		List<String> list = new ArrayList<String>();
		SearchHits hits = response.getHits();
		hits.forEach(item -> list.add(item.getSourceAsString()));
		close();
		return list;
	}
	
	public static void insert() {
		try {
			init();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> map = new HashMap<>();
		map.put("categorate", "12345");
		map.put("isproductstandard", "false");
		map.put("releasestatus", "test");
		map.put("speccode", "123456");
		ObjectMapper mapper = new ObjectMapper();
		String source = null;
		try {
			source = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(source != null) {
			IndexResponse response = client.prepareIndex("test1", "doc").setSource(map).get();
			String responseId = response.getId();
			System.out.println(responseId);
		}
		close();
	}
}
