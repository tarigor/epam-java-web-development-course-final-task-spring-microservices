package com.epam.mailingservice;

import com.epam.mailingservice.model.EmailToClient;
import com.epam.mailingservice.model.User;
import com.epam.mailingservice.service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailingServiceApplicationTests {

    @Autowired
    private EmailServiceImpl emailService;

    @Test
    void contextLoads() {

//        private int requestID;
//        private int orderAssigned;
//
//        rivate long id;
//        private String firstName;
//        private String lastName;
//        private String email;
//        private String userType;

        User user = new User(1L, "Igor", "Taren", "palkin@yopmail.com", "CLIENT");
        int requestID = 1;
        int orderAssigned = 1;
        EmailToClient emailToClient = new EmailToClient(user, requestID, orderAssigned);
        emailService.sendEmailToClient(emailToClient);
    }

}
