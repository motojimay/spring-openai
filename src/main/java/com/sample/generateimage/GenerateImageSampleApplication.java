package com.sample.generateimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GenerateImageSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenerateImageSampleApplication.class, args);
    }
}
