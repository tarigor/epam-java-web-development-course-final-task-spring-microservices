package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.*;
import com.epam.apigatewayui.types.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestOrderServiceFeignClientService {

    @Autowired
    private RequestOrderServiceFeignClient requestOrderServiceFeignClient;
    @Autowired
    private CommonServiceFeignClient commonServiceFeignClient;

    public List<RequestView> getClientRequestsForView(Long clientId) {
        return getRequestsListForView(requestOrderServiceFeignClient.getClientRequests(clientId));
    }

    private List<RequestView> getRequestsListForView(List<Request> clientRequests) {
        return clientRequests.stream().map(RequestView::new).collect(Collectors.toList());
    }

    public List<OrderView> getClientOrdersForView(Long clientId) {
        return getOrdersListForView(requestOrderServiceFeignClient.getClientOrders(clientId));
    }

    private List<OrderView> getOrdersListForView(List<Orders> clientOrders) {
        return clientOrders.stream().map(OrderView::new).collect(Collectors.toList());
    }

    public void insertRequest(User user, Integer persons, String roomClass, String dateFilter) {
        Date checkInDateSQL = convertStringToSqlDate(dateFilter.split(":")[0].trim());
        Date checkOutDateSQL = convertStringToSqlDate(dateFilter.split(":")[1].trim());
        requestOrderServiceFeignClient.insertRequest(new InsertRequestData(user, new Request(
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

    public List<Request> getRequestsForAdminCabinetView() {
        List<Request> requestList = requestOrderServiceFeignClient.getClientsRequests();
        System.out.println("REQUESTS");
        requestList.forEach(System.out::println);
        return requestList;
    }

    public List<Orders> getOrdersForAdminCabinetView() {
        List<Orders> orderList = requestOrderServiceFeignClient.getClientsOrders();
        System.out.println("ORDERS");
        orderList.forEach(System.out::println);
        return orderList;
    }

    public List<RoomData> getRooms() {
        return requestOrderServiceFeignClient.getRooms();
    }

    public Double getRoomPriceByRoomID(int roomId){
        return getRooms().stream().filter(roomData -> roomData.getId().equals(roomId)).findFirst().get().getRoomCost();
    }

    public ClientRequestView getRequestByRequestId(Integer requestId) {
        Request request = requestOrderServiceFeignClient.getClientsRequests().stream()
                .filter(r -> r.getRequestId().equals(requestId))
                .findFirst().get();
        User user = commonServiceFeignClient.getClients().stream()
                .filter(u -> u.getId() == request.getClientId())
                .findFirst().get();
        return new ClientRequestView(
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
        );
    }

    public void changeOrderStatus(int orderID){
        requestOrderServiceFeignClient.changeOrderStatus(orderID);
    }
}
