package com.pachimari.order;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.model.OrderRepository;
import com.pachimari.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = {PachimariApplication.class, MongoConfigTest.class})
public class OrderRepositoryTest {
    @LocalServerPort
    private int localServerPort;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void init(){
        RestAssured.port = localServerPort;

        User user1 = User.builder().id("1").email("test@test.fr").name("test").login("test1").build();
        User user2 = User.builder().id("2").email("test2@test.fr").name("test2").login("test3").build();
        mongoTemplate.save(user1);
        mongoTemplate.save(user2);

        OrderEntity order1 = OrderEntity.builder().id(1).user(user1).amount(50).build();
        OrderEntity order2 = OrderEntity.builder().id(2).user(user1).amount(30).build();
        OrderEntity order3 = OrderEntity.builder().id(3).user(user2).amount(100).build();
        mongoTemplate.save(order1);
        mongoTemplate.save(order2);
        mongoTemplate.save(order3);
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.dropCollection(OrderEntity.class);
    }

    @Test
    public void should_find_all_orders(){
        List<OrderEntity> result = orderRepository.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    public void should_find_order_by_id(){
        Integer idOrder1 = 1;
        float amountExpectedOrder1 = 50;

        Integer idOrder2 = 2;
        float amountExpectedOrder2 = 30;

        Integer idOrder3 = 3;
        float amountExpectedOrder3 = 100;

        OrderEntity result = orderRepository.findById(idOrder1);
        assertThat(result.getAmount()).isEqualTo(amountExpectedOrder1);

        result = orderRepository.findById(idOrder2);
        assertThat(result.getAmount()).isEqualTo(amountExpectedOrder2);

        result = orderRepository.findById(idOrder3);
        assertThat(result.getAmount()).isEqualTo(amountExpectedOrder3);
    }

    @Test
    public void should_find_order_by_user(){
        String idUser1 = "1";
        int nbOrderExpectedUser1 = 2;
        String idUser2 = "2";
        int nbOrderExpectedUser2 = 1;

        List<OrderEntity> result = orderRepository.findByUser(idUser1);
        assertThat(result).hasSize(nbOrderExpectedUser1);

        result = orderRepository.findByUser(idUser2);
        assertThat(result).hasSize(nbOrderExpectedUser2);
    }
}
