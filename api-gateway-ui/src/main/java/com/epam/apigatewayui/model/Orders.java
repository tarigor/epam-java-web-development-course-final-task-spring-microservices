package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer orderId;
    private long clientId;
    private Integer requestId;
    private Integer roomId;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private String orderStatus;
    private String invoiceSentTime;
    private String paymentReceivingTime;
}
