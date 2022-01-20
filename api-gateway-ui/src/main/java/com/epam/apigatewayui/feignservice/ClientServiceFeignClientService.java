package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.OrderView;
import com.epam.apigatewayui.model.Request;
import com.epam.apigatewayui.model.RequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceFeignClientService {

    @Autowired
    private ClientServiceFeignClient clientServiceFeignClient;

    public List<RequestView> getClientRequestsForView(Long clientId) {
        return getRequestsListForView(clientServiceFeignClient.getClientRequests(clientId));
    }

    private List<RequestView> getRequestsListForView(List<Request> clientRequests) {
        return clientRequests.stream().map(RequestView::new).collect(Collectors.toList());
    }

    public List<OrderView> getClientOrdersForView(Long clientId) {
        return getOrdersListForView(clientServiceFeignClient.getClientOrders(clientId));
    }

    private List<OrderView> getOrdersListForView(List<Order> clientOrders) {
        return clientOrders.stream().map(OrderView::new).collect(Collectors.toList());
    }
}
