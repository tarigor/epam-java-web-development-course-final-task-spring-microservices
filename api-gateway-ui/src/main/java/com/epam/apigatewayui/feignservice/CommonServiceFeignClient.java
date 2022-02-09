package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.feignservice.fallback.CommonServiceFeignClientFactoryFallback;
import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Primary
@FeignClient(name = "COMMON-USER-SERVICE", url = "http://localhost:8084", fallbackFactory = CommonServiceFeignClientFactoryFallback.class)
public interface CommonServiceFeignClient {

    @GetMapping(value = "/api/login/{email}/{password}", consumes = "application/json")
    ResponseEntity<Object> doLogin(@PathVariable String email, @PathVariable String password);

    @PostMapping(value = "/api/registration", consumes = "application/json")
    HttpStatus doRegistration(@RequestBody UserDataWhileRegistration user);

    @GetMapping(value = "/api/get-clients")
    ResponseEntity<List<User>> getClients();
}


