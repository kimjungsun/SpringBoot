package com.ecommerce.j3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@Slf4j
@SpringBootApplication
public class J3Application {
	//public static final String APPLICATION_LOCATIONS = "spring.config.location=" + "classpath:application2.yml," + "/Users/user/Desktop/spring-backend-main 2/src/main/resources/application2.yml";

	public static void main(String[] args) {

		//new SpringApplicationBuilder(J3Application.class) .properties(APPLICATION_LOCATIONS) .run(args);

		SpringApplication.run(J3Application.class, args);
	}

}
