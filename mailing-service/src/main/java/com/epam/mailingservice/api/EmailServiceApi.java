package com.epam.mailingservice.api;

import com.epam.mailingservice.model.EmailToAdmin;
import com.epam.mailingservice.model.EmailToClient;
import com.epam.mailingservice.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceApi {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/email-to-admin")
    public void sendEmailToAdmin(@RequestBody EmailToAdmin emailToAdmin) {
        emailService.sendEmailToAdmin(emailToAdmin);
    }

    @PostMapping("/email-to-client")
    public void sendEmailToClient(@RequestBody EmailToClient emailToClient) {
        emailService.sendEmailToClient(emailToClient);
    }
}
