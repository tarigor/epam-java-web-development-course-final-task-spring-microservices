package com.epam.apigatewayui.feignservice.fallback;

import com.epam.apigatewayui.feignservice.CommonServiceFeignClient;
import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonServiceFeignClientFactoryFallback implements FallbackFactory<CommonServiceFeignClient> {
    @Override
    public CommonServiceFeignClient create(Throwable cause) {
        int status = ((FeignException)cause).status();
        if(cause.getClass().getName().contains("RetryableException")){
            status = 503;
        }
        int finalStatus = status;
        return new CommonServiceFeignClient() {
            @Override
            public ResponseEntity<Object> doLogin(String email, String password) {
                return new ResponseEntity<>(HttpStatus.valueOf(finalStatus));
            }

            @Override
            public void doRegistration(UserDataWhileRegistration user) {
throw  new FeignException.ServiceUnavailable(" ",);
            }

            @Override
            public List<User> getClients() {
                return null;
            }
        };
    }
}
