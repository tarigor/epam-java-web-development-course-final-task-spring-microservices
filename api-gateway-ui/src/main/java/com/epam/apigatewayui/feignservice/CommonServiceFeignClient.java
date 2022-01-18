package com.epam.apigatewayui.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "COMMON-USER-SERVICE", url = "http://localhost:8084")
public interface CommonServiceFeignClient {

    @GetMapping(value = "/api/login/{email}/{password}", consumes = "application/json")
    @ResponseBody
    ResponseEntity<Object> doLogin(@PathVariable String email, @PathVariable String password);
}
