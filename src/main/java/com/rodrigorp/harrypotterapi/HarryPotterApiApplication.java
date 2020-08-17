package com.rodrigorp.harrypotterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HarryPotterApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarryPotterApiApplication.class, args);
    }

}
