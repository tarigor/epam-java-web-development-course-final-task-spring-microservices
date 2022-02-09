package com.epam.apigatewayui.feignservice.fallback;

import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClient;
import com.epam.apigatewayui.model.*;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestOrderServiceFeignClientFactoryFallback implements FallbackFactory<RequestOrderServiceFeignClient> {
    @Override
    public RequestOrderServiceFeignClient create(Throwable cause) {
        return new RequestOrderServiceFeignClient() {
            @Override
            public ResponseEntity<List<Request>> getClientRequests(long clientId) {
                return new ResponseEntity<>(new ArrayList<>() {
                    @Override
                    public void add(int index, Request element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public ResponseEntity<List<Orders>> getClientOrders(long clientId) {
                System.out.println("method-2");
                return new ResponseEntity<List<Orders>>(new ArrayList<>() {
                    @Override
                    public void add(int index, Orders element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public HttpStatus insertRequest(InsertRequestData insertRequestData) {
                System.out.println("method-3");
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public ResponseEntity<List<Orders>> getClientsOrders() {
                System.out.println("method-4");
                return new ResponseEntity<List<Orders>>(new ArrayList<>() {
                    @Override
                    public void add(int index, Orders element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);

            }

            @Override
            public ResponseEntity<List<Request>> getClientsRequests() {
                System.out.println("method-5");
                return new ResponseEntity<>(new ArrayList<>() {
                    @Override
                    public void add(int index, Request element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public ResponseEntity<List<RoomData>> getRooms() {
                System.out.println("method-6");
                return new ResponseEntity<List<RoomData>>(new ArrayList<>() {
                    @Override
                    public void add(int index, RoomData element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public ResponseEntity<List<RoomView>> getFreeRooms(String checkInDate, String checkOutDate) {
                System.out.println("method-7");
                return new ResponseEntity<List<RoomView>>(new ArrayList<>() {
                    @Override
                    public void add(int index, RoomView element) {
                        super.add(index, element);
                    }
                }, HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public HttpStatus insertNewOrder(OrdersDataWhileInsert ordersDataWhileInsert) {
                System.out.println("method-8");
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public HttpStatus rejectRequest(Integer requestId) {
                System.out.println("method-9");
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public HttpStatus changeOrderStatus(Integer orderID) {
                System.out.println("method-10");
                return HttpStatus.SERVICE_UNAVAILABLE;
            }
        };
    }
}
