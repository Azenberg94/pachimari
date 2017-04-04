package com.pachimari.auth;

import com.jayway.restassured.RestAssured;
import com.pachimari.MongoConfigTest;
import com.pachimari.PachimariApplication;
import com.pachimari.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by iPlowPlow on 10/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {MongoConfigTest.class, PachimariApplication.class})
public class AuthRepositoryIt {

    @LocalServerPort
    private int localServerPort;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void init(){
        mongoTemplate.dropCollection(AuthEntity.class);
        mongoTemplate.save(AuthEntity.builder().id("1").login("test1").pwd("test1").build());
        mongoTemplate.save(AuthEntity.builder().id("2").login("test2").pwd("test2").build());
        RestAssured.port=localServerPort;
    }
    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(AuthEntity.class);
    }

    @Test
    public void should_find_one(){
        String login = "test1";
        String pwd = "test1";
        AuthEntity authEntity = authRepository.findByLoginAndPwd(login, pwd);
        assertThat(authEntity).isNotNull();
    }

    @Test
    @DirtiesContext
    public void should_create_account(){
        AuthDto dto = AuthDto.builder()
                .id("25")
                .login("testInsertLogin")
                .pwd("testInsertMdp")
                .build();

        authRepository.save(AuthAdapter.toAuthEntity(dto));
        AuthEntity authEntity = authRepository.findByLoginAndPwd(dto.getLogin(), "testInsertMdp");
        assertThat(authEntity).isNotNull();
    }

    @Test
    public void should_update_pwd(){
        AuthDto dto = AuthDto.builder()
                .id("25")
                .login("testInsertLogin")
                .pwd("testUpdateMdp")
                .build();
        authRepository.save(AuthAdapter.toAuthEntity(dto));
        AuthEntity authEntity = authRepository.findByLoginAndPwd(dto.getLogin(), "testUpdateMdp");
        assertThat(authEntity).isNotNull();
    }

}
