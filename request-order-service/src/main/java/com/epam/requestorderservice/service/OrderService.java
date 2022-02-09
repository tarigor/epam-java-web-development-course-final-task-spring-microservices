package com.epam.requestorderservice.service;

import com.epam.requestorderservice.entity.Orders;
import com.epam.requestorderservice.feignservice.EmailingServiceFeignClientService;
import com.epam.requestorderservice.model.OrdersDataWhileInsert;
import com.epam.requestorderservice.model.User;
import com.epam.requestorderservice.repository.OrderRepository;
import com.epam.requestorderservice.repository.RequestRepository;
import com.epam.requestorderservice.type.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private EmailingServiceFeignClientService emailingServiceFeignClientService;

    protected static Date convertStringToSqlDate(String date) {
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

    @Transactional
    public HttpStatus insertNewOrder(OrdersDataWhileInsert orders) {

        boolean res1 = insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getSingleRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        boolean res2 = insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getDoubleRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        boolean res3 = insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getSuiteRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        boolean res4 = insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getDeluxeRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        return res1 && res2 && res3 && res4 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private boolean insertOrder(Integer requestID, User user, String[] roomsSelected, Date dateFrom, Date dateTo) {
        boolean res = false;
        if (roomsSelected != null && roomsSelected.length != 0) {
            for (String room : roomsSelected) {
                Orders savedOrder = orderRepository.save(new Orders(
                        0,
                        requestID,
                        user.getId(),
                        Integer.parseInt(room),
                        dateFrom,
                        dateTo,
                        RequestOrderStatus.APPROVED_WAITING_FOR_PAYMENT.name(),
                        getCurrentTime(),
                        "-"
                ));
                if (savedOrder != null) {
                    res = true;
                }
                emailingServiceFeignClientService.sentEmailToClient(user, requestID, savedOrder.getOrderId());
            }
        }
        requestRepository.changeRequestStatus(RequestOrderStatus.REQUEST_PROCESSED.name(), requestID);
        return res;
    }

    public void changeOrderStatus(int orderID) {
        System.out.println("in change order status 2 , order -> " + orderID);
        orderRepository.changeOrderStatus(RequestOrderStatus.PAID_AND_BOOKED.name(), orderID);
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(cal.getTime());
    }
}
