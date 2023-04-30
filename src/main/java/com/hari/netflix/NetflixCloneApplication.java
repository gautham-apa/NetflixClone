package com.hari.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan
public class NetflixCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneApplication.class, args);
	}

}
