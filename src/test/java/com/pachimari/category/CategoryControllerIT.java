package com.pachimari.category;

import com.jayway.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
//@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CategoryControllerIT {

    @LocalServerPort
    private int localServerPort;

    @Autowired
    CategoryRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void init() {
        mongoTemplate.dropCollection(Category.class);
        mongoTemplate.save(Category.builder().id("0").name("Phone").build());
        mongoTemplate.save(Category.builder().id("1").name("Tablet").build());
        RestAssured.port=localServerPort;
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Category.class);
    }


    @Test
    public void should_create_category() {

        CategoryDTO categoryDTO = CategoryDTO.builder().id("1").name("Phone").build();

        given().log().all().contentType(JSON).body(categoryDTO)
                .when().post("/categories")
                .then().statusCode(201)
                .body("name", is("Phone"))
                .log().all();
    }

    @Test
    public void should_get_all_categories() {

        given().log().all()
        .when().get("/categories")
                .then().log().all().statusCode(200);

    }

    @Test
    public void should_delete_category() {

        given().log().all().contentType(JSON).body(1).when()
                .delete("/categories")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void should_update_category() {

        CategoryDTO categoryDTO = CategoryDTO.builder().id("1").name("Computer").build();
        given().log().all().contentType(JSON).body(categoryDTO).when()
                .put("/categories")
                .then()
                .statusCode(200)
                .body("id",is("1"))
                .body("name",is("Computer")).log().all();

    }

}
