package com.epam.commonuserservice;

import com.epam.commonuserservice.entity.User;
import com.epam.commonuserservice.repository.UserRepository;
import com.epam.commonuserservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CommonUserServiceApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user = userService.getUserByEmail("elkin@mail.com").get();
        System.out.println("user->"+user.toString());
    }

}
