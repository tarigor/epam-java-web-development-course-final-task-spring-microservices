package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "REQUEST-ORDER-SERVICE", url = "http://localhost:8089")
public interface RequestOrderServiceFeignClient {

    @GetMapping("/client/get-requests/{clientId}")
    List<Request> getClientRequests(@PathVariable long clientId);

    @GetMapping("/client/get-orders/{clientId}")
    List<Orders> getClientOrders(@PathVariable long clientId);

    @PostMapping(value = "/client/insert-request", consumes = "application/json")
    void insertRequest(@RequestBody InsertRequestData insertRequestData);

    @GetMapping("/admin/get-orders")
    List<Orders> getClientsOrders();

    @GetMapping("/admin/get-requests")
    List<Request> getClientsRequests();

    @GetMapping("/admin/get-rooms")
    List<RoomData> getRooms();

    @GetMapping("/admin/get-freeRooms/{checkInDate}/{checkOutDate}")
    List<RoomView> getFreeRooms(@PathVariable String checkInDate, @PathVariable String checkOutDate);

    @PostMapping(value = "/admin/insert-order", consumes = "application/json")
    void insertNewOrder(@RequestBody OrdersDataWhileInsert ordersDataWhileInsert);

    @PostMapping(value = "/admin/reject/{requestId}")
    void rejectRequest(@PathVariable Integer requestId);

    @PostMapping("/modify-order-status-paid/{orderID}")
    void changeOrderStatus(@PathVariable Integer orderID);
}
