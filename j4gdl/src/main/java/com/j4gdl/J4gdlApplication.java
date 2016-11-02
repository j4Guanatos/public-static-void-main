package com.j4gdl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class J4gdlApplication {

	public static void main(String[] args) {
		SpringApplication.run(J4gdlApplication.class, args);
	}
}
