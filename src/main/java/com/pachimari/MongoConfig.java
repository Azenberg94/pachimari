package com.pachimari;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends  AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "heroku_ncgf20l8";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI("mongodb://az:projetjee2017@ds117931.mlab.com:17931/"));
    }
}

