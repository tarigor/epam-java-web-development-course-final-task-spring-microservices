package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.feignservice.fallback.RequestOrderServiceFeignClientFactoryFallback;
import com.epam.apigatewayui.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "REQUEST-ORDER-SERVICE", url = "http://localhost:8089", fallbackFactory = RequestOrderServiceFeignClientFactoryFallback.class)
public interface RequestOrderServiceFeignClient {

    @GetMapping("/client/get-requests/{clientId}")
    ResponseEntity<List<Request>> getClientRequests(@PathVariable long clientId);

    @GetMapping("/client/get-orders/{clientId}")
    ResponseEntity<List<Orders>> getClientOrders(@PathVariable long clientId);

    @PostMapping(value = "/client/insert-request", consumes = "application/json")
    HttpStatus insertRequest(@RequestBody InsertRequestData insertRequestData);

    @GetMapping("/admin/get-orders")
    ResponseEntity<List<Orders>> getClientsOrders();

    @GetMapping("/admin/get-requests")
    ResponseEntity<List<Request>> getClientsRequests();

    @GetMapping("/admin/get-rooms")
    ResponseEntity<List<RoomData>> getRooms();

    @GetMapping("/admin/get-freeRooms/{checkInDate}/{checkOutDate}")
    ResponseEntity<List<RoomView>> getFreeRooms(@PathVariable String checkInDate, @PathVariable String checkOutDate);

    @PostMapping(value = "/admin/insert-order", consumes = "application/json")
    HttpStatus insertNewOrder(@RequestBody OrdersDataWhileInsert ordersDataWhileInsert);

    @PostMapping(value = "/admin/reject/{requestId}")
    HttpStatus rejectRequest(@PathVariable Integer requestId);

    @PostMapping("/client/modify-order-status-paid/{orderID}")
    HttpStatus changeOrderStatus(@PathVariable Integer orderID);
}
