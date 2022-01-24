package com.epam.requestorderservice.feignservice;


import com.epam.requestorderservice.model.EmailToAdmin;
import com.epam.requestorderservice.model.EmailToClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MAILING-SERVICE", url = "http://localhost:8091")
public interface EmailingServiceFeignClient {

    @PostMapping(value = "/email-to-admin", consumes = "application/json")
    void sendEmailToAdmin(@RequestBody EmailToAdmin emailToAdmin);

    @PostMapping(value = "/email-to-client", consumes = "application/json")
    void sendEmailToClient(@RequestBody EmailToClient emailToClient);
}
