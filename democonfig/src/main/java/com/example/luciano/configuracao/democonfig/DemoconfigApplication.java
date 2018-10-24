package com.example.luciano.configuracao.democonfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DemoconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoconfigApplication.class, args);
	}
}
