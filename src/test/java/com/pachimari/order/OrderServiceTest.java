package com.pachimari.order;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.order.model.OrderDTO;
import com.pachimari.order.service.OrderService;
import com.pachimari.product.ProductEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {PachimariApplication.class, MongoConfigTest.class})
public class OrderServiceTest {
    OrderDTO orderDTO;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    OrderService orderServiceImpl;

    @Before
    public void init(){
        RestAssured.port = localServerPort;

        List<ProductEntity> itemEntityList = new ArrayList<>();

        ProductEntity item1 = ProductEntity.builder()
                            .id("1")
                            .name("Item 1")
                            .price((double) 5)
                            .build();

        ProductEntity item2 = ProductEntity.builder()
                .id("2")
                .name("Item 2")
                .price((double) 5)
                .build();

        itemEntityList.add(item1);
        itemEntityList.add(item2);

        orderDTO = OrderDTO.builder()
                        .id("1")
                        .items(itemEntityList)
                        .build();
    }

    @Test
    public void should_calculate_the_right_amount(){
        orderServiceImpl.calculateAmountOrder(orderDTO);

        assertThat(orderDTO.getAmount()).isEqualTo(10);
    }
}
