package com.ZacharyChao.group.elasticsearch.example;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class ElasticsearchClient {
	/**
	 * ����ʹ��RestHighLevelClient����elasticsearch��Ⱥ
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		RestHighLevelClient highLevelClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost("39.100.232.215", 9200, "http")));
		GetRequest getRequest = new GetRequest("specinfo", "doc", "2");
//		boolean exists =highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
//		if(exists) {
//			System.out.println("���ݴ���");
//		}else {
//			System.out.println("���ݲ�����");
//		}
		highLevelClient.get(getRequest, RequestOptions.DEFAULT);
		highLevelClient.close();
	}
}
