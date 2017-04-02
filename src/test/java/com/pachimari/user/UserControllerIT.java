package com.pachimari.user;
import com.github.fakemongo.Fongo;
import com.jayway.restassured.RestAssured;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.pachimari.MongoConfigTest;
import com.pachimari.user.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by Pierre on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes =  MongoConfigTest.class)
@EnableAutoConfiguration
public class UserControllerIT {
    @LocalServerPort
    private int localServerPort;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    UserRepository repository;
    @Before
    public void init(){
        mongoTemplate.dropCollection(User.class);
        RestAssured.port=localServerPort;
    }
    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(User.class);
    }
    @Test
    public void should_get_list_user(){
        given().log().all().when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("$",hasSize(0)).log().all();
    }

    @Test
    public void should_create_user(){

        UserDTO userDTO=UserDTO.builder().id(5).name("fifth").login("test").email("test@email.fr").build();

        given().log().all().contentType(JSON).body(userDTO).when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("id",is(5))
                .body("name",is("fifth")).log().all();
    }
    @Test
    public void should_fail_get_list_user(){

        given().log().all().when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("$",hasSize(3)).log().all();
    }


}

