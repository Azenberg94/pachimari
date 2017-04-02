package com.pachimari.auth;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by iPlowPlow on 10/03/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.TYPE)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:auth-init.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:auth-cleanup.sql")
public @interface AuthData {

}


