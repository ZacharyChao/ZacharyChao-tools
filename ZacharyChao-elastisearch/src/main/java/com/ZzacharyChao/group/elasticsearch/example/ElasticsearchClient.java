package com.ZzacharyChao.group.elasticsearch.example;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class ElasticsearchClient {
	/**
	 * 测试使用RestHighLevelClient连接elasticsearch集群
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		RestHighLevelClient highLevelClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost("47.98.163.175", 9200, "http")));
		GetRequest getRequest = new GetRequest("test", "user", "2");
		boolean exists =highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
		if(exists) {
			System.out.println("数据存在");
		}else {
			System.out.println("数据不存在");
		}
		highLevelClient.close();
	}
}
