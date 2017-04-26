package com.pachimari.product;

import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by andrem on 28/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = {PachimariApplication.class,MongoConfigTest.class})
public class ProductRepositoryTest {
    @Autowired
    ProductRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void init(){
        mongoTemplate.dropCollection(ProductEntity.class);
        repository.deleteAll();

        mongoTemplate.save(ProductEntity.builder().id("1").name("test1").brand("marque1").typeId("1").price(650.50).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("2").name("test2").brand("marque2").typeId("2").price(570.72).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("3").name("test3").brand("marque3").typeId("3").price(647.56).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("4").name("test4").brand("marque3").typeId("1").price(620.56).imageURL("images/sm01.jpg").build());
        mongoTemplate.save(ProductEntity.builder().id("5").name("test3").brand("marque4").typeId("1").price(677.56).imageURL("images/sm01.jpg").build());
    }
    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(ProductEntity.class);
    }
    @Test
    public void should_find_all(){
        List<ProductEntity> result = repository.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    public void should_find_by_type(){
        ProductEntity product = ProductEntity.builder().id("2").name("test2").brand("marque2").typeId("2").price(570.72).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters(null, null, "2");
        assertThat(result).hasSize(1);
        assertThat(result.contains(product));
    }
    @Test
    public void should_find_by_type_and_name(){
        ProductEntity product = ProductEntity.builder().id("5").name("test3").brand("marque4").typeId("1").price(677.56).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters("test3", null, "1");
        assertThat(result).hasSize(1);
        assertThat(result.contains(product));
    }

    @Test
    public void should_find_by_name(){
        ProductEntity product = ProductEntity.builder().id("4").name("test4").brand("marque3").typeId("1").price(620.56).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters("test4", null, null);
        assertThat(result).hasSize(1);
        assertThat(result.contains(product));
    }
    @Test
    public void should_find_by_brand_and_name(){
        ProductEntity product =ProductEntity.builder().id("3").name("test3").brand("marque3").typeId("3").price(647.56).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters("test3", "marque3", null);
        assertThat(result).hasSize(1);
        assertThat(result.contains(product));
    }
    @Test
    public void should_find_by_brand(){
        ProductEntity product =ProductEntity.builder().id("3").name("test3").brand("marque3").typeId("3").price(647.56).imageURL("images/sm01.jpg").build();
        ProductEntity product2 =ProductEntity.builder().id("4").name("test4").brand("marque3").typeId("1").price(620.56).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters(null, "marque3", null);
        assertThat(result).hasSize(2);
        assertThat(result.contains(product));
        assertThat(result.contains(product2));
    }
    @Test
    public void should_find_by_brand_and_type(){
        ProductEntity product = ProductEntity.builder().id("1").name("test1").brand("marque1").typeId("1").price(650.50).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters(null, "marque1", "1");
        assertThat(result).hasSize(1);
        assertThat(result.contains(product));
    }
    @Test
    public void should_find_by_brand_and_type_and_name(){
        ProductEntity product = ProductEntity.builder().id("5").name("test3").brand("marque4").typeId("1").price(677.56).imageURL("images/sm01.jpg").build();
        List<ProductEntity> result = repository.findByOptionalParameters("test3", "marque4", "1");
        assertThat(result).hasSize(1);
        assertThat(result.contains(product));
    }
}