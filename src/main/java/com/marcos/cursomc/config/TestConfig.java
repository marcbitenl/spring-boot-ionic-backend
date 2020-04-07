package com.marcos.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcos.cursomc.services.DBservice;
import com.marcos.cursomc.services.EmailService;
import com.marcos.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	
	@Autowired
	private DBservice dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
