package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileLogin;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceFeignClientService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonServiceFeignClient commonServiceFeignClient;

    public ResponseEntity<Object> getUserWithStatusCode(UserDataWhileLogin userDataWhileLogin) {
        return commonServiceFeignClient.doLogin(
                userDataWhileLogin.getEmail(),
                userDataWhileLogin.getPassword());
    }

    public void doRegistration(UserDataWhileRegistration user) {
        UserDataWhileRegistration userDataWhileRegistration = new UserDataWhileRegistration(
                user.getFirstName(),
                user.getLastName(),
                user.getUserType(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                "");
        commonServiceFeignClient.doRegistration(userDataWhileRegistration);
    }

    public List<User> getClientsForAdminCabinetView(){
        return commonServiceFeignClient.getClients();
    }
}
