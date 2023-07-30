package com.example.rediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RediscacheApplication {

	public static void main(String[] args) {
		System.out.println("started the application...");
		SpringApplication.run(RediscacheApplication.class, args);
	}

}
