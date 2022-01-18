package com.epam.commonuserservice.api;



import com.epam.commonuserservice.entity.User;
import com.epam.commonuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommonUserActivityApi {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/login/{userEmail}/{password}")
    @ResponseStatus
    public ResponseEntity<Object> doLogin(
            @PathVariable String userEmail,
            @PathVariable String password) {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (userService.getUserByEmail(userEmail).isPresent()) {
            User user = userService.getUserByEmail(userEmail).get();
            if (user.getPassword().contentEquals(password)) {
                //user is exist -> return User code:200
                return new ResponseEntity<>(
                        new User(
                                user.getId(),
                                user.getUserFirstName(),
                                user.getUserLastName(),
                                user.getUserEmail(),
                                user.getUserType()),
                        HttpStatus.OK);
            } else {
                //wrong password code -> return "" :403
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            //there is no such a user -> return "" code:404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "registration/{userRegistrationData}")
    public long doRegistration(@PathVariable String[] userRegistrationData) {
        return 0L;
    }
}
