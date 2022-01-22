package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController extends BaseController {

    @Autowired
    private RequestOrderServiceFeignClientService clientServiceFeignClient;

    @RequestMapping(value = "/client-cabinet")
    public String openClientCabinetPage(HttpServletRequest request) {
        request.setAttribute("clientRequests", clientServiceFeignClient.getClientRequestsForView(getLoggedUser().getId()));
        request.setAttribute("clientOrders", clientServiceFeignClient.getClientOrdersForView(getLoggedUser().getId()));
        return openPage("clientcabinet");
    }

    @RequestMapping(value = "/client-request")
    public String doRequest(@RequestParam(name = "persons") String persons,
                            @RequestParam(name = "roomClass") String roomClass,
                            @RequestParam(name = "datefilter") String dateFilter) {

        clientServiceFeignClient.insertRequest(getLoggedUser().getId(), Integer.valueOf(persons), roomClass, dateFilter);
        return "forward:/client-cabinet";
    }
}
