package com.example.wasylewskigithubconsumerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WasylewskiGithubConsumerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WasylewskiGithubConsumerApiApplication.class, args);
    }

}
