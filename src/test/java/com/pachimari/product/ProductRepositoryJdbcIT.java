package com.pachimari.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by andrem on 23/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductRepositoryJdbc.class)
@DataJpaTest
@ProductData
public class ProductRepositoryJdbcIT {
    @Autowired
    ProductRepositoryJdbc productRepositoryJdbc;

    @Test
    public void should_find_all(){
        List<ProductEntity> productEntities = productRepositoryJdbc.findAll();
        assertThat(productEntities).isNotNull();
    }
}
