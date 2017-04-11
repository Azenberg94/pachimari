package com.pachimari.auth;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by iPlowPlow on 10/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = {PachimariApplication.class,MongoConfigTest.class})
public class AuthRepositoryTest {
    @LocalServerPort
    private int localServerPort;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    AuthRepository repository;
    @Before
    public void init(){
        mongoTemplate.dropCollection(AuthEntity.class);
        mongoTemplate.save(AuthEntity.builder().id("1").login("login1").pwd("pwd1").build());
        mongoTemplate.save(AuthEntity.builder().id("2").login("login2").pwd("pwd2").build());
        RestAssured.port=localServerPort;
    }
    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(AuthEntity.class);
    }

    @Test
    public void should_find_by_id(){
        AuthEntity authEntity = AuthEntity.builder().id("1").login("login1").pwd("pwd1").build();
        AuthEntity result=repository.findById("1");
        assertThat(result.equals(authEntity));
    }

    @Test
    public void should_find_by_login(){

        AuthEntity authEntity = AuthEntity.builder().id("2").login("login2").pwd("pwd2").build();
        AuthEntity result=repository.findByLogin("login2");
        assertThat(result.equals(authEntity));
    }

}
