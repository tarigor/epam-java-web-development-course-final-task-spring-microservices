package com.epam.requestorderservice.api.clientapi;

import com.epam.requestorderservice.entity.ClientOrders;
import com.epam.requestorderservice.entity.Requests;
import com.epam.requestorderservice.repository.ClientOrdersRepository;
import com.epam.requestorderservice.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientApi {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClientOrdersRepository clientOrdersRepository;

    @GetMapping("/get-requests/{clientId}")
    public List<Requests> getClientRequests(@PathVariable Long clientId) {
        return requestRepository.getRequestsByClientId(clientId);
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
