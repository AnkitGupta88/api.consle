package com.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;

@SpringBootApplication
public class UrlAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlAccessApplication.class, args);
	}

	@Bean
	public Cluster connect() {
		try {
			Cluster cluster = Cluster.builder().withPort(9042).addContactPoint("159.203.141.233").build();
			Metadata metadata = cluster.getMetadata();
			System.out.println(metadata.getClusterName());
			return cluster;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
