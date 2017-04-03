package com.pachimari.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
