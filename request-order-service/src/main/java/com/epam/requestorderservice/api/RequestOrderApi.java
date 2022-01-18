package com.epam.requestorderservice.api;

import com.epam.requestorderservice.entity.ClientOrders;
import com.epam.requestorderservice.entity.Requests;
import com.epam.requestorderservice.repository.ClientOrdersRepository;
import com.epam.requestorderservice.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestOrderApi {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClientOrdersRepository clientOrdersRepository;

    @GetMapping("/get-requests/{clientId}")
    public Requests getClientRequests(@PathVariable Integer clientId) {
        return requestRepository.getById(clientId);
    }

    @GetMapping("/get-orders/{clientId}")
    public List<ClientOrders> getClientOrders(@PathVariable Long clientId) {
        return clientOrdersRepository.getClientOrdersByClientId(clientId);
    }

    @PostMapping("/insert-request")
    public Requests insertNewRequest(@RequestBody Requests request) {
        return requestRepository.save(request);
    }


}
