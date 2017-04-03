package com.pachimari;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.github.fakemongo.Fongo;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
<<<<<<< HEAD
/*
@Configuration
@EnableMongoRepositories(basePackages = "com.pachimari.user.repository")

=======
@Configuration
>>>>>>> a4e2acbd441a0930c399f790b619c38103b552ae
public class MongoConfigTest extends  AbstractMongoConfiguration {


    @Override
    public String getDatabaseName() {
        return "pachimari-test";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
<<<<<<< HEAD
        return new Fongo(getDatabaseName()).getMongo();
    }

}*/
=======
        return new MongoClient("127.0.0.1");
    }

}
>>>>>>> a4e2acbd441a0930c399f790b619c38103b552ae
