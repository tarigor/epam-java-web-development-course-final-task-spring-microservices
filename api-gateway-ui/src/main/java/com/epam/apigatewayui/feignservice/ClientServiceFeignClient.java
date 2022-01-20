package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "REQUEST-ORDER-SERVICE", url = "http://localhost:8089")
public interface ClientServiceFeignClient {

    @GetMapping("/client/get-requests/{clientId}")
    List<Request> getClientRequests(@PathVariable long clientId);

    @GetMapping("/client/get-orders/{clientId}")
    List<Order> getClientOrders(@PathVariable long clientId);
}
