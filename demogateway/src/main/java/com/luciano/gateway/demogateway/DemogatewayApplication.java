package com.luciano.gateway.demogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class DemogatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemogatewayApplication.class, args);
	}
}
