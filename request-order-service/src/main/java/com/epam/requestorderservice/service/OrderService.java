package com.epam.requestorderservice.service;

import com.epam.requestorderservice.entity.Orders;
import com.epam.requestorderservice.feignservice.EmailingServiceFeignClientService;
import com.epam.requestorderservice.model.OrdersDataWhileInsert;
import com.epam.requestorderservice.model.User;
import com.epam.requestorderservice.repository.OrderRepository;
import com.epam.requestorderservice.repository.RequestRepository;
import com.epam.requestorderservice.type.RequestOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void insertNewOrder(OrdersDataWhileInsert orders) {

        insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getSingleRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getDoubleRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getSuiteRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
        insertOrder(
                Integer.parseInt(orders.getRequestID()),
                orders.getUser(),
                orders.getDeluxeRoomsSelected(),
                convertStringToSqlDate(orders.getDateFrom()),
                convertStringToSqlDate(orders.getDateTo()));
    }

    private void insertOrder(Integer requestID, User user, String[] roomsSelected, Date dateFrom, Date dateTo) {
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
                emailingServiceFeignClientService.sentEmailToClient(user, requestID, savedOrder.getOrderId());
            }
        }
        requestRepository.changeRequestStatus(RequestOrderStatus.REQUEST_PROCESSED.name(), requestID);
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(cal.getTime());
    }

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
}
