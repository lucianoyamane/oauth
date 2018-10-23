package com.luciano.oauth.demoresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class DemoresourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoresourceApplication.class, args);
	}
}
