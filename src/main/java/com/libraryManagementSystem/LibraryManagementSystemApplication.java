package com.libraryManagementSystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class LibraryManagementSystemApplication {
	
	@Value("${spring.datasource.url:NOT_SET}")
    private String dbUrl;


	
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
		
	}
	
	@PostConstruct
    public void logDbUrl() {
        System.out.println("spring.datasource.url = " + dbUrl);
    }


}
