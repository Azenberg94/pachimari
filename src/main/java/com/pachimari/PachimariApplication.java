package com.pachimari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class PachimariApplication {

	public static void main(String[] args) {
		SpringApplication.run(PachimariApplication.class, args);
	}
}
