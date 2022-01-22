package com.epam.requestorderservice.service;

import com.epam.requestorderservice.repository.RequestRepository;
import com.epam.requestorderservice.type.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void changeRequestStatus(Integer requestId){
        requestRepository.changeRequestStatus(RequestOrderStatus.REJECTED.name(),requestId);
    }
}
