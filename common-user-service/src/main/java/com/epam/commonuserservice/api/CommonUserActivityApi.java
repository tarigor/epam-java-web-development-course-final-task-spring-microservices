package com.epam.commonuserservice.api;


import com.epam.commonuserservice.entity.User;
import com.epam.commonuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommonUserActivityApi {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/login/{userEmail}/{password}")
    @ResponseStatus
    public ResponseEntity<Object> doLogin(
            @PathVariable String userEmail,
            @PathVariable String password) {
        System.out.println("received: email->" + userEmail + " password->" + password);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (userService.getUserByEmail(userEmail).isPresent()) {
            User user = userService.getUserByEmail(userEmail).get();
            System.out.println("user from DB -> " + user.toString());
            if (passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("password matched");
                //user is exist -> return User code:200
                return new ResponseEntity<>(
                        new User(
                                user.getId(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getEmail(),
                                user.getUserType()
                        ),
                        HttpStatus.OK);
            } else {
                //wrong password code -> return "" :403
                System.out.println("wrong password");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            //there is no such a user -> return "" code:404
            System.out.println("there is no such a user");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public HttpStatus doRegistration(@RequestBody User user) {
        System.out.println("received data -> " + user.toString());
        userService.insertNewUser(user);
        return HttpStatus.OK;
    }

    @GetMapping("/get-clients")
    public List<User> getClients() {
        return userService.getClients();
    }
}
