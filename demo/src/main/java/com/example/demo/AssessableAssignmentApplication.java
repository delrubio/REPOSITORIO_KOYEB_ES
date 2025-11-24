package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Manager.Manager;

@SpringBootApplication
public class AssessableAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessableAssignmentApplication.class, args);
		
	}

	@Bean
    CommandLineRunner initData(){
        return args ->{
            Manager.getInstance();
        };
    }
}
