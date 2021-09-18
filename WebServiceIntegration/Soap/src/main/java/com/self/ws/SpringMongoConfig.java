package com.self.ws;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class SpringMongoConfig extends AbstractMongoClientConfiguration{
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;
    
    @Value("${spring.data.mongodb.username}")
    private String mongoUser;
    @Value("${spring.data.mongodb.password}")
    private String password; 

    @Bean
    public MongoClient mongo() throws Exception { 
    	MongoCredential credential = MongoCredential.createCredential(mongoUser, mongoDB, password.toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToSslSettings(builder -> builder.enabled(true))
                .applyToClusterSettings(builder -> 
                    builder.hosts(Arrays.asList(new ServerAddress(mongoHost,Integer.parseInt(mongoPort)))))
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

	@Override
	protected String getDatabaseName() {
		return mongoDB;
	}

}
