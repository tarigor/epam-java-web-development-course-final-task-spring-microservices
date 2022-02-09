package com.epam.apigatewayui.service.impl;

import com.epam.apigatewayui.feignservice.CommonServiceFeignClientService;
import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClientService;
import com.epam.apigatewayui.model.*;
import com.epam.apigatewayui.types.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminViewService {

    @Autowired
    private CommonServiceFeignClientService commonServiceFeignClientService;
    @Autowired
    private RequestOrderServiceFeignClientService requestOrderServiceFeignClientService;

    public ResponseEntity<List<ClientOrderView>> getClientsOrdersForAdminCabinetView() throws ServiceUnavailableException {
        List<Orders> clientOrders = requestOrderServiceFeignClientService.getOrdersForAdminCabinetView().getBody();
        List<User> clients = commonServiceFeignClientService.getClientsForAdminCabinetView().getBody();
        List<ClientOrderView> clientOrdersAdminView = new ArrayList<>();
        for (Orders order : clientOrders) {
            for (User user : clients) {
                if (order.getClientId() == user.getId()) {
                    clientOrdersAdminView.add(new ClientOrderView(
                            order.getOrderId(),
                            order.getRequestId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            order.getRoomId(),
                            order.getRoomClass(),
                            order.getCheckInDate(),
                            order.getCheckOutDate(),
                            RequestOrderStatus.valueOf(order.getOrderStatus()),
                            order.getInvoiceSentTime(),
                            order.getPaymentReceivingTime()
                    ));
                }
            }
        }
        return new ResponseEntity<>(clientOrdersAdminView, requestOrderServiceFeignClientService.getOrdersForAdminCabinetView().getStatusCode());
    }

    public ResponseEntity<List<ClientRequestView>> getClientsRequestsForAdminCabinetView() {
        List<Request> clientRequests = requestOrderServiceFeignClientService.getRequestsForAdminCabinetView().getBody();
        List<User> clients = commonServiceFeignClientService.getClientsForAdminCabinetView().getBody();
        List<ClientRequestView> clientRequestAdminView = new ArrayList<>();
        for (Request request : clientRequests) {
            for (User user : clients) {
                if (request.getClientId() == user.getId()) {
                    clientRequestAdminView.add(new ClientRequestView(
                            request.getRequestId(),
                            request.getClientId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            request.getPersonsAmount(),
                            request.getRoomClass(),
                            request.getCheckInDate(),
                            request.getCheckOutDate(),
                            RequestOrderStatus.valueOf(request.getRequestStatus()),
                            request.getRequestSentTime()
                    ));
                }
            }
        }
        return new ResponseEntity<>(clientRequestAdminView, requestOrderServiceFeignClientService.getRequestsForAdminCabinetView().getStatusCode());
    }
}
