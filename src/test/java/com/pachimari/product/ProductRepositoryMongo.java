package com.pachimari.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by andrem on 28/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryMongo {
    @Autowired
    ProductsRepository repository;

    ProductEntity product1, product2, product3;
    @Before
    public void init(){
        repository.deleteAll();

        product1 = repository.save(ProductEntity.builder().name("test1").brand("marque1").typeId(1).price(650.50).imageURL("images/sm01.jpg").build());
        product2 = repository.save(ProductEntity.builder().name("test2").brand("marque2").typeId(2).price(570.72).imageURL("images/sm01.jpg").build());
        product3 = repository.save(ProductEntity.builder().name("test3").brand("marque3").typeId(3).price(647.56).imageURL("images/sm01.jpg").build());
    }

    @Test
    public void should_find_all(){
        List<ProductEntity> result = repository.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    public void should_find_by_type(){
        List<ProductEntity> result = repository.findByOptionalParameters(null, null, 1);
        assertThat(result).hasSize(1);
        assertThat(result.contains(product1));
    }

    @Test
    public void should_find_by_name(){
        List<ProductEntity> result = repository.findByOptionalParameters("test3", null, null);
        assertThat(result).hasSize(1);
        assertThat(result.contains(product3));
    }

    @Test
    public void should_find_by_brand(){
        List<ProductEntity> result = repository.findByOptionalParameters(null, "marque2", null);
        assertThat(result).hasSize(1);
        assertThat(result.contains(product2));
    }
}
