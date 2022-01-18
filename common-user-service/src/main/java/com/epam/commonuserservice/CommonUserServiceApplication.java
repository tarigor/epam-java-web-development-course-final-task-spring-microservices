package com.epam.commonuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommonUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonUserServiceApplication.class, args);
    }

}
