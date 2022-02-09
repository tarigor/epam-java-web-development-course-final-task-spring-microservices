package com.epam.requestorderservice.api.clientapi;

import com.epam.requestorderservice.entity.ClientOrders;
import com.epam.requestorderservice.entity.Requests;
import com.epam.requestorderservice.model.InsertRequestData;
import com.epam.requestorderservice.repository.ClientOrdersRepository;
import com.epam.requestorderservice.repository.RequestRepository;
import com.epam.requestorderservice.service.OrderService;
import com.epam.requestorderservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientApi {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClientOrdersRepository clientOrdersRepository;
    @Autowired
    private RequestService requestService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/get-requests/{clientId}")
    public List<Requests> getClientRequests(@PathVariable Long clientId) {
        return requestRepository.getRequestsByClientId(clientId);
    }

    @GetMapping("/get-orders/{clientId}")
    public List<ClientOrders> getClientOrders(@PathVariable Long clientId) {
        return clientOrdersRepository.getClientOrdersByClientId(clientId);
    }

    @PostMapping("/insert-request")
    public Requests insertNewRequest(@RequestBody InsertRequestData insertRequestData) {
        return requestService.insertRequest(insertRequestData);
    }

    @PostMapping("/modify-order-status-paid/{orderID}")
    public HttpStatus changeOrderStatus(@PathVariable Integer orderID) {
        System.out.println("in change order status");
        orderService.changeOrderStatus(orderID);
        return HttpStatus.OK;
    }
}
