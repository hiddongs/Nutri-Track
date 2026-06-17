package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages = {"com.example.demo", "com.nutriTrack"})
@EnableJpaRepositories(basePackages = {"com.nutriTrack"})
@EntityScan(basePackages = {"com.nutriTrack"})
public class NutriTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutriTrackApplication.class, args);
    }
}