package com.pachimari.product;

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
import static org.hamcrest.Matchers.hasSize;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by andrem on 23/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = {PachimariApplication.class,MongoConfigTest.class})
public class ProductControllerIT {
    @LocalServerPort
    private int localServerPort;
    @Autowired
    ProductRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Before
    public void init(){
        mongoTemplate.dropCollection(ProductEntity.class);

        mongoTemplate.save(ProductEntity.builder().id("1").name("test1").brand("marque1").typeId("1").price(650.50).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("2").name("test2").brand("marque2").typeId("2").price(570.72).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("3").name("test3").brand("marque3").typeId("3").price(647.56).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("4").name("test4").brand("marque3").typeId("1").price(620.56).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("5").name("test3").brand("marque4").typeId("1").price(677.56).imageURL("images/sm01.jpg").build());
        RestAssured.port=localServerPort;
    }
    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(ProductEntity.class);
    }

    @Test
    public void should_get_all(){
        given().log().all()
                .when().get("/product").then().log().all()
                .statusCode(200).body("$", hasSize(5));
    }
    @Test
    public void should_create_product(){

        ProductDto productDTO=ProductDto.builder().id("5").name("test3").brand("marque4").typeId("1").price(677.56).imageURL("images/sm01.jpg").build();

        given().log().all().contentType(JSON).body(productDTO).when()
                .post("/product")
                .then()
                .statusCode(201)
                .body("typeId", is("1"))
                .body("brand", is("marque4"))
                .body("name",is("test3")).log().all();
    }
    @Test
    public void should_delete_product(){
        given().log().all().contentType(JSON).body("1").when()
                .delete("/product")
                .then()
                .statusCode(200)
                .body("typeId", is("1"))
                .body("brand", is("marque1"))
                .body("name",is("test1")).log().all();
    }
    @Test
    public void should_get_product_by_filter(){
        ProductDto productDto = ProductDto.builder().id("2").name("test2").brand("marque2").typeId("2").price(570.72).imageURL("images/sm01.jpg").build();
        given().log().all()
                .when().get("/product/find/test4/any/any").then().log().all()
                .statusCode(200)
                .body("$", hasSize(1));
    }
    @Test
    public void should_update_product(){
        ProductDto productDTO=ProductDto.builder().id("1").name("test6").brand("marque6").typeId("6").price(644.56).imageURL("images/sm01.jpg").build();
        given().log().all().contentType(JSON).body(productDTO).when()
                .put("/product")
                .then()
                .statusCode(200)
                .body("id",is("1"))
                .body("typeId", is("6"))
                .body("brand", is("marque6"))
                .body("name",is("test6")).log().all();
    }
}
