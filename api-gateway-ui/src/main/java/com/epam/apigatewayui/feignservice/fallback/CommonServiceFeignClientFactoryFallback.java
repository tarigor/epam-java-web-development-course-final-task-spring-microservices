package com.epam.apigatewayui.feignservice.fallback;

import com.epam.apigatewayui.feignservice.CommonServiceFeignClient;
import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonServiceFeignClientFactoryFallback implements FallbackFactory<CommonServiceFeignClient> {
    @Override
    public CommonServiceFeignClient create(Throwable cause) {
        int status = ((FeignException) cause).status();
        if (cause.getClass().getName().contains("RetryableException")) {
            status = 503;
        }
        int finalStatus = status;
        System.out.println("final status ->" + finalStatus);
        return new CommonServiceFeignClient() {
            @Override
            public ResponseEntity<Object> doLogin(String email, String password) {
                return new ResponseEntity<>(HttpStatus.valueOf(finalStatus));
            }

            @Override
            public HttpStatus doRegistration(UserDataWhileRegistration user) {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public ResponseEntity<List<User>> getClients() {
                return new ResponseEntity<List<User>>(new ArrayList<>() {
                    @Override
                    public void add(int index, User element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);
            }
        };
    }
}
