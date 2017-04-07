package com.pachimari.user;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfig;

import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.user.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Java6Assertions.assertThat;
import java.util.List;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by Pierre on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = {PachimariApplication.class,MongoConfigTest.class})
public class UserRepositoryTest {
    @LocalServerPort
    private int localServerPort;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    UserRepository repository;
    @Before
    public void init(){
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.save(User.builder().id("1").email("test@test.fr").name("test").login("test1").addresse("36 rue houdart").ville("roissy").cp("95700").build());
        mongoTemplate.save(User.builder().id("2").email("test2@test.fr").name("test2").login("test3").addresse("36 rue cdg").ville("roissy").cp("95700").build());
        RestAssured.port=localServerPort;
    }
    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(User.class);
    }
    @Test
    public void should_find_all(){
        List<User> result=repository.findAll();
        assertThat(result).hasSize(2);
    }
    @Test
    public void should_find_by_id(){
        User user=User.builder().email("test2@test.fr").name("test2").login("test2").build();
      User result=repository.findById("1");
      assertThat(result.equals(user));
    }

    @Test
    public void should_findbyname(){

        User user=User.builder().email("test2@test.fr").name("test2").login("test2").build();
        User result=repository.findByName("test2");
        assertThat(result.equals(user));
    }
    @Test
    public void should_findbylogin(){

        User user=User.builder().email("test2@test.fr").name("test2").login("test3").build();
        User result=repository.findByLogin("test3");
        assertThat(result.equals(user));
    }
    @Test
    public void should_findbyType(){

        User user=User.builder().email("test2@test.fr").name("test2").login("test3").type("admin").build();
        List<User> result=repository.findByType("test3");
        assertThat(result.contains(user));
    }





}
