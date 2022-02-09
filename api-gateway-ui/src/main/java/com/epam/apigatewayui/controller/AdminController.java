package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClient;
import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClientService;
import com.epam.apigatewayui.model.*;
import com.epam.apigatewayui.service.impl.AdminViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController extends BaseController {

    @Autowired
    private AdminViewService adminViewService;
    @Autowired
    private RequestOrderServiceFeignClientService requestOrderServiceFeignClientService;
    @Autowired
    private RequestOrderServiceFeignClient requestOrderServiceFeignClient;

    @GetMapping(value = "admin-cabinet")
    public String goAdminCabinet(HttpServletRequest request) throws ServiceUnavailableException {

        ResponseEntity<List<ClientRequestView>> listClientRequestsResponseEntity = adminViewService.getClientsRequestsForAdminCabinetView();
        checkForServiceError(listClientRequestsResponseEntity.getStatusCode(), request);
        request.getSession().setAttribute("clientRequests", listClientRequestsResponseEntity.getBody());

        ResponseEntity<List<ClientOrderView>> listClientOrderResponseEntity = adminViewService.getClientsOrdersForAdminCabinetView();
        checkForServiceError(listClientOrderResponseEntity.getStatusCode(), request);
        request.getSession().setAttribute("clientOrders", listClientOrderResponseEntity.getBody());
        return openPage("admincabinet");
    }

    @RequestMapping(value = "request-handling")
    public String doHandling(@RequestParam("requestID") String requestID,
                             @RequestParam("dateFrom") String dateFrom,
                             @RequestParam("dateTo") String dateTo,
                             HttpServletRequest request) {

        ResponseEntity<ClientRequestView> clientRequestViewResponseEntity = requestOrderServiceFeignClientService.getRequestByRequestId(Integer.valueOf(requestID));
        checkForServiceError(clientRequestViewResponseEntity.getStatusCode(), request);
        request.getSession().setAttribute("clientRequest", clientRequestViewResponseEntity.getBody());

        ResponseEntity<List<RoomView>> roomViewResponseEntity = requestOrderServiceFeignClient.getFreeRooms(dateFrom, dateTo);
        checkForServiceError(roomViewResponseEntity.getStatusCode(), request);
        request.getSession().setAttribute("roomArrayList", roomViewResponseEntity.getBody());
        request.getSession().setAttribute("dateFrom", dateFrom);
        request.getSession().setAttribute("dateTo", dateTo);

        ResponseEntity<List<RoomData>> roomDataResponseEntity = requestOrderServiceFeignClientService.getRooms();
        checkForServiceError(roomDataResponseEntity.getStatusCode(), request);
        request.getSession().setAttribute("roomsData", roomDataResponseEntity.getBody());
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
            @RequestParam(name = "requestID") String requestID,
            HttpServletRequest request
    ) {

        checkForServiceError(
                requestOrderServiceFeignClient.insertNewOrder(new OrdersDataWhileInsert(
                        requestID,
                        new User(Long.parseLong(clientID), firstName, lastName, email, ""),
                        singleRoomsSelected,
                        doubleRoomsSelected,
                        suiteRoomsSelected,
                        deluxeRoomsSelected,
                        dateFrom,
                        dateTo
                )), request);
        return "redirect:/admin-cabinet";
    }

    @RequestMapping(value = "reject")
    public String rejectRequest(@RequestParam(value = "request") Integer requestID, HttpServletRequest request) {

        checkForServiceError(requestOrderServiceFeignClient.rejectRequest(requestID), request);
        return "redirect:/admin-cabinet";
    }
}
