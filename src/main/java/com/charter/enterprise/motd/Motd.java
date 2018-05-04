package com.charter.enterprise.motd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.charter.enterprise.motd.model.Message;

@SpringBootApplication
public class Motd {

    @Bean
    public Message messageOfTheDay() {
        return new Message("Welcome to Charter. All systems are normal.");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Started Spring Boot Message of The Day");

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Motd.class, args);
    }
}