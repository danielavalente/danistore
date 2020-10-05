package com.store.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.store.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DbService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instatiateTestDatabase();
		return true;
	}
}