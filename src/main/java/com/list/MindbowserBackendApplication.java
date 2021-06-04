package com.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.list.entity.Registration;
import com.list.service.RegistrationService;

@SpringBootApplication
public class MindbowserBackendApplication //implements CommandLineRunner
{
	//@Autowired
	//private RegistrationService registrationService;
	public static void main(String[] args) {
		SpringApplication.run(MindbowserBackendApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception { // TODO
	 * Auto-generated method stub System.out.println("start code"); }
	 * 
	 */

}
