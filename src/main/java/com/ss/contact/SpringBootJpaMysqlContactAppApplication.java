package com.ss.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootJpaMysqlContactAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMysqlContactAppApplication.class, args);
	}

}
