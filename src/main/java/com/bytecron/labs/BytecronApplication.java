package com.bytecron.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BytecronApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytecronApplication.class, args);
	}

}
