package com.zacharychao.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ESConfig {
	@Bean
	public RestHighLevelClient getRestHighLevelClient() {
		return getESDecorator().getRestHighLevelClient();
	}
	@Bean
	@Scope("singleton")
	public ESDecorator getESDecorator() {
		return new ESDecorator(new HttpHost("47.98.163.175",9200,"http"));
	}
}
