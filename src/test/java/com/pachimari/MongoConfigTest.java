package com.pachimari;

import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by andrem on 05/04/2017.
 */
@Configuration
public class MongoConfigTest extends AbstractMongoConfiguration {

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