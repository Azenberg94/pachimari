package com.pachimari.auth;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by iPlowPlow on 10/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthRepositoryJdbc.class)
@DataJpaTest
@AuthData
public class AuthRepositoryJdbcIt {

    @Autowired
    AuthRepositoryJdbc authRepositoryJdbc;

    @Test
    public void should_find_one(){
        String login = "test1";
        String pwd = "test1";
        AuthEntity authEntity = authRepositoryJdbc.tryAuth(login, pwd);
        assertThat(authEntity).isNotNull();
    }

    @Test
    @DirtiesContext
    public void should_create_account(){
        AuthDto dto = AuthDto.builder()
                .id(Long.valueOf(4))
                .login("testInsertLogin")
                .pwd("testInsertMdp")
                .build();
        authRepositoryJdbc.addAuth(dto.getLogin(), dto.getPwd());
        AuthEntity authEntity = authRepositoryJdbc.tryAuth(dto.getLogin(), "testInsertMdp");
        assertThat(authEntity).isNotNull();
    }

    @Test
    public void should_update_pwd(){
        authRepositoryJdbc.updateAuth("test1", "testUpdatePwd");
        AuthEntity authEntity = authRepositoryJdbc.tryAuth("test1", "testUpdatePwd");
        assertThat(authEntity).isNotNull();
    }

    public void should_insert_one(){
        String login = "test1";
        String pwd = "test1";
        authRepositoryJdbc.addAuth(login, pwd);

    }
}
