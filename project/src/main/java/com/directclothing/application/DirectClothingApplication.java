package com.directclothing.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.directclothing.application", "com.directclothing.controller", "com.directclothing.service"})
public class DirectClothingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DirectClothingApplication.class, args);
    }
}