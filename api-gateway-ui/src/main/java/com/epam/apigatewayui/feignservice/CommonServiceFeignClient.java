package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "COMMON-USER-SERVICE", url = "http://localhost:8084")
public interface CommonServiceFeignClient {

    @GetMapping(value = "/api/login/{email}/{password}", consumes = "application/json")
    ResponseEntity<Object> doLogin(@PathVariable String email, @PathVariable String password);

    @PostMapping(value = "/api/registration", consumes = "application/json")
    void doRegistration(@RequestBody UserDataWhileRegistration user);

    @GetMapping(value = "/api/get-clients")
    List<User> getClients();
}
