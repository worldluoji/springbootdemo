package com.example.eslearn;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

@Slf4j
@SpringBootApplication
public class EslearnApplication implements ApplicationRunner {

	@Autowired
	private RestHighLevelClient client;

	public static void main(String[] args) {
		SpringApplication.run(EslearnApplication.class, args);
	}

	/**
	* public static enum OpType {
	 *         INDEX(0),
	 *         CREATE(1),
	 *         UPDATE(2),
	 *         DELETE(3);
	* */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 创建一个index名叫my-index-0003,里面包含一个字段feature, 值为"high-level-rest-client"
		IndexRequest request = new IndexRequest("my-index-0003")
				.source(singletonMap("feature", "high-level-rest-client"))
				.setRefreshPolicy(IMMEDIATE);

		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
		log.info(response.toString());
	}

	/**
	* GET my-index-0003/_search：
	 *  {
	 *   "took" : 0,
	 *   "timed_out" : false,
	 *   "_shards" : {
	 *     "total" : 1,
	 *     "successful" : 1,
	 *     "skipped" : 0,
	 *     "failed" : 0
	 *   },
	 *   "hits" : {
	 *     "total" : {
	 *       "value" : 1,
	 *       "relation" : "eq"
	 *     },
	 *     "max_score" : 1.0,
	 *     "hits" : [
	 *       {
	 *         "_index" : "my-index-0003",
	 *         "_type" : "_doc",
	 *         "_id" : "I0kDm3gBWuwxqfb56A-s",
	 *         "_score" : 1.0,
	 *         "_source" : {
	 *           "feature" : "high-level-rest-client"
	 *         }
	 *       }
	 *     ]
	 *   }
	 * }
	* */
}
