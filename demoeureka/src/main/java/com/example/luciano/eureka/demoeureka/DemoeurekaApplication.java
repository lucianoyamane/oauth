package com.example.luciano.eureka.demoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoeurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoeurekaApplication.class, args);
	}
}
