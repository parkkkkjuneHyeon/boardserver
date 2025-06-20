package com.junhyun.boardwas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BoardwasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardwasApplication.class, args);
	}

}
