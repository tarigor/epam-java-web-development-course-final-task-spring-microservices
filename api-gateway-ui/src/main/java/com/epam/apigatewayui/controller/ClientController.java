package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.ClientServiceFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController extends BaseController {

    @Autowired
    private ClientServiceFeignClientService clientServiceFeignClient;

    @RequestMapping(value = "/client-cabinet")
    public String openClientCabinetPage(HttpServletRequest request) {
        request.setAttribute("clientRequests", clientServiceFeignClient.getClientRequestsForView(getLoggedUser(request).getId()));
        request.setAttribute("clientOrders", clientServiceFeignClient.getClientOrdersForView(getLoggedUser(request).getId()));
        return openPage("clientcabinet");
    }
}
