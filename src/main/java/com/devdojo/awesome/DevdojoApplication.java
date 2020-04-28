package com.devdojo.awesome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan @Configuration @EnableAutoConfiguration
public class DevdojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevdojoApplication.class, args);
	}
}
