package com.zacharychao.es.service;

import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

public class ESSearchClient implements ISearchClient{
	@Autowired
	private RestHighLevelClient client;
	
	@Override
	public List<JSONObject> search(SearchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchString(SearchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> search(SearchRequest request, Class<T> T) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> IndexResponse index(String index, String type, String id, T t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
