package com.pachimari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableAutoConfiguration
public class PachimariApplication {

	public static void main(String[] args) {
		SpringApplication.run(PachimariApplication.class, args);
	}

}
