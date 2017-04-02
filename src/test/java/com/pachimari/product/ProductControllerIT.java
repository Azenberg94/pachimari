package com.pachimari.product;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by andrem on 23/03/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ProductData
public class ProductControllerIT {
    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    public void should_get_all(){
        given().log().all()
                .when().get("/product").then().log().all()
                .statusCode(200).body("$", hasSize(3));
    }
}
