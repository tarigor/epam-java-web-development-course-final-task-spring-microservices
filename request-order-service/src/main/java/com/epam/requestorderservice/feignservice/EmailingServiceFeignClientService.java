package com.epam.requestorderservice.feignservice;

import com.epam.requestorderservice.entity.Requests;
import com.epam.requestorderservice.model.EmailToAdmin;
import com.epam.requestorderservice.model.EmailToClient;
import com.epam.requestorderservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailingServiceFeignClientService {

    @Autowired
    private EmailingServiceFeignClient emailingServiceFeignClient;


    public void sentEmailToClient(User user, Integer requestID, Integer orderId) {
        emailingServiceFeignClient.sendEmailToClient(new EmailToClient(user, requestID, orderId));
    }

    public void sendEmailToAdmin(User user, Requests request) {
        emailingServiceFeignClient.sendEmailToAdmin(new EmailToAdmin(
                user,
                request.getRequestId(),
                request.getPersonsAmount(),
                request.getRoomClass(),
                request.getCheckInDate().toString(),
                request.getCheckOutDate().toString()));
    }
}
