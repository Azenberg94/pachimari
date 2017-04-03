package com.pachimari;


import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.Mongo;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
public class MongoConfigTest extends  AbstractMongoConfiguration {
    @Autowired
    private Environment env;

    @Override
    public String getDatabaseName() {
        return "pachimari-test";
    }

    @Override
    @Bean(destroyMethod="close")
    public Mongo mongo() throws Exception {
        return new EmbeddedMongoBuilder()
                .bindIp("127.0.0.1")
                .port(12345)
                .build();
    }

}
