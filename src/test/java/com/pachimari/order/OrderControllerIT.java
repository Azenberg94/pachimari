package com.pachimari.order;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.order.model.OrderDTO;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.model.OrderRepository;
import com.pachimari.user.User;
import com.pachimari.user.UserDTO;
import com.pachimari.user.repository.UserRepository;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {PachimariApplication.class, MongoConfigTest.class})
public class OrderControllerIT {
    @LocalServerPort
    private int localServerPort;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void init() throws Exception{
        RestAssured.port = localServerPort;

        User user1 = User.builder().id("1").login("az").build();
        User user2 = User.builder().id("2").login("lolo").build();

        mongoTemplate.save(user1);
        mongoTemplate.save(user2);
        mongoTemplate.save(OrderEntity.builder().id(1).amount(100).user(user1).build());
        mongoTemplate.save(OrderEntity.builder().id(2).amount(30).user(user1).build());
        mongoTemplate.save(OrderEntity.builder().id(3).amount(50).user(user2).build());
    }

    @After
    public void tearDown() throws Exception{
        mongoTemplate.dropCollection(OrderEntity.class);
    }

    @Test
    public void should_get_all_orders(){
        given().log()
                .all()
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("$", hasSize(3)).log().all();
    }

    @Test
    public void should_get_user_orders(){
        String user1Login = "az";
        int nbOrdersExpectedForUser1 = 2;

        String user2Login = "lolo";
        int nbOrdersExpectedForUser2 = 1;

        given().log()
                .all()
                .when()
                .get("/orders/users/" + user1Login)
                .then()
                .statusCode(200)
                .body("$", hasSize(nbOrdersExpectedForUser1)).log().all();

        given().log()
                .all()
                .when()
                .get("/orders/users/" + user2Login)
                .then()
                .statusCode(200)
                .body("$", hasSize(nbOrdersExpectedForUser2)).log().all();
    }

    @Test
    public void should_get_one_order(){
        Integer idToFind = 1;
        float amountToFind = 100;
        String userLoginToFind = "az";

        when()
                .get("/orders/id/" + idToFind)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(idToFind))
                .body("amount", is(amountToFind))
                .body("user.login", is(userLoginToFind));
    }

    @Test
    @DirtiesContext
    public void should_create_order(){
        OrderDTO orderDTO = OrderDTO.builder()
                                .id(4)
                                .amount(70)
                                .build();


        given().log().all().contentType(JSON).body(orderDTO).when()
                .post("/orders/")
                .then()
                .statusCode(201)
                .body("id",is(4))
                .log()
                .all();
    }
}
