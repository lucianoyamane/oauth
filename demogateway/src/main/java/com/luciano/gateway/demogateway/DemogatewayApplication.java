package com.luciano.gateway.demogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableZuulProxy
public class DemogatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemogatewayApplication.class, args);
	}
}
