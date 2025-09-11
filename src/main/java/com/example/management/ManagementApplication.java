package com.example.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.example.management",
		"common"
})
@EnableJpaRepositories(basePackages = {
		"com.example.management",
		"common.repository"
})
@EntityScan(basePackages = {
		"com.example.management",
		"common.model"
})

public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
		System.out.println("\n\nApplication Compiled!!!!!\n");

	}

}
