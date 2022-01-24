package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClient;
import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClientService;
import com.epam.apigatewayui.model.OrdersDataWhileInsert;
import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.service.impl.AdminViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController extends BaseController {

    @Autowired
    private AdminViewService adminViewService;
    @Autowired
    private RequestOrderServiceFeignClientService requestOrderServiceFeignClientService;
    @Autowired
    private RequestOrderServiceFeignClient requestOrderServiceFeignClient;

    @GetMapping(value = "admin-cabinet")
    public String goAdminCabinet(HttpServletRequest request) {

        request.getSession().setAttribute("clientRequests", adminViewService.getClientsRequestsForAdminCabinetView());
        request.getSession().setAttribute("clientOrders", adminViewService.getClientsOrdersForAdminCabinetView());
        return openPage("admincabinet");
    }

    @RequestMapping(value = "request-handling")
    public String doHandling(@RequestParam("requestID") String requestID,
                             @RequestParam("dateFrom") String dateFrom,
                             @RequestParam("dateTo") String dateTo,
                             HttpServletRequest request) {

        request.getSession().setAttribute("clientRequest", requestOrderServiceFeignClientService.getRequestByRequestId(Integer.valueOf(requestID)));
        request.getSession().setAttribute("roomArrayList", requestOrderServiceFeignClient.getFreeRooms(dateFrom, dateTo));
        request.getSession().setAttribute("dateFrom", dateFrom);
        request.getSession().setAttribute("dateTo", dateTo);
        request.getSession().setAttribute("roomsData", requestOrderServiceFeignClientService.getRooms());
        return openPage("roomslist");
    }

    @PostMapping(value = "send-invoice")
    public String sendInvoice(
            @RequestParam(name = "singleRoomsSelected", required = false) String[] singleRoomsSelected,
            @RequestParam(name = "doubleRoomsSelected", required = false) String[] doubleRoomsSelected,
            @RequestParam(name = "suiteRoomsSelected", required = false) String[] suiteRoomsSelected,
            @RequestParam(name = "deluxeRoomsSelected", required = false) String[] deluxeRoomsSelected,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo,
            @RequestParam(name = "clientID") String clientID,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "requestID") String requestID
    ) {

        requestOrderServiceFeignClient.insertNewOrder(new OrdersDataWhileInsert(
                requestID,
                new User(Long.parseLong(clientID), firstName, lastName, email, ""),
                singleRoomsSelected,
                doubleRoomsSelected,
                suiteRoomsSelected,
                deluxeRoomsSelected,
                dateFrom,
                dateTo
        ));
        return "redirect:/admin-cabinet";
    }

    @RequestMapping(value = "reject")
    public String rejectRequest(@RequestParam(value = "request") Integer requestID) {

        requestOrderServiceFeignClient.rejectRequest(requestID);
        return "redirect:/admin-cabinet";
    }
}
