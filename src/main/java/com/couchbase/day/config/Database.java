package com.couchbase.day.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

@Configuration
public class Database {

	@Value("${storage.host}")
	private String host;

	@Value("${storage.bucket}")
	private String bucket;

	@Value("${storage.username}")
	private String username;

	@Value("${storage.password}")
	private String password;

	public @Bean Cluster couchbaseCluster() {

		//CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().queryEndpoints(1).build();

		//CouchbaseCluster cluster = CouchbaseCluster.create(env, host);
		CouchbaseCluster cluster = CouchbaseCluster.create(host);
		cluster.authenticate(username, password);
		return cluster;
	}

	public @Bean Bucket loginBucket() {
		return couchbaseCluster().openBucket(bucket);
	}
}
