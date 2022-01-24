package com.epam.requestorderservice.service;

import com.epam.requestorderservice.entity.Requests;
import com.epam.requestorderservice.feignservice.EmailingServiceFeignClientService;
import com.epam.requestorderservice.model.InsertRequestData;
import com.epam.requestorderservice.repository.RequestRepository;
import com.epam.requestorderservice.type.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private EmailingServiceFeignClientService emailingServiceFeignClientService;

    public void changeRequestStatus(Integer requestId) {
        requestRepository.changeRequestStatus(RequestOrderStatus.REJECTED.name(), requestId);
    }

    public Requests insertRequest(InsertRequestData insertRequestData) {
        Requests request = requestRepository.save(insertRequestData.getRequest());
        insertRequestData.getRequest().setRequestId(request.getRequestId());
        emailingServiceFeignClientService.sendEmailToAdmin(insertRequestData.getUser(), insertRequestData.getRequest());
        return request;
    }
}
