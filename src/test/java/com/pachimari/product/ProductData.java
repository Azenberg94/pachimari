package com.pachimari.product;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by andrem on 23/03/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.TYPE)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:product-init.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:product-cleanup.sql")
public @interface ProductData {

}
