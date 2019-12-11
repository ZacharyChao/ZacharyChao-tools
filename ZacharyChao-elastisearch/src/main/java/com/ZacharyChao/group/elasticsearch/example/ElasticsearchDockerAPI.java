package com.ZacharyChao.group.elasticsearch.example;

import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticsearchDockerAPI {
	public final static String HOST = "39.100.232.215";
	public final static int PORT = 9300;// http����Ķ˿���9200���ͻ�����9300
	private static TransportClient client;

	public static void main(String[] args) throws Exception {
		Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();
		// �����ͻ���
		client = new PreBuiltTransportClient(settings)
				.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));
		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchResponse response = client.prepareSearch("specinfo").setQuery(queryBuilder).get();
        System.out.println(response.getHits().getTotalHits());
        for (SearchHit searchHit : response.getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
		client.close();
	}
}
