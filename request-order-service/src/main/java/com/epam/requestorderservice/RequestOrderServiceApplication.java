package com.epam.requestorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RequestOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestOrderServiceApplication.class, args);
    }

}
