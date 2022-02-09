package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.*;
import com.epam.apigatewayui.types.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RequestOrderServiceFeignClientService {

    @Autowired
    private RequestOrderServiceFeignClient requestOrderServiceFeignClient;
    @Autowired
    private CommonServiceFeignClient commonServiceFeignClient;

    private static Date convertStringToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return dateSQL;
    }

    public ResponseEntity<List<RequestView>> getClientRequestsForView(Long clientId) {
        return new ResponseEntity<>(
                getRequestsListForView(Objects.requireNonNull(requestOrderServiceFeignClient.getClientRequests(clientId).getBody())),
                requestOrderServiceFeignClient.getClientRequests(clientId).getStatusCode());
    }

    private List<RequestView> getRequestsListForView(List<Request> clientRequests) {
        return clientRequests.stream().map(RequestView::new).collect(Collectors.toList());
    }

    public ResponseEntity<List<OrderView>> getClientOrdersForView(Long clientId) {
        return new ResponseEntity<>(getOrdersListForView(
                Objects.requireNonNull(requestOrderServiceFeignClient.getClientOrders(clientId).getBody())),
                requestOrderServiceFeignClient.getClientOrders(clientId).getStatusCode());
    }

    private List<OrderView> getOrdersListForView(List<Orders> clientOrders) {
        return clientOrders.stream().map(OrderView::new).collect(Collectors.toList());
    }

    public HttpStatus insertRequest(User user, Integer persons, String roomClass, String dateFilter) {
        Date checkInDateSQL = convertStringToSqlDate(dateFilter.split(":")[0].trim());
        Date checkOutDateSQL = convertStringToSqlDate(dateFilter.split(":")[1].trim());
        return requestOrderServiceFeignClient.insertRequest(new InsertRequestData(user, new Request(
                0,
                user.getId(),
                persons,
                roomClass,
                checkInDateSQL,
                checkOutDateSQL,
                RequestOrderStatus.WAITING_FOR_APPROVAL.name(),
                getCurrentTime()
        )));
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(cal.getTime());
    }

    public ResponseEntity<List<Request>> getRequestsForAdminCabinetView() {
        System.out.println("REQUESTS");
        return requestOrderServiceFeignClient.getClientsRequests();
    }

    public ResponseEntity<List<Orders>> getOrdersForAdminCabinetView() {
        System.out.println("ORDERS");
        return requestOrderServiceFeignClient.getClientsOrders();
    }

    public ResponseEntity<List<RoomData>> getRooms() {
        return requestOrderServiceFeignClient.getRooms();
    }

    public ResponseEntity<Double> getRoomPriceByRoomID(int roomId) {
        return new ResponseEntity<>(Objects.requireNonNull(getRooms().getBody()).stream()
                .filter(roomData -> roomData.getId().equals(roomId))
                .findFirst().get().getRoomCost(), getRooms().getStatusCode());
    }

    public ResponseEntity<ClientRequestView> getRequestByRequestId(Integer requestId) {
        Request request = Objects.requireNonNull(requestOrderServiceFeignClient.getClientsRequests().getBody()).stream()
                .filter(r -> r.getRequestId().equals(requestId))
                .findFirst().get();
        User user = commonServiceFeignClient.getClients().getBody().stream()
                .filter(u -> u.getId() == request.getClientId())
                .findFirst().get();
        return new ResponseEntity<>(new ClientRequestView(
                request.getRequestId(),
                request.getClientId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                request.getPersonsAmount(),
                request.getRoomClass(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                RequestOrderStatus.valueOf(request.getRequestStatus()),
                request.getRequestSentTime()
        ), requestOrderServiceFeignClient.getClientsRequests().getStatusCode());
    }

    public void changeOrderStatus(int orderID) {
        requestOrderServiceFeignClient.changeOrderStatus(orderID);
    }
}
