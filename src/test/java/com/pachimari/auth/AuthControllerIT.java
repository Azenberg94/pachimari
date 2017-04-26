package com.pachimari.auth;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by iPlowPlow on 10/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = {PachimariApplication.class,MongoConfigTest.class})
public class AuthControllerIT {
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
    public void should_update_auth(){

        AuthDto authDto= AuthDto.builder().id("1").login("login1Upd").pwd("pwd1").build();

        given().log().all().contentType(JSON).body(authDto).when()
                .put("/auth")
                .then()
                .statusCode(200)

                .body("id",is("1"))
                .body("login",is("login1Upd")).log().all();

    }

    @Test
    public void should_create_auth(){

        AuthDto authDto= AuthDto.builder().id("3").login("login3").pwd("pwd3").build();

        given().log().all().contentType(JSON).body(authDto).when()
                .post("/auth/add")
                .then()
                .statusCode(201)
                .body("login",is("login3")).log().all();

    }

    @Test
    public void should_delete_auth(){

        given().log().all().contentType(JSON).body("login1").when()
                .delete("/auth")
                .then()
                .statusCode(200)
                .body("login",is("login1")).log().all();
    }

    @Test
    public void should_get_one_auth(){
        given().log().all().body("login2").when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("id",is("2")).log().all();
    }


}