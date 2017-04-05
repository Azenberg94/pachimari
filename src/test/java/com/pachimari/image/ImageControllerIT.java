package com.pachimari.image;

import com.mongodb.gridfs.GridFSDBFile;
import com.pachimari.product.ProductEntity;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by andrem on 04/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ImageControllerIT {
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Test
    public void should_create_image(){
        File image = new File("src/test/resources/pachimari.jpg");
        try {
            FileInputStream inputStream = new FileInputStream(image);
            System.out.println(inputStream.available());
            String id =
                    gridFsTemplate.store(inputStream, "pachimari.jpg", "image").getId().toString();
            given().log().all().contentType(JSON).body(inputStream).when()
                    .post("/image")
                    .then()
                    .statusCode(201)
                    .log().all();
        }
        catch(Exception e){
            assertThat(0).isEqualTo(1);
        }
    }
    @Test public void shouldListExistingFiles() {
        List<GridFSDBFile> files = gridFsTemplate.find(null);
        System.out.println("FILES =================================================");
        for (GridFSDBFile file: files) {
            System.out.println("FILE =================================================");
            System.out.println(file);
        }
    }
    @After
    public void tearDown() throws Exception {
        //mongoTemplate.dropCollection(ProductEntity.class);
    }
}
