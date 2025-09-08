package com.outerbean.outerbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan(basePackages = {"com.example.courserecommender", "com.outerbean"})

public class OuterbeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(OuterbeanApplication.class, args);
	}

}
