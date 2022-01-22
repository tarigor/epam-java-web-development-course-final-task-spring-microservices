package com.epam.apigatewayui.model;

import com.epam.apigatewayui.types.RequestOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderView {
    private long orderID;
    private int requestID;
    private boolean canBeCanceled;
    private String firstName;
    private String lastName;
    private String email;
    private int roomID;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private RequestOrderStatus requestOrderStatus;
    private boolean paymentRequired;
    private String invoiceSentTime;
    private String paymentReceivingTime;

    public ClientOrderView(
            long orderID,
            int requestID,
            String firstName,
            String lastName,
            String email,
            int roomID,
            String roomClass,
            Date checkInDate,
            Date checkOutDate,
            RequestOrderStatus requestOrderStatus,
            String invoiceSentTime,
            String paymentReceivingTime) {
        this.orderID = orderID;
        this.requestID = requestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roomID = roomID;
        this.roomClass = roomClass;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.requestOrderStatus = requestOrderStatus;
        if (requestOrderStatus.equals(RequestOrderStatus.WAITING_FOR_APPROVAL)) {
            setCanBeCanceled(true);
        }
        this.invoiceSentTime = invoiceSentTime;
        this.paymentReceivingTime = paymentReceivingTime;
    }
}
