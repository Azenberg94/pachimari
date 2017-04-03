package com.pachimari.auth;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by iPlowPlow on 10/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthControllerIt {

    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }


    @Test
    public void should_get_one(){
        AuthDto dto = AuthDto.builder()
            .login("test1")
            .pwd("test2")
            .build();
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post("/auth")
        .then()
            .log().all()
            .statusCode(200)
            .body("$",equalTo(0));
    }

}
