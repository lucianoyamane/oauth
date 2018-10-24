package com.luciano.oauth.demooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class DemooauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemooauthApplication.class, args);
	}
}
