package com.pachimari.category;

import com.jayway.restassured.RestAssured;
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

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryRespositoryTest {

    @LocalServerPort
    private int localServerPort;

    @Autowired
    CategoryRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void init() {
        mongoTemplate.dropCollection(Category.class);
        mongoTemplate.save(Category.builder().id(0).name("Phone").build());
        mongoTemplate.save(Category.builder().id(1).name("Tablet").build());
        RestAssured.port=localServerPort;
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Category.class);
    }

    @Test
    public void should_find_by_id() {
        Category category = Category.builder().id(0).name("Phone").build();
        Category res = repository.findById(0);
        assertThat(res.equals(category));
    }

    @Test
    public void should_find_by_name() {
        Category category = Category.builder().id(0).name("Tablet").build();
        Category res = repository.findByName("Tablet");
        assertThat(res.equals(category));
    }

    @Test
    public void should_find_all() {
        List<Category> listCategory = repository.findAll();
        assertThat(listCategory).hasSize(2);
    }



}
