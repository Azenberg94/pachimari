package com.pachimari.order;

import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.item.model.ItemEntity;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {PachimariApplication.class,MongoConfigTest.class})
public class OrderServiceTest {
    OrderEntity orderEntity;

    @Autowired
    OrderService orderServiceImpl;

    @Before
    public void init(){
        List<ItemEntity> itemEntityList = new ArrayList<>();

        ItemEntity item1 = ItemEntity.builder()
                            .itemId(1)
                            .itemName("Item 1")
                            .itemPrice((float) 5)
                            .build();

        ItemEntity item2 = ItemEntity.builder()
                .itemId(2)
                .itemName("Item 2")
                .itemPrice((float) 5)
                .build();

        itemEntityList.add(item1);
        itemEntityList.add(item2);

        orderEntity = OrderEntity.builder()
                        .id(1)
                        .items(itemEntityList)
                        .build();
    }

    @Test
    public void should_calculate_the_right_amount(){
        orderServiceImpl.calculateAmountOrder(orderEntity);

        assertThat(orderEntity.getAmount()).isEqualTo(10);
    }
}