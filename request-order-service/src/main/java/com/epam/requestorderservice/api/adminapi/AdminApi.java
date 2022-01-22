package com.epam.requestorderservice.api.adminapi;

import com.epam.requestorderservice.entity.ClientOrders;
import com.epam.requestorderservice.entity.Requests;
import com.epam.requestorderservice.entity.RoomData;
import com.epam.requestorderservice.entity.RoomView;
import com.epam.requestorderservice.model.OrdersDataWhileInsert;
import com.epam.requestorderservice.repository.ClientOrdersRepository;
import com.epam.requestorderservice.repository.RequestRepository;
import com.epam.requestorderservice.service.OrderService;
import com.epam.requestorderservice.service.RequestService;
import com.epam.requestorderservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminApi {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ClientOrdersRepository clientOrdersRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RequestService requestService;


    @GetMapping("/get-requests")
    public List<Requests> getClientsRequests() {
        return requestRepository.findAll();
    }

    @GetMapping("/get-orders")
    public List<ClientOrders> getClientsOrders() {
        return clientOrdersRepository.findAll();
    }

    @GetMapping("/get-rooms")
    public List<RoomData> getRoomsData() {
        return roomService.getAllRoomsData();
    }

    @GetMapping("/get-freeRooms/{checkInDate}/{checkOutDate}")
    public List<RoomView> getFreeRooms(@PathVariable Date checkInDate, @PathVariable Date checkOutDate) {
        return roomService.getFreeRooms(checkInDate, checkOutDate);
    }

    @PostMapping("/insert-order")
    public void insertNewOrder(@RequestBody OrdersDataWhileInsert ordersDataWhileInsert) {
        orderService.insertNewOrder(ordersDataWhileInsert);
    }

    @PostMapping(value = "/reject/{requestId}")
    public void rejectRequest(@PathVariable Integer requestId) {
        requestService.changeRequestStatus(requestId);
    }
}
