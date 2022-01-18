package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.entity.User;
import com.epam.apigatewayui.model.UserDataWhileLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CommonUserActivityServiceClient {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonServiceFeignClient commonServiceFeignClient;

    public HttpStatus checkHttpStatusWhileLogin(UserDataWhileLogin userDataWhileLogin){
        return commonServiceFeignClient.doLogin(userDataWhileLogin.getEmail(), userDataWhileLogin.getPassword()).getStatusCode();
    }

    public ResponseEntity<Object> getUserWithHttpStatusCode(UserDataWhileLogin userDataWhileLogin){
        return commonServiceFeignClient.doLogin(userDataWhileLogin.getEmail(), userDataWhileLogin.getPassword());
    }

}
