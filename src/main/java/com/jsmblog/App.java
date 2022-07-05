package com.jsmblog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsmblog.utility.CreateRoles;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {
	
	@Autowired
	private CreateRoles createRoles;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("JSMedia7 - Blogging Core Web App Initialised at {}", new Date());
		this.createRoles.addRolesToDB();
		log.info("Roles added to Database {}", new Date());
	}

}
