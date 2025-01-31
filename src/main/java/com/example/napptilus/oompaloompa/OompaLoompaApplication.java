package com.example.napptilus.oompaloompa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication
@EnableCircuitBreaker
public class OompaLoompaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OompaLoompaApplication.class, args);
    }

}
