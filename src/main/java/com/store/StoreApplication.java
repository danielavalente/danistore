package com.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.store.services.S3Service;

@SpringBootApplication
public class StoreApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;	
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				
	}
	
	
}
