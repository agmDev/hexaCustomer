package com.orness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.orness")
public class CustomerApi {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApi.class, args);
    }
}
