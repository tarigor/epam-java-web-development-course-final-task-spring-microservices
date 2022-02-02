package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.feignservice.fallback.CommonServiceFeignClientFactoryFallback;
import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@Primary
@FeignClient(name = "COMMON-USER-SERVICE", url = "http://localhost:8084", fallbackFactory = CommonServiceFeignClientFactoryFallback.class)
public interface CommonServiceFeignClient {

    @GetMapping(value = "/api/login/{email}/{password}", consumes = "application/json")
    ResponseEntity<Object> doLogin(@PathVariable String email, @PathVariable String password);

    @PostMapping(value = "/api/registration", consumes = "application/json")
    void doRegistration(@RequestBody UserDataWhileRegistration user);

    @GetMapping(value = "/api/get-clients")
    List<User> getClients();

//    @Component
//    class ServiceFallback implements CommonServiceFeignClient{
//
//        @Override
//        public ResponseEntity<Object> doLogin(String email, String password){
//            System.out.println("doLogin failed");
////           throw new ServiceUnavailableException("Sorry, but login service temporally not available, please try later");
//            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
//        }
//
//        @Override
//        public void doRegistration(UserDataWhileRegistration user) {
//            System.out.println("doRegistration failed");
//        }
//
//        @Override
//        public List<User> getClients() {
//            System.out.println("getClients failed");
//            return null;
//        }
//    }
}


