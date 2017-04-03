package com.pachimari.user;

import com.jayway.restassured.RestAssured;
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

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by Pierre on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
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
        mongoTemplate.save(User.builder().id(0).email("test@test.fr").name("test").login("test1").build());
        mongoTemplate.save(User.builder().id(1).email("test2@test.fr").name("test2").login("test2").build());
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

                .body("$",hasSize(2)).log().all();
    }
    @Test
    public void should_update_user(){
        UserDTO userDTO= UserDTO.builder().id(1).email("test3@test.fr").name("test3").login("test2").build();
        given().log().all().contentType(JSON).body(userDTO).when()
                .put("/user")
                .then()
                .statusCode(200)
                .body("id",is(1))
                .body("name",is("test3")).log().all();
    }

    @Test
    public void should_create_user(){

        UserDTO userDTO=UserDTO.builder().id(5).name("fifth").login("test").email("test@email.fr").type("admin").build();

        given().log().all().contentType(JSON).body(userDTO).when()
                .post("/user")
                .then()
                .statusCode(201)
                .body("name",is("fifth")).log().all();
    }

    @Test
    public void should_delete_user(){

        given().log().all().contentType(JSON).body(0).when()
                .delete("/user")
                .then()
                .statusCode(200)
                .body("id",is(0))
                .body("name",is("test")).log().all();
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
