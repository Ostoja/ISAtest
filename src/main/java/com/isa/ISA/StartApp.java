package com.isa.ISA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StartApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(StartApp.class, args);
	}

}
