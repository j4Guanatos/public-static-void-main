package com.j4gdl.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StringUtils;

import com.j4gdl.respositories.RepositoriesPackageMarker;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackageClasses = { RepositoriesPackageMarker.class })
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private String port;

	@Value("${spring.data.mongodb.username:null}")
	private String username;

	@Value("${spring.data.mongodb.password:null}")
	private String password;

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public Mongo mongo() throws Exception {

		MongoClient mongoClient = null;

		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
			MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
			mongoClient = new MongoClient(Arrays.asList(new ServerAddress(host, Integer.parseInt(port))),
					Arrays.asList(credential));
		} else {
			mongoClient = new MongoClient(Arrays.asList(new ServerAddress(host, Integer.parseInt(port))));
		}

		return mongoClient;
	}

}
